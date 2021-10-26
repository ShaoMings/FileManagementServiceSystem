package com.graduation.repo.adapter;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.jcr.api.Jcr;
import com.graduation.jcr.model.dto.JcrContentTreeDto;
import com.graduation.model.dto.gitee.response.ContentTreeDto;
import com.graduation.model.dto.gitee.response.TreeDto;
import com.graduation.utils.DateConverter;
import com.graduation.jcr.utils.JcrUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description Gitee 远程仓库适配器
 *
 * @author shaoming
 * @date 2021/10/20 15:40
 * @since 1.0
 */
@Component
public class GiteeAdapter implements Jcr {

    private static final Integer FILE_CREATED_STATUS_CODE = 201;

    @Autowired
    private JcrUtils jcrUtils;


    /**
     * 初始化仓库  一般授权后执行 初始化已授权的仓库信息到jcr中
     *
     * @param repository 初始化的jcr仓库名称  这里设定为登录用户的用户+/+当前远程仓库名称
     * @param api        实际远程操作的api
     * @param params     请求参数
     * @param method     请求方式
     * @return 是否初始化成功
     */
    @Override
    public boolean initializeRepository(String repository, String api, Map<String, Object> params, String method) {
        // 如果之前仓库有数据，需重新删除 添加
        boolean hasOld = jcrUtils.hasItemOnNodeByAbsPath("/" + repository);
        if (hasOld) {
            jcrUtils.removeItemByAbsPath("/" + repository);
        }
        // last modified time
        StringBuilder sb = new StringBuilder();
        String owner = (String) params.get("owner");
        String repo = (String) params.get("repo");
        String token = (String) params.get("access_token");
        sb.append("https://gitee.com/api/v5/repos/").append(owner)
                .append("/").append(repo).append("/events");
        Map<String, Object> m = new HashMap<>(2);
        m.put("access_token", token);
        m.put("limit", 1);
        String lastUtcTime = getTheLast(sb.toString(), m, "GET");
        jcrUtils.addPropertyOnNodeByAbsPath(".last-" + repo, lastUtcTime, "/" + repository);
        params.remove("owner");
        params.remove("repo");
        // init repository
        String json = jcrUtils.executeUrl(api, params, method);
        if (StringUtils.isNotBlank(json)) {
            String prefix = "/" + repository + "/";
            ContentTreeDto fileTreeDto = JSONUtil.toBean(json, ContentTreeDto.class);
            List<TreeDto> tree = fileTreeDto.getTree();
            if (tree != null) {
                tree.forEach(t -> {
                    // 初始格式化 getPath
                    t.getPath();
                    if (t.getIs_dir()) {
                        jcrUtils.addNodeOnRootByAbsPath(prefix + t.getPath());
                    } else {
                        jcrUtils.addPropertyOnNodeByAbsPath(t.getName(), JSONUtil.toJsonStr(t), prefix + t.getPath());
                    }
                });
                return true;
            }
        }
        return false;
    }

    /**
     * 获取用户授权的所有仓库项目
     * @param token 用户令牌
     * @return json数组
     */
    public JSONArray getAllRepoArray(String token){
        String api = "https://gitee.com/api/v5/user/repos";
        Map<String, Object> params = new HashMap<>(4);
        params.put("access_token", token);
        params.put("sort", "full_name");
        params.put("page", "1");
        params.put("pre_page","100");
        String json = HttpUtil.get(api, params);
        if (StringUtils.isNotBlank(json)) {
            return JSONUtil.parseArray(json);
        }
        return null;
    }

    /**
     * 获取用户授权的所有仓库项目的总数
     * @param token 用户令牌
     * @return 仓库项目总数
     */
    public Integer getAllRepoCount(String token){
        JSONArray allRepoArray = getAllRepoArray(token);
        if (allRepoArray!=null){
            return allRepoArray.size();
        }
        return 0;
    }

    /**
     * 通过token 获取 远程仓库用户名
     *
     * @param token token
     * @return 远程仓库用户名
     */
    public String getOwnerByToken(String token) {
        String api = "https://gitee.com/api/v5/user?access_token=" + token;
        String json = HttpUtil.get(api);
        if (StringUtils.isNotBlank(json)) {
            return JSONUtil.parseObj(json).getStr("login");
        }
        return null;
    }

    /**
     * 获取仓库最近一次更新时间 是否比现在早   判断是否为最新的
     *
     * @param api    实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 仓库是否需要更新数据
     */
    public boolean isNoTheLast(String api, Map<String, Object> params, String method, String repoLastTime) {
        if (repoLastTime != null) {
            String utcTime = getTheLast(api, params, method);
            return DateConverter.isFirstUtcTimeIsEarlier(repoLastTime, utcTime);
        }
        return true;
    }

    /**
     * 获取仓库存放的最近一次更新时间
     *
     * @return 仓库是否为最新状态
     */
    public String getTheLastOnRepo(String user, String repo) {
        return jcrUtils.getStringPropertyByAbsPath("/" + user + "/" + repo + "/" + ".last-" + repo);
    }

    /**
     * 获取仓库最近一次更新时间
     *
     * @param api    实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 仓库最近一次更新时间 utc
     */
    public String getTheLast(String api, Map<String, Object> params, String method) {
        String json = jcrUtils.executeUrl(api, params, method);
        JSONArray jsonArray = JSONUtil.parseArray(json);
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        return jsonObject.getStr("created_at");
    }

    /**
     * 获取用户的所有授权仓库名称
     *
     * @param user 用户名
     * @return 仓库名
     */
    public List<String> getAllRepoNames(String user) {
        return jcrUtils.getAllNodeNamesOnNodeByAbs("/" + user);
    }

    /**
     * !!! 注意 gitee 仓库操作路径中 根路径不要以 / 开头   jcr中则需要
     * <p>
     * 添加文件夹  gitee中没有只创建文件夹的api
     * 需通过创建文件api进行操作 同时文件夹不能为空 需给一个默认空文件 .keep  空格字符串 base64 为 IA==
     *
     * @param absolute 文件夹的绝对路径
     * @param api      实际远程操作的api
     * @param params   请求参数
     * @param method   请求方式
     * @return 是否添加成功
     */
    @Override
    public boolean addDirectory(String absolute, String api, Map<String, Object> params, String method) {
        int code = jcrUtils.executeUrlBackResponse(api, params, method).getStatus();
        if (code == FILE_CREATED_STATUS_CODE){
            boolean isAdded = jcrUtils.addNodeOnRootByAbsPath(absolute);
            if (isAdded) {
                jcrUtils.addPropertyOnNodeByAbsPath(".keep", " ", absolute);
                return StringUtils.isNotBlank(jcrUtils.executeUrl(api, params, method));
            }
        }
        return false;
    }

    /**
     * 删除文件夹
     *
     * @param absolute 文件夹的绝对路径
     * @param params 自定义参数
     * @return 是否删除成功
     */
    @Override
    public boolean removeDirectory(String absolute,Map<String ,Object> params) {
        // 删除文件夹需保证文件夹里没有文件  包括其子文件夹  当一个文件夹没有文件时 会默认把文件夹删除
        // 所以只需把文件夹下所有文件删除即可
        // 远程删除的path开头没有/
        String user = (String) params.get("user");
        String repo = (String) params.get("repo");
        String token = (String) params.get("token");
        String owner = getOwnerByToken(token);
        if (absolute.startsWith("/")){
            absolute = absolute.substring(1);
        }
        String name = absolute.substring(absolute.lastIndexOf("/")+1);
        removeDirOnGiteeRepo(absolute,repo,owner,token,"删除文件夹:"+name+" "+DateConverter.getNowMonthAndDay());
        String repository = "/"+user+"/"+repo+"/"+absolute;
        return jcrUtils.removeItemByAbsPath(repository);
    }


    /**
     * 递归远程删除文件夹下的所有文件
     * @param path 要删除的文件夹路径 开头不含 /
     * @param repo 仓库项目
     * @param owner 远程用户名
     * @param token 令牌
     * @param msg commit 信息
     */
    public void removeDirOnGiteeRepo(String path,String repo,String owner,String token,String msg){
        String contentApi = "https://gitee.com/api/v5/repos/"+owner+"/"+repo+"/contents/"
                +path+"?access_token="+token;
        String contentJson = HttpUtil.get(contentApi);
        JSONArray array = JSONUtil.parseArray(contentJson);
        for (Object o : array) {
            JSONObject obj = (JSONObject) o;
            String type = obj.getStr("type");
            if ("dir".equals(type)){
                String dirPath = obj.getStr("path");
                removeDirOnGiteeRepo(dirPath, repo, owner, token, msg);
            }else if ("file".equals(type)){
                String filePath = obj.getStr("path");
                String sha = obj.getStr("sha");
                String deleteApi = "https://gitee.com/api/v5/repos/"+owner+"/"+repo+"/contents/"+filePath;
                Map<String,Object> params = new HashMap<>(3);
                params.put("access_token",token);
                params.put("sha",sha);
                params.put("message",msg);
                HttpResponse response = jcrUtils.executeUrlBackResponse(deleteApi, params, "DELETE");
                if (response.getStatus() != 200) {
                    System.out.println(filePath+" 删除失败!");
                    throw new RuntimeException("文件删除失败!");
                }
            }
        }

    }

    /**
     * 重命名文件夹
     *
     * @param absolute 文件夹的绝对路径
     * @param newName  文件夹新名称
     * @param api      实际远程操作的api
     * @param params   请求参数
     * @param method   请求方式
     * @return 是否重命名成功
     */
    @Override
    public boolean renameDirectory(String absolute, String newName, String api, Map<String, Object> params, String method) {
        return Jcr.super.renameDirectory(absolute, newName, api, params, method);
    }

    /**
     * 获取绝对路径文件夹下的文件或文件夹
     *
     * @param absolute 文件夹的绝对路径
     * @return 文件或文件夹信息列表
     */
    @Override
    public List<JcrContentTreeDto> getDirectoryFiles(String absolute) {
        return jcrUtils.getContentTreeOfNodeByAbsPath(absolute);
    }


    /**
     * 添加文件
     *
     * @param api         实际远程操作的api
     * @param params      请求参数
     * @param method      请求方式
     * @return 是否添加成功
     */
    @Override
    public boolean addFile(String api, Map<String, Object> params, String method) {
        String username = (String) params.get("user");
        String repo = (String) params.get("repo");
        String owner = (String) params.get("owner");
        String token = (String) params.get("access_token");
        String path = (String) params.get("path");
        if (path.startsWith("/")){
            path = path.substring(1);
        }
        params.remove("user");
        params.remove("repo");
        params.remove("owner");
        HttpResponse response = jcrUtils.executeUrlBackResponse(api, params, method);
        if (response.getStatus() == 201){
            String contentApi = "https://gitee.com/api/v5/repos/"+owner+"/"+repo+"/contents/"
                    +path+"?access_token="+token;
            String contentJson = HttpUtil.get(contentApi);
            TreeDto treeDto = JSONUtil.toBean(contentJson, TreeDto.class);
            treeDto.setOwner(owner);
            String absolute = "/"+username+"/"+repo+"/"+treeDto.getPath();
            return jcrUtils.addPropertyOnNodeByAbsPath(treeDto.getName(), JSONUtil.toJsonStr(treeDto), absolute);
        }else if (response.getStatus() == 401){
            throw new RuntimeException("没有权限,请检查token");
        }
        return false;
    }

    /**
     * 添加文件
     *
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename    文件名
     * @param content     文件源
     * @param api         实际远程操作的api
     * @param params      请求参数
     * @param method      请求方式
     * @return 是否添加成功
     */
    @Override
    public boolean addFile(String dirAbsolute, String filename, Object content, String api, Map<String, Object> params, String method) {
        return Jcr.super.addFile(dirAbsolute, filename, content, api, params, method);
    }

    /**
     * 删除文件
     *
     * @param path    文件路径
     * @param params 自定义参数
     * @return 是否删除成功
     */
    @Override
    public boolean deleteFile(String path, Map<String, Object> params) {
        String user = (String) params.get("user");
        String repo = (String) params.get("repo");
        String token = (String) params.get("token");
        String owner = getOwnerByToken(token);
        if (path.startsWith("/")){
            path = path.substring(1);
        }
        String contentApi = "https://gitee.com/api/v5/repos/"+owner+"/"+repo+"/contents/"
                +path+"?access_token="+token;
        String json = HttpUtil.get(contentApi);
        String sha = JSONUtil.parseObj(json).getStr("sha");
        String deleteApi = "https://gitee.com/api/v5/repos/"+owner+"/"+repo+"/contents/"+path;
        params.remove("user");
        params.remove("repo");
        params.remove("token");
        params.put("access_token",token);
        params.put("sha",sha);
        String name = path.substring(path.lastIndexOf("/")+1);
        params.put("message","删除文件:"+name+" "+DateConverter.getNowMonthAndDay());
        HttpResponse response = jcrUtils.executeUrlBackResponse(deleteApi, params, "DELETE");
        if (response.getStatus() == 200) {
            String repository = "/"+user+"/"+repo+"/"+path;
            return jcrUtils.removeItemByAbsPath(repository);
        }else if (response.getStatus() == 401){
            throw new RuntimeException("没有权限删除文件");
        }
        return false;
    }

    /**
     * 重命名文件
     *
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param oldName     旧文件名
     * @param newName     新文件名
     * @param api         实际远程操作的api
     * @param params      请求参数
     * @param method      请求方式
     * @return 是否重命名成功
     */
    @Override
    public boolean renameFile(String dirAbsolute, String oldName, String newName, String api, Map<String, Object> params, String method) {
        return Jcr.super.renameFile(dirAbsolute, oldName, newName, api, params, method);
    }

    /**
     * 更新文件内容
     *
     * @param content 新文件源
     * @param api     实际远程操作的api
     * @param params  请求参数
     * @param method  请求方式
     * @return 是否更新成功
     */
    @Override
    public boolean updateFile(InputStream content, String api, Map<String, Object> params, String method) {
        return Jcr.super.updateFile(content, api, params, method);
    }

    /**
     * 更新文件内容
     *
     * @param content 新文件源
     * @param api     实际远程操作的api
     * @param params  请求参数
     * @param method  请求方式
     * @return 是否更新成功
     */
    @Override
    public boolean updateFile(String content, String api, Map<String, Object> params, String method) {
        return Jcr.super.updateFile(content, api, params, method);
    }

    /**
     * 更新文件内容
     *
     * @param content 新文件源
     * @param api     实际远程操作的api
     * @param params  请求参数
     * @param method  请求方式
     * @return 是否更新成功
     */
    @Override
    public boolean updateFile(Object content, String api, Map<String, Object> params, String method) {
        return Jcr.super.updateFile(content, api, params, method);
    }

    /**
     * 获取文件
     *
     * @param absolute  文件绝对路径
     * @param beanClass 用于接收文件信息的实体类型
     * @return 文件
     */
    @Override
    public <T> T getFile(String absolute, Class<T> beanClass) {
        String json = jcrUtils.getStringPropertyByAbsPath(absolute);
        return JSONUtil.toBean(json, beanClass);
    }
}

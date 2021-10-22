package com.graduation.repo.adapter;

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

    @Autowired
    private JcrUtils jcrUtils;


//    public List<RepoInfoVo> initializeRepository(String user, String api, Map<String, Object> params, String method) {
//        String json = jcrUtils.executeUrl(api, params, method);
//        if (StringUtils.isNotBlank(json)) {
//            JSONArray jsonArray = JSONUtil.parseArray(json);
//            JSONObject obj;
//            List<RepoInfoVo> list = new ArrayList<>();
//            List<String> repos = new ArrayList<>();
//            for (Object o : jsonArray) {
//                obj = (JSONObject) o;
//                repos.add(obj.getStr("path"));
//                list.add(new RepoInfoVo(
//                        obj.getStr("name"), obj.getStr("path"), obj.getStr("full_name"),
//                        obj.getStr("project_creator"), obj.getStr("html_url"), obj.getStr("description"),
//                        obj.getBool("public"), obj.getStr("default_branch"), DateConverter.getFormatDate(obj.getDate("created_at")),
//                        DateConverter.getFormatDate(obj.getDate("pushed_at")), DateConverter.getFormatDate(obj.getDate("updated_at"))));
//            }
//            repos.forEach(r -> jcrUtils.addNodeOnRootByAbsPath("/" +user+"/"+r));
//            List<String> names = jcrUtils.getAllNodeNamesOnNodeByAbs("/" + user);
//            System.out.println(names);
//            return list;
//        }
//        return null;
//    }


    /**
     * 初始化仓库  一般授权后执行 初始化已授权的仓库信息到jcr中
     *
     * @param repository 初始化的jcr仓库名称  这里设定为登录用户的用户+/+当前远程仓库名称
     * @param api    实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否初始化成功
     */
    @Override
    public boolean initializeRepository(String repository,String api, Map<String, Object> params, String method) {
        String json = jcrUtils.executeUrl(api, params, method);
        if (StringUtils.isNotBlank(json)){
            String prefix = "/"+repository+"/";
            ContentTreeDto fileTreeDto = JSONUtil.toBean(json, ContentTreeDto.class);
            List<TreeDto> tree = fileTreeDto.getTree();
               if (tree!=null){
                   tree.forEach(t->{
                       if (!"".equals(t.getPath())&&t.getIs_dir()){
                           jcrUtils.addNodeOnRootByAbsPath(prefix+t.getPath());
                       }else {
                           jcrUtils.addPropertyOnNodeByAbsPath(t.getName(),JSONUtil.toJsonStr(t),prefix+t.getPath());
                       }
                   });
                   return true;
               }
        }
        return false;
    }

    /**
     * 通过token 获取 远程仓库用户名
     * @param token token
     * @return 远程仓库用户名
     */
    public String getOwnerByToken(String token){
        String api = "https://gitee.com/api/v5/user?access_token="+token;
        String json = HttpUtil.get(api);
        if (StringUtils.isNotBlank(json)){
            return JSONUtil.parseObj(json).getStr("login");
        }
        return null;
    }

    /**
     * 获取仓库最近一次更新时间 是否比现在早   判断是否为最新的
     * @param api    实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 仓库是否为最新状态
     */
    public boolean isTheLast(String api,Map<String, Object> params,String method){
        String json = jcrUtils.executeUrl(api, params, method);
        JSONArray jsonArray = JSONUtil.parseArray(json);
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        String utcTime = jsonObject.getStr("created_at");
        return DateConverter.isUtcTimeIsEarlierThanNow(utcTime);
    }

    /**
     * 获取用户的所有授权仓库名称
     * @param user 用户名
     * @return 仓库名
     */
    public List<String> getAllRepoNames(String user){
        return jcrUtils.getAllNodeNamesOnNodeByAbs("/"+user);
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
        boolean isAdded = jcrUtils.addNodeOnRootByAbsPath(absolute);
        if (isAdded) {
            jcrUtils.addPropertyOnNodeByAbsPath(".keep", " ", absolute);
            return StringUtils.isNotBlank(jcrUtils.executeUrl(api, params, method));
        }
        return false;
    }

    /**
     * 删除文件夹
     *
     * @param absolute 文件夹的绝对路径
     * @param api      实际远程操作的api
     * @param params   请求参数
     * @param method   请求方式
     * @return 是否删除成功
     */
    @Override
    public boolean removeDirectory(String absolute, String api, Map<String, Object> params, String method) {
        // 删除文件夹需保证文件夹里没有文件  包括其子文件夹  当一个文件夹没有文件时 会默认把文件夹删除
        // 所以只需把文件夹下所有文件删除即可
        return Jcr.super.removeDirectory(absolute, api, params, method);
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
     * @param absolute  文件夹的绝对路径
     * @return 文件或文件夹信息列表
     */
    @Override
    public List<JcrContentTreeDto> getDirectoryFiles(String absolute) {
        return  jcrUtils.getContentTreeOfNodeByAbsPath(absolute);
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
    public boolean addFile(String dirAbsolute, String filename, InputStream content, String api, Map<String, Object> params, String method) {
        return Jcr.super.addFile(dirAbsolute, filename, content, api, params, method);
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
    public boolean addFile(String dirAbsolute, String filename, String content, String api, Map<String, Object> params, String method) {
        return Jcr.super.addFile(dirAbsolute, filename, content, api, params, method);
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
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename    文件名
     * @param api         实际远程操作的api
     * @param params      请求参数
     * @param method      请求方式
     * @return 是否删除成功
     */
    @Override
    public boolean deleteFile(String dirAbsolute, String filename, String api, Map<String, Object> params, String method) {
        return Jcr.super.deleteFile(dirAbsolute, filename, api, params, method);
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
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename    文件名
     * @param beanClass   用于接收文件信息的实体类型
     * @return 文件
     */
    @Override
    public <T> T getFile(String dirAbsolute, String filename, Class<T> beanClass) {
        return Jcr.super.getFile(dirAbsolute, filename, beanClass);
    }
}

package com.graduation.jcr.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.jcr.model.dto.JcrContentTreeDto;
import com.graduation.utils.FileSizeConverter;
import org.apache.jackrabbit.oak.Oak;
import org.apache.jackrabbit.oak.jcr.Jcr;
import org.apache.jackrabbit.value.BinaryImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.jcr.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description 用于进行jcr管理的工具类 此工具类放入spring容器进行管理
 *
 * @author shaoming
 * @date 2021/9/30 10:19
 * @since 1.0
 */
@Component
public class JcrUtils {

    private static final String[] IGNORES = {"/jcr:", "/rep:", "/oak:", ".last-", ".keep"};

    private static final String SEPARATOR = "/";

    private static final String GTE_METHOD = "get";

    private static final String POST_METHOD = "post";

    private static final String PUT_METHOD = "put";

    private static final String DELETE_METHOD = "delete";

    private Session session;

    private List<String> allFilesPath = new ArrayList<>();
    private List<String> allDirsPath = new ArrayList<>();

    /**
     * 初始化构造session  默认本地jcr认证id密码均为admin
     */
    public JcrUtils() {
        try {
            session = new Jcr(new Oak()).createRepository().login(new SimpleCredentials("admin", "admin".toCharArray()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 是否为过滤路径
     *
     * @param filePath 文件路径
     * @return 是否为过滤路径
     */
    private static boolean isIgnores(String filePath) {
        for (String ignore : IGNORES) {
            if (filePath.startsWith(ignore)) {
                return true;
            } else {
                if (filePath.contains(ignore)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 保存操作  新增node或property时均需进行保存操作  也可以在所有添加操作完成后再进行此操作
     */
    public void save() {
        try {
            this.session.save();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过文件名判断jcr中是否存在该文件 当节点数量较多时不建议使用 效率较低  bug  待改为递归
     *
     * @param filename 文件名
     * @return 是否存在该文件
     */
    @Deprecated
    public boolean hasProperty(String filename) {
        try {
            Node rootNode = getRootNode();
            if (rootNode.hasProperty(rootNode.getPath() + "" + filename)) {
                return true;
            } else {
                if (rootNode.hasNodes()) {
                    NodeIterator nodes = rootNode.getNodes();
                    while (nodes.hasNext()) {
                        Node node = nodes.nextNode();
                        if (node.hasProperty(node.getPath() + "" + filename)) {
                            return true;
                        }
                    }
                }
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 通过绝对路径判断是否存在该文件
     *
     * @param absPath 文件绝对路径
     * @return 是否存在该文件
     */
    public boolean hasPropertyByAbsPath(String absPath) {
        try {
            return session.getProperty(absPath) != null;
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过绝对路径判断是否存在该文件
     *
     * @param absPath 文件绝对路径
     * @return 是否存在该文件
     */
    public boolean hasItemOnNodeByAbsPath(String absPath) {
        try {
            if (session.nodeExists(absPath)) {
                Node node = session.getNode(absPath);
                return node.hasNodes() || node.hasProperties();
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取指定路径下的文件及文件夹
     *
     * @param absPath 绝对路径
     * @return 文件及文件夹
     */
    public List<JcrContentTreeDto> getContentTreeOfNodeByAbsPath(String repo,String absPath) {
        try {
            if (session.nodeExists(absPath)) {
                List<JcrContentTreeDto> list = new ArrayList<>();
                Node node = session.getNode(absPath);
                if (node.hasNodes()) {
                    NodeIterator nodes = node.getNodes();
                    while (nodes.hasNext()) {
                        Node n = nodes.nextNode();
                        String name = n.getName();
                        String path = n.getPath();
                        if (!isIgnores(path)) {
                            list.add(new JcrContentTreeDto(repo,path, name, 0L, "0B", true));
                        }
                    }
                }
                if (node.hasProperties()) {
                    PropertyIterator properties = node.getProperties();
                    while (properties.hasNext()) {
                        Property p = properties.nextProperty();
                        String path = p.getPath();
                        if (!isIgnores(path)) {
                            String name = p.getName();
                            String json = p.getString();
                            JSONObject jsonObject = JSONUtil.parseObj(json);
                            Long size = jsonObject.getLong("size");
                            String file_size = jsonObject.getStr("file_size");
                            list.add(new JcrContentTreeDto(repo,path, name, size, file_size, false));
                        }

                    }
                }
                return list;
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过文件绝对路径获取文件的输入流
     *
     * @param absPath 文件绝对路径
     * @return 文件对应输入流
     */
    public InputStream getFileByAbsPath(String absPath) {
        try {
            return session.getProperty(absPath).getValue().getBinary().getStream();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过绝对路径获取字符串
     *
     * @param absPath 绝对路径
     * @return 字符串
     */
    public String getStringPropertyByAbsPath(String absPath) {
        try {
            if (session.propertyExists(absPath)) {
                return session.getProperty(absPath).getString();
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取头节点
     *
     * @return 头节点
     */
    public Node getRootNode() {
        try {
            return session.getRootNode();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNodeStringPropertyByAbs(String absPath, String repo) {
        try {
            Node node;
            if (session.nodeExists(absPath)) {
                node = session.getNode(absPath);
                node.getProperty(".");
            } else {
                return null;
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定路径下的所有一级文件夹名称
     *
     * @param absPath 指定路径
     * @return 所有一级文件夹名称
     */
    public List<String> getAllNodeNamesOnNodeByAbs(String absPath) {
        try {
            Node node;
            if (session.nodeExists(absPath)) {
                node = session.getNode(absPath);
            } else {
                boolean isAdded = addNodeOnRootByAbsPath(absPath);
                if (isAdded) {
                    node = session.getNode(absPath);
                } else {
                    node = null;
                }
            }
            assert node != null;
            if (node.hasNodes()) {
                List<String> list = new ArrayList<>();
                NodeIterator nodes = node.getNodes();
                while (nodes.hasNext()) {
                    String name = nodes.nextNode().getName();
                    if (!isIgnores(name)) {
                        list.add(name);
                    }
                }
                return list;
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过节点的绝对路径获取节点
     *
     * @param absPath 节点绝对路径
     * @return 该节点
     */
    public Node getNodeByAbsPath(String absPath) {
        try {
            if (session.nodeExists(absPath)) {
                return session.getNode(absPath);
            } else {
                addNodeOnRootByAbsPath(absPath);
                return session.getNode(absPath);
            }
        } catch (RepositoryException e) {
            addNodeOnRootByAbsPath(absPath);
            try {
                return session.getNode(absPath);
            } catch (RepositoryException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取仓库里面所有节点或属性的绝对路径
     *
     * @return 绝对路径列表
     */
    public List<String> getAllFilesPathInfo() {
        try {
            Node rootNode = getRootNode();
            allFilesPath.clear();
            getFilesPath(rootNode);
            return allFilesPath;
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取仓库里面所有节点或属性的绝对路径
     *
     * @return 绝对路径列表
     */
    public List<String> getAllDirsPathInfo() {
        try {
            Node rootNode = getRootNode();
            allDirsPath.clear();
            getDirPaths(rootNode);
            return allDirsPath;
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取仓库里面指定节点下所有节点或属性的绝对路径
     *
     * @return 绝对路径列表
     */
    public List<String> getAllFilesPathInfoOfNode(Node node) {
        try {
            allFilesPath.clear();
            getFilesPath(node);
            return allFilesPath;
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取仓库里面所有节点或属性的绝对路径
     *
     * @return 绝对路径列表
     */
    public List<String> getAllDirsPathInfoOfNode(Node node) {
        try {
            allDirsPath.clear();
            getDirPaths(node);
            return allDirsPath;
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 递归获取节点下所有文件绝对路径
     *
     * @param node 当前节点
     * @throws RepositoryException 仓库异常
     */
    private void getFilesPath(Node node) throws RepositoryException {
        if (!isIgnores(node.getPath())) {
            if (node.hasNodes()) {
                NodeIterator nodes = node.getNodes();
                while (nodes.hasNext()) {
                    getFilesPath(nodes.nextNode());
                }
            }
            if (node.hasProperties()) {
                PropertyIterator properties = node.getProperties();
                Property property;
                String path;
                while (properties.hasNext()) {
                    property = properties.nextProperty();

                    path = property.getPath();
                    if (!isIgnores(path)) {
                        allFilesPath.add(path);
                    }
                }
            }
        }
    }


    /**
     * 递归获取节点下所有文件夹绝对路径
     *
     * @param node 当前节点
     * @throws RepositoryException 仓库异常
     */
    private void getDirPaths(Node node) throws RepositoryException {
        if (!isIgnores(node.getPath())) {
            if (node.hasNodes()) {
                NodeIterator nodes = node.getNodes();
                while (nodes.hasNext()) {
                    getDirPaths(nodes.nextNode());
                }
            }
            String path;
            path = node.getPath();
            if (!isIgnores(path)) {
                allDirsPath.add(path);
            }
        }
    }

    /**
     * 在根节点处添加下级节点 (只能添加一级节点)
     *
     * @param name 节点名称
     * @return 是否添加成功
     */
    public boolean addNodeOnRoot(String name) {
        try {
            return getRootNode().addNode(name) != null;
        } catch (RepositoryException e) {
            e.printStackTrace();
        } finally {
            this.save();
        }
        return false;
    }

    /**
     * 通过绝对路径添加多级目录
     *
     * @param absPath 绝对路径
     */
    public boolean addNodeOnRootByAbsPath(String absPath) {
        if (absPath.startsWith(SEPARATOR)) {
            String[] nodes = absPath.split(SEPARATOR);
            String prePath = "/";
            Node preNode = null;
            StringBuilder nowPath = new StringBuilder();
            for (int i = 1; i < nodes.length; i++) {
                try {
                    preNode = session.getNode(prePath);
                } catch (RepositoryException e) {
                    try {
                        assert preNode != null;
                        preNode.addNode(nodes[i]);
                    } catch (RepositoryException ex) {
                        ex.printStackTrace();
                    }
                }
                nowPath.append(SEPARATOR).append(nodes[i]);
                try {
                    if (!session.nodeExists(nowPath.toString())) {
                        preNode.addNode(nodes[i]);
                    }
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
                prePath = nowPath.toString();
            }
        }
        try {
            return session.nodeExists(absPath);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 在指定节点处添加下级节点
     *
     * @param name 节点名称
     * @param node 当前节点
     * @return 是否添加成功
     */
    public boolean addNodeOnNode(String name, Node node) {
        try {
            if (node != null) {
                if (node.getPath().startsWith(getRootNode().getPath())) {
                    return node.addNode(name) != null;
                }
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        } finally {
            this.save();
        }
        return false;
    }


    /**
     * 用于自动判断通过节点还是绝对路径 去添加属性( 属性也自动判断类型)
     *
     * @param pName 属性名
     * @param p     属性 类型可以是Value String Integer Boolean InputStream
     * @param n     节点或者节点的绝对路径
     * @return 是否添加成功
     */
    private boolean autoAddProperties(String pName, Object p, Object n) {
        try {
            if (n != null) {
                Node node;
                if (n instanceof Node) {
                    node = (Node) n;
                } else {
                    String absPath = (String) n;
                    node = getNodeByAbsPath(absPath);
                }
                if (p instanceof Value) {
                    Value v = (Value) p;
                    return node.setProperty(pName, v) != null;
                } else if (p instanceof String) {
                    String s = (String) p;
                    return node.setProperty(pName, s) != null;
                } else if (p instanceof Integer) {
                    Integer i = (Integer) p;
                    return node.setProperty(pName, i) != null;
                } else if (p instanceof Boolean) {
                    Boolean b = (Boolean) p;
                    return node.setProperty(pName, b) != null;
                } else if (p instanceof InputStream) {
                    InputStream in = (InputStream) p;
                    return node.setProperty(pName, new BinaryImpl(in)) != null;
                }
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        } finally {
            this.save();
        }
        return false;
    }

    /**
     * 在某个节点上添加属性
     *
     * @param pName 属性名称
     * @param p     属性 类型可以是Value String Integer Boolean InputStream
     * @param node  节点
     * @return 是否添加成功
     */
    public boolean addPropertyOnNode(String pName, Object p, Node node) {
        if (node != null) {
            return autoAddProperties(pName, p, node);
        }
        return false;
    }

    /**
     * 通过节点的绝对路径添加属性
     *
     * @param pName   属性名称
     * @param p       属性 类型可以是Value String Integer Boolean InputStream
     * @param absPath 节点的绝对路径
     * @return 是否添加成功
     */
    public boolean addPropertyOnNodeByAbsPath(String pName, Object p, String absPath) {
        try {
            if (StringUtils.hasText(absPath)) {
                if (absPath.startsWith(getRootNode().getPath())) {
                    return autoAddProperties(pName, p, absPath);
                }
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 在某个节点上移除某个属性
     *
     * @param pName 属性名
     * @param node  指定节点
     * @return 是否移除成功
     */
    public boolean removePropertyOnNode(String pName, Node node) {
        try {
            if (node != null) {
                if (node.hasProperty(pName)) {
                    node.getProperty(pName).remove();
                    return true;
                }
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        } finally {
            this.save();
        }
        return false;
    }

    /**
     * 通过节点或文件的绝对路径进行移除 如果是节点则会移除该节点下的全部子节点或属性
     *
     * @param absPath 节点或文件的绝对路径
     * @return 是否移除成功
     */
    public boolean removeItemByAbsPath(String absPath) {
        try {
            session.removeItem(absPath);
            return true;
        } catch (RepositoryException e) {
            e.printStackTrace();
        } finally {
            this.save();
        }
        return false;
    }

    /**
     * 重命名节点   采用workspace move来移动节点及其子图到指定的新位置  workspace move不能用于单个属性
     *
     * @param absPath 原节点的绝对路径
     * @param newName 节点的新名称
     * @return 是否修改成功
     * @throws RepositoryException 仓库异常
     */
    public boolean renameNodeByAbsPath(String absPath, String newName) throws RepositoryException {
        String newPath = absPath.substring(0, absPath.lastIndexOf("/") + 1) + newName;
        session.getWorkspace().move(absPath, newPath);
        return session.getNode(newPath) != null;
    }

    /**
     * 重命名属性
     *
     * @param nodeAbsPath 该属性所在节点的绝对路径
     * @param oldName     原属性名
     * @param newName     新属性名
     * @return 是否更改成功
     * @throws RepositoryException 仓库异常
     */
    public boolean renamePropertyByAbsPath(String nodeAbsPath, String oldName, String newName) throws RepositoryException {
        Node node = session.getNode(nodeAbsPath);
        Property property = session.getProperty(nodeAbsPath + "/" + oldName);
        Property newProperty = node.setProperty(newName, property.getValue());
        property.remove();
        return newProperty != null;
    }


    /** ------------------------ 对外适配器使用API ----------------------------*/

    /**
     * 通过节点绝对路径  添加 json 到某个节点下
     *
     * @param pName      属性名
     * @param objectJson 对象json
     * @param absPath    节点绝对路径
     * @return 是否添加成功
     * @throws RepositoryException 仓库异常
     */
    public boolean addPropertiesOnNode(String pName, String objectJson, String absPath) throws RepositoryException {
        Node node = session.getNode(absPath);
        if (node != null) {
            return node.setProperty(pName, objectJson) != null;
        }
        return false;
    }

    /**
     * 添加 json 到某个节点下
     *
     * @param pName      属性名
     * @param objectJson 对象json
     * @param node       节点
     * @return 是否添加成功
     * @throws RepositoryException 仓库异常
     */
    public boolean addPropertiesOnNode(String pName, String objectJson, Node node) throws RepositoryException {
        if (node != null) {
            return node.setProperty(pName, objectJson) != null;
        }
        return false;
    }

    /**
     * 将json格式的字符串转为实体类
     *
     * @param objectJson json字符串
     * @param beanClass  实体类型
     * @param <T>        Bean类型
     * @return Bean对象
     */
    public <T> T toBean(String objectJson, Class<T> beanClass) {
        return JSONUtil.toBean(objectJson, beanClass);
    }


    /**
     * 用于执行远程操作的方法
     *
     * @param url    远程操作api
     * @param params 参数
     * @param method 方式
     * @return 执行结果json
     */
    public String executeUrl(String url, Map<String, Object> params, String method) {
        if (GTE_METHOD.equalsIgnoreCase(method)) {
            return HttpUtil.get(url, params);
        } else if (POST_METHOD.equalsIgnoreCase(method)) {
            return HttpUtil.post(url, params);
        } else if (PUT_METHOD.equalsIgnoreCase(method)) {
            return HttpRequest.put(url).form(params).execute().body();
        } else if (DELETE_METHOD.equalsIgnoreCase(method)) {
            return HttpRequest.delete(url).form(params).execute().body();
        }
        return null;
    }


    public HttpResponse executeUrlBackResponse(String url, Map<String, Object> params, String method) {
        if (GTE_METHOD.equalsIgnoreCase(method)) {
            return HttpRequest.get(url).form(params).execute();
        } else if (POST_METHOD.equalsIgnoreCase(method)) {
            return HttpRequest.post(url).form(params).execute();
        } else if (PUT_METHOD.equalsIgnoreCase(method)) {
            return HttpRequest.put(url).form(params).execute();
        } else if (DELETE_METHOD.equalsIgnoreCase(method)) {
            return HttpRequest.delete(url).form(params).execute();
        }
        return null;
    }


}

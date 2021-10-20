package com.graduation.utils;

import cn.hutool.json.JSONUtil;
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

/**
 * Description 用于进行jcr管理的工具类 此工具类放入spring容器进行管理
 *
 * @author shaoming
 * @date 2021/9/30 10:19
 * @since 1.0
 */
@Component
public class JcrUtils {

    private static final String[] IGNORES = {"/jcr:", "/rep:", "/oak:"};

    private static final String SEPARATOR = "/";

    private Session session;

    private List<String> allFilePaths = new ArrayList<>();

    /**
     * 初始化构造session  默认本地jcr认证id密码均为admin
     */
    public JcrUtils() {
        try {
            session = new Jcr(new Oak()).createRepository().login(new SimpleCredentials("admin", "admin".toCharArray()));
        } catch (RepositoryException e) {
            e.printStackTrace();
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
    public List<String> getAllPathsInfo() {
        try {
            Node rootNode = getRootNode();
            allFilePaths.clear();
            getFilePaths(rootNode);
            return allFilePaths;
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
    public List<String> getAllPathsInfoOfNode(Node node) {
        try {
            allFilePaths.clear();
            getFilePaths(node);
            return allFilePaths;
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void getFilePaths(Node node) throws RepositoryException {
        if (!isIgnores(node.getPath())) {
            if (node.hasNodes()) {
                NodeIterator nodes = node.getNodes();
                while (nodes.hasNext()) {
                    getFilePaths(nodes.nextNode());
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
                        allFilePaths.add(path);
                    }
                }
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

    public void addNodeOnRootByAbsPath(String absPath) {
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
    public boolean removePropertyByAbsPath(String absPath) {
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


    /** ------------------------ 对外适配器使用API ----------------------------*/

    /**
     * 添加 json 到某个节点下
     * @param pName 属性名
     * @param objectJson 对象json
     * @param node 节点
     * @return 是否添加成功
     * @throws RepositoryException 仓库异常
     */
    public boolean addPropertiesOnNode(String pName,String objectJson,Node node) throws RepositoryException {
        if (node !=null){
            return node.setProperty(pName, objectJson) != null;
        }
        return false;
    }

    /**
     * 将json格式的字符串转为实体类
     * @param objectJson json字符串
     * @param beanClass 实体类型
     * @param <T> Bean类型
     * @return Bean对象
     */
    public <T> T toBean(String objectJson,Class<T> beanClass){
        return JSONUtil.toBean(objectJson, beanClass);
    }


    


}

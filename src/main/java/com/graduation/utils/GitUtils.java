package com.graduation.utils;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description 操作git仓库的工具类
 * // https://gitee.com/shaoming123/file-management-service-system.git
 *
 * @author shaoming
 * @date 2021/8/31 13:33
 * @since 1.0
 */
public class GitUtils {

    public static String clone = "https://gitee.com/shaoming123/JavaReview.git";

    /**
     * 通过公开或者开源的git url pull 仓库代码
     *
     * @param cloneUrl git url
     * @return 文件夹文件路径
     */
    public static List<String> pullFilesFromGit(String cloneUrl) {
        String dirName = cloneUrl.substring(cloneUrl.lastIndexOf("/") + 1, cloneUrl.lastIndexOf("."));
        File tmpDir = new File(Constant.OUTPUT_TMP_FILE_PATH + dirName);
        if (tmpDir.exists()) {
            tmpDir.delete();
        }
        try {
            Git.cloneRepository().setDirectory(tmpDir).setURI(cloneUrl).call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return getAllFilePathFromDirectory(tmpDir.getPath());
    }


    public static List<String> getAllFilePathFromDirectory(String path) {
        List<String> list = new ArrayList<>();
        File fileDir = new File(path);
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                if (files[i].isDirectory() && !".git".equals(files[i].getName())) {
                    path = files[i].getPath();
                    list.addAll(getAllFilePathFromDirectory(path));
                } else {
                    if (!".git".equals(files[i].getName())) {
                        list.add(files[i].getPath());
                    }
                }
            }
        }
        return list;
    }

    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            assert children != null;
            for (String child : children) {
                deleteDir(new File(dir, child));
            }
        }
      dir.delete();
    }

    public static void main(String[] args) {
//        pullFilesFromGit("");
        List<String> list = getAllFilePathFromDirectory(Constant.OUTPUT_TMP_FILE_PATH + "clone");
        list.forEach(System.out::println);
        System.out.println(list.size());
    }
}

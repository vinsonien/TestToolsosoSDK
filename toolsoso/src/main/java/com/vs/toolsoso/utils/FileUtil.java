package com.vs.toolsoso.utils;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;

/**
 * @author: S
 * @date: 2019/1/15 15:14
 * @description:
 */
public class FileUtil {

    /**
     * 获取文件大小
     * @param fileOrPath
     * @return
     */
    public static String GetFileSize(Object fileOrPath){
        if (fileOrPath instanceof String) {
            return FileUtils.getFileSize((String) fileOrPath);
        }else if (fileOrPath instanceof File){
            return FileUtils.getFileSize((File) fileOrPath);
        }
        return null;
    }

    /**
     * 获取文件长度
     * @param fileOrPath
     * @return
     */
    public static long GetFileLong(Object fileOrPath){
        if (fileOrPath instanceof String) {
            return FileUtils.getFileLength((String) fileOrPath);
        }else if (fileOrPath instanceof File){
            return FileUtils.getFileLength((File) fileOrPath);
        }
        return 0;
    }

    /**
     * 获取全路径中的文件名
     * @param fileOrPath
     * @return
     */
    public static String GetFileName(Object fileOrPath){
        if (fileOrPath instanceof String) {
            return FileUtils.getFileName((String) fileOrPath);
        }else if (fileOrPath instanceof File){
            return FileUtils.getFileName((File) fileOrPath);
        }
        return null;
    }

    /**
     * 删除文件
     */
    public static boolean DeleteFile(String filePath) {
        File file = new File(filePath);
        boolean isOk = false;
        if (file.isFile() && file.exists())
            isOk = file.delete(); // 删除文件
        return isOk;
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     * @param fileOrPath
     * @return
     */
    public static boolean CreateOrExistsFile(Object fileOrPath){
        if (fileOrPath instanceof String) {
            return FileUtils.createOrExistsFile((String) fileOrPath);
        }else if (fileOrPath instanceof File){
            return FileUtils.createOrExistsFile((File) fileOrPath);
        }
        return false;
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     * @param fileOrPath
     * @return
     */
    public static boolean CreateOrExistsDir(Object fileOrPath){
        if (fileOrPath instanceof String) {
            return FileUtils.createOrExistsDir((String) fileOrPath);
        }else if (fileOrPath instanceof File){
            return FileUtils.createOrExistsDir((File) fileOrPath);
        }
        return false;
    }

    /**
     * 删除目录下的所有东西
     * @param fileOrPath
     * @return
     */
    public static boolean DeleteAllInDir(Object fileOrPath){
        if (fileOrPath instanceof String) {
            return FileUtils.deleteAllInDir((String) fileOrPath);
        }else if (fileOrPath instanceof File){
            return FileUtils.deleteAllInDir((File) fileOrPath);
        }
        return false;
    }
}

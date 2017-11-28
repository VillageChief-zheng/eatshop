package com.stateunion.eatshop;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by admini on 2017/11/28.
 *  * FileUtil: 文件操作辅助类
  */

public class FileUtil {

    public static final String TAG = "FileUtil";


    /**
     * 删除文件夹下所有的文件
     * @param dir 要删除的文件夹文件
     * @throws java.io.IOException 删除文件失败报错
     * @exception IllegalArgumentException 非文件夹报错
     */
    public static void deleteContents(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) {
            throw new IllegalArgumentException("这不是文件夹: " + dir);
        }
        for (File file : files) {
            if (file.isDirectory()) {
                deleteContents(file);
            }
            if (!file.delete()) {
                throw new IOException("删除文件失败: " + file);
            }
        }
    }


    /**
     * 关闭输入流
     */
    public static void closeInputStream(InputStream inputStream){

        if (inputStream != null)
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "关闭流", e);
            }

    }

    /**
     * 关闭输出流
     */
    public static  void closeOutputStream(OutputStream outputStream) {

        if (outputStream != null)
            try {
                outputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "关闭流", e);
            }
    }
    }

package cn.edu.hust.utils;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by lxiao on 2017/2/19.
 * 压缩文件工具类
 */
public class ZipUtil {

    /**
     * 文件压缩
     * @param srcFileList  待压缩文件
     * @param outputStream 输出流
     */
    public static void doCompress(List<String> srcFileList, OutputStream outputStream) throws IOException {
        ZipOutputStream out = new ZipOutputStream(outputStream);
        for (String srcFile : srcFileList) {
            File file = new File(srcFile);
            doCompress(file, out);
        }

    }

    private static void doCompress(File file, ZipOutputStream out) throws IOException{
        if( file.exists() ){
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
            int len = 0 ;
            // 读取文件的内容,打包到zip文件
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.closeEntry();
            fis.close();
        }
    }

}

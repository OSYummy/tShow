package com.wisedu.core.utils;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-20
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
public class ArchiveUtil {
    private final static String ZIP_SUFFIX = "zip";

    private final static int BUFFER_SIZE = 1024<<3;

    private final static String SEPARATOR =System.getProperty("file.separator");

    /**
     * 打包文件为zip
     * @param to 目标文件
     * @param from 源文件
     * @param name 打包文件名
     */
    public static void convertFile2Zip(String to, String from, String name)
            throws IOException{
        File src = new File(from);
        if (!src.exists()){
            throw new IOException("file do not exist");
        }

        File dst = new File(to);
        if (!dst.exists()){
            dst.mkdirs();
        }

        File zipFile = new File(dst, name + "." + ZIP_SUFFIX);
        ZipOutputStream zos
                = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
        zos.setEncoding("utf-8");

        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int startIndex = src.getAbsolutePath().length();
            /*遍历文件*/
            LinkedList<String> files = new LinkedList<String>();
            files.add(src.getAbsolutePath());

            while (!files.isEmpty()){
                File head = new File(files.poll());
                if (!head.exists() || !head.canRead()){
                    continue;
                }

                if (head.isFile() || head.listFiles().length==0){
                    String parent
                            = head.getParent().substring(startIndex) + SEPARATOR +head.getName();
                    ZipEntry entry = null;
                    if (head.isDirectory()){
                        entry = new ZipEntry(parent.substring(SEPARATOR.length()) + SEPARATOR);
                        zos.putNextEntry(entry);
                        continue;
                    } else {
                        entry = new ZipEntry(parent.substring(SEPARATOR.length()));
                        zos.putNextEntry(entry);
                    }

                    FileInputStream fis = new FileInputStream(head);
                    BufferedInputStream bis = new BufferedInputStream(fis, BUFFER_SIZE);

                    int read = 0;
                    while((read=bis.read(buffer, 0, BUFFER_SIZE)) != -1) {
                        zos.write(buffer, 0, read);
                    }
                    bis.close();
                    zos.closeEntry();
                } else {
                    for (File file: head.listFiles()){
                        if(file.getAbsolutePath().equals(zipFile.getAbsolutePath())){
                            continue;
                        }
                        files.add(file.getAbsolutePath());
                    }
                }
            }
        } finally {
            if (zos!=null){
                zos.close();
            }
        }
    }


    /**
     * 加压文件
     * @param to 目标文件夹
     * @param from 源文件，zip格式
     */
    public static void convertZip2File(String to, String from) throws IOException{
        File src = new File(from);
        if (!src.exists() || !src.isFile() || !src.canRead()){
            throw new IOException("file do not exist or can not read");
        }

        File dst = new File(to);
        if (!dst.exists()){
            dst.mkdir();
        }

        ZipFile zipFile = new ZipFile(from);
        Enumeration<ZipEntry> entries = zipFile.getEntries();
        if (entries == null){
            return;
        }

        byte[] buffer = new byte[BUFFER_SIZE];
        while (entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            File target = new File(dst.getPath() + SEPARATOR + entry.getName());
            if (entry.isDirectory()){
                target.mkdirs();
            } else {
                if(!target.getParentFile().exists()){
                    target.getParentFile().mkdirs();
                }
                OutputStream os = new BufferedOutputStream(new FileOutputStream(target));
                InputStream is =zipFile.getInputStream(entry);
                int read = 0;
                while((read=is.read(buffer, 0, BUFFER_SIZE)) > 0) {
                    os.write(buffer, 0, read);
                }
                is.close();
                os.close();
            }
        }
    }
}

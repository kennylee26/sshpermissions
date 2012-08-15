/**   
  * @Title: CompressFile.java 
  * @Package com.tgyt.common.tools.util 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-11 下午02:49:26 
  * @version V1.0   
  */

package com.tgyt.common.tools.util;

import java.io.File;   
import java.io.FileInputStream;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.io.InputStream;   
import java.util.Enumeration;   
import org.apache.tools.zip.ZipEntry;   
import org.apache.tools.zip.ZipFile;   
import org.apache.tools.zip.ZipOutputStream;   
  
/** 
 * @ClassName: CompressFile 
 * @Description: 压缩文件和解压文件
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-11 下午02:49:26 
 *  
 */

public class CompressFile {   
    private static CompressFile instance = new CompressFile();   
       
    private CompressFile() {   
    }   
       
    public static CompressFile getInstance() {   
        return instance;   
    }   
  
    public synchronized void zip(String inputFilename, String zipFilename)   
            throws IOException {   
        zip(new File(inputFilename), zipFilename);   
    }   
       
    public synchronized void zip(File inputFile, String zipFilename) throws IOException {   
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(   
                zipFilename));   
  
        try {   
            zip(inputFile, out, "");   
        } catch (IOException e) {   
            throw e;   
        } finally {   
            out.close();   
        }   
    }   
  
    private synchronized void zip(File inputFile, ZipOutputStream out, String base)   
            throws IOException {   
        if (inputFile.isDirectory()) {   
            File[] inputFiles = inputFile.listFiles();   
            out.putNextEntry(new ZipEntry(base + "/"));   
            base = base.length() == 0 ? "" : base + "/";   
            for (int i = 0; i < inputFiles.length; i++) {   
                zip(inputFiles[i], out, base + inputFiles[i].getName());   
            }   
  
        } else {   
            if (base.length() > 0) {   
                out.putNextEntry(new ZipEntry(base));   
            } else {   
                out.putNextEntry(new ZipEntry(inputFile.getName()));   
            }   
  
            FileInputStream in = new FileInputStream(inputFile);   
            try {   
                int c;   
                byte[] by = new byte[BUFFEREDSIZE];   
                while ((c = in.read(by)) != -1) {   
                    out.write(by, 0, c);   
                }   
            } catch (IOException e) {   
                throw e;   
            } finally {   
                in.close();   
            }   
        }   
    }   
  
    public synchronized void unzip(String zipFilename, String outputDirectory)   
            throws IOException {   
        File outFile = new File(outputDirectory);   
        if (!outFile.exists()) {   
            outFile.mkdirs();   
        }   
  
        ZipFile zipFile = new ZipFile(zipFilename);   
        Enumeration en = zipFile.getEntries();   
        ZipEntry zipEntry = null;   
        while (en.hasMoreElements()) {   
            zipEntry = (ZipEntry) en.nextElement();   
            if (zipEntry.isDirectory()) {   
                // mkdir directory   
                String dirName = zipEntry.getName();   
                dirName = dirName.substring(0, dirName.length() - 1);   
  
                File f = new File(outFile.getPath() + File.separator + dirName);   
                f.mkdirs();   
  
            } else {   
                // unzip file   
                File f = new File(outFile.getPath() + File.separator   
                        + zipEntry.getName());   
                f.createNewFile();   
                InputStream in = zipFile.getInputStream(zipEntry);   
                FileOutputStream out = new FileOutputStream(f);   
                try {   
                    int c;   
                    byte[] by = new byte[BUFFEREDSIZE];   
                    while ((c = in.read(by)) != -1) {   
                        out.write(by, 0, c);   
                    }   
                    // out.flush();   
                } catch (IOException e) {   
                    throw e;   
                } finally {   
                    out.close();   
                    in.close();   
                }   
            }   
        }   
    }   
  
    private static final int BUFFEREDSIZE = 1024;   
       
    public static void main(String[] args) {      
        CompressFile bean = new CompressFile();      
        try {      
            //bean.zip("E:\\js\\ext", "C:\\ext.zip");  
            //bean.zip("E:\\js\\xtree", "C:\\xtree.zip");  
            bean.zip("C:\\Users\\zyl\\Downloads\\easyui", "C:\\Users\\zyl\\Downloads\\easyui.zip");  
     
            //bean.unzip("C:\\Users\\zyl\\Downloads\\easyui.zip", "C:\\Users\\zyl\\Downloads\\easyui");      
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
    }    
}   


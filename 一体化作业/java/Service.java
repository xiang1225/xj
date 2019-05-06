package com.flong.codegenerator;  
  
import java.io.File;  
import java.io.FileWriter;  
  
public class FileUtils {  
    /** 
     * 把生成的文件都保存. 
     * @param path 
     * @param data 
     */  
    public static void save(String path, String data) {  
        try {  
            File file = new File(path);  
            File dir = new File(path.substring(0, path.lastIndexOf("/")));  
            if(!dir.exists()) {  
                dir.mkdirs();  
            }  
            FileWriter out = new FileWriter(file);  
            out.write(data);  
            out.flush();  
            out.close();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
}  
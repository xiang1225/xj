package com.flong.codegenerator;  
  
  
import java.util.HashMap;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.Properties;  
/*** 
 *@Author:liangjilong 
 *@Date:2015��12��5������12:25:12 
 *@Email:jilongliang@sina.com 
 *@Version:1.0 
 *@CopyRight(c)Flong Intergrity Ltd. 
 *@Description����ȡ��properties 
 */  
public class PropertiesHelper {  
  
    private static final Map<String, String> properties = new HashMap<String, String>();  
    static {  
        try {  
            Properties pps = new Properties();  
            pps.load(PropertiesHelper.class.getClassLoader().getResourceAsStream("prop/DBSource.properties"));  
            //�����ظ���ֵ.  
            for (Entry<Object, Object> entry : pps.entrySet()) {  
                properties.put(entry.getKey().toString().trim(), entry.getValue().toString().trim());  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     *ͨ��keyֵȥ��ȡֵ. 
     */  
    public static String getValueByKey(String name) {  
        return properties.get(name);  
    }  
  
}  
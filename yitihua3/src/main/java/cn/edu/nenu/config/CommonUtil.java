package cn.edu.nenu.config;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Test Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-14 12:49
 */
public class CommonUtil {

    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    public static String autoGenericCode(String code, int num) {
        String result = "";
        // 保留num的位数
        // 0 代表前面补充0
        // num 代表长度为4
        // d 代表参数为正数型
        result = String.format("%0" + num + "d", Integer.parseInt(code) + 1);
        return result;
    }

    /**
     * 存在bug，层数过多时会抛出异常
     * @param code
     * @param lenNum
     * @return
     */
    public static String getNextAutoCode(String code,int lenNum){
        int length = code.length();
        if (code.length()%lenNum>0){
            length = length + lenNum;
        }
        return autoGenericCode(code,length);
    }

    public static Resource loadFileAsResource(final String filePath) {
        //Path filePath =new File("d:\\aa1.xlsx").toPath();
        //Resource resource = new UrlResource(filePath.toUri());
        try {
            Resource resource = new UrlResource(new File(filePath).toURI());
            if (resource.exists()){
                return resource;
            }else{
                throw new ResourceNotFoundException("File not found ");
            }
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("File not found ",e);
        }
    }

    public static void main(String args[]){
        String autoCode = "000100040003";
        //String autoCode = "0";
        int length = autoCode.length();
        if (autoCode.length()%4>0){
            length = length +4;
        }
        System.out.println(autoGenericCode(autoCode,length));
    }


}

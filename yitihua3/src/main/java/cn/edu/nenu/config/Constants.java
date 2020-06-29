package cn.edu.nenu.config;

/**
 * Constants Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-21 13:13
 */
public class Constants {

    public static final int PAGE_SIZE = 5;

    public static final  String UTF_8 = "UTF-8";

    public enum Status{
        ENABLE(1,"启用"),
        DISABLE(0,"禁用");

        private final int value;
        private final String desc;

        Status(int value,String desc){
            this.value=value;
            this.desc =desc;
        }

        public int value(){return this.value;}

        public String getDesc() {
            return this.desc;
        }
    }
}

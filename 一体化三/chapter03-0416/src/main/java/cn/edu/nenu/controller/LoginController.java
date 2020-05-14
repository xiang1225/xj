package cn.edu.nenu.controller;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * LoginController Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-03-04 22:05
 */
@CommonsLog
@Controller
//@RequestMapping("/login")
public class LoginController {

    //@RequestMapping(value = {"/index","/"},method = RequestMethod.GET)
    //@RequestMapping("/index")
    //@GetMapping("/index")
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        String url = "http://localhost:8080/index";
        String url1 = "http://localhost:8080/login/index";
        log.info(url);
        return "index";
    }

    /*
     * 1. 用户点击请求链接
     * 2. controller -> URL -> Get/Post Method
     * 3. 接收参数，处理业务，形成Model
     * 4. 返回viewName， viewResolver + viewName => project相对地址 prefix + viewName + suffix
     * 5. viewName的文件中进行渲染数据
     *
     * 开发顺序： 1-> 5 (正序) 或者 5 -> 1 （逆序）
     */

    //@RequestMapping(value = "/login",method = RequestMethod.GET)
    @GetMapping("/login/{id}")
    public String loginForm(@PathVariable("id")Integer id){
        log.info("login?method=get");
        log.info(id);
        return "login";
    }

    //@RequestMapping(value = "/login",method = RequestMethod.POST)
    @PostMapping("/login")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        //@RequestBody String json,
                        //ServletRequest request,
                        Model model){
        log.info("login?method=post");
        //String username = request.getParameter("username");
        log.info(username);
        //log.info(json);
        model.addAttribute("username",username);

        //User user = new User();
        //user.setName("admin");

        /**
         * *.ignore插件的作用
         * 1.需要配合git、subversion等版本控制软件使用
         * 2.将不需要添加到git等版本控制中的文件过滤掉不再提示是否加入到版本控制中
         *   - IDEA工具的.idea文件夹
         *   - IDEA工具的*.iml文件
         */
        /**
         * 使用lombok需要两步
         * 1. IDEA中安装Lombok插件
         * 2. 项目工程的pom.xml文件中加入对lombok的依赖。
         */

        /**
         * 1.基本数据值类型相等的判断：int，long，float，boolean
         * 2.对象类型相等的判断：String，Integer，Long，Float，Boolean
         */
        int a=1;
        long b=1;
        float c=1;
        Integer aa=1,aaa=1;
        Long bb=1l,bbb=1l;
        Float cc=1f,ccc=1f;
        if(1==a){System.out.println(0);}else{System.out.println(1);}
        if(1==b){System.out.println(0);}else{System.out.println(1);}
        if(1==c){System.out.println(0);}else{System.out.println(1);}
        if(aaa==aa){System.out.println(0);}else{System.out.println(1);}
        if(bbb==bb){System.out.println(0);}else{System.out.println(1);}
        if (ccc==cc){System.out.println(0);}else{System.out.println(1);}
        String ss="abc";
        if ("abc"==ss){System.out.println(0);}else{System.out.println(1);}
        if("abc".equals(ss)){System.out.println(0);}else{System.out.println(1);}





        String sss = null; //""," ","abc"
        sss.equals("abc"); // 方法1
        "abc".equals(sss); // 方法2

        /**
         * 作业3 - JAVA基础-如何判断相等
         *    1- 对象数据类型如何判断相等(Integer、Long、Float、Boolean)
         *    2- 与基本数据类型相比有何区别
         */

        Float fc=1f,fcc=1f;
        //fc == fcc; //方法3
        fc.equals(fcc); //方法4
        //不能是null
        fc=null;
        if(fc.floatValue() == fcc.floatValue()) {
        }

        /**
         * 1，生产环境中，升级某项设置或者功能，顺序：
         *     - 本地搭建与生产环境类似的环境
         *     - 升级步骤形成文档/便签
         *     - 生产环境做备份：备份DB、程序、备份配置文件
         *          - 更新程序：备份程序、备份DB
         *          - 更新数据库：备份DB
         *          - 更新配置文件：备份DB、备份配置文件
         *          - 增量更新
         *          - JVM参数，增大内存。
         *    - 测试
         *
         *
         */


        /**
         * 1.spring
         * 2.JDBC、ORM、JPA、Hibernate、Mybatis（理论）
         * 3.数据库MariaDB/MySQL，复习数据库知识 select、update、delete、left join、inner join等
         * 4.整合框架（SpringMVC + Spring + JPA/Mybatis）动手写代码
         * 5.课程作业需求分析（小组作业）分析功能，设计数据库
         * 6.根据数据库设计完成领域模型domain（实体类）
         * 7.开发repository、service（事务）、controller
         * 8.文件上传下载
         *
         *
         */


        if ("admin".equals(username)&&"123".equals(password)){
            return "loginsuccess";
        }else{
            //return "loginfail";
            return "user/userForm";
            //web-inf/views/+ return 字符串 + .jsp
        }

    }

    //public ModelAndView login(ServletRequest request){
    //    String username = request.getParameter("username");
    //    ModelAndView mv = new ModelAndView("loginsuccess");
    //    mv.addObject("username",username);
    //    return mv;
    //}
}

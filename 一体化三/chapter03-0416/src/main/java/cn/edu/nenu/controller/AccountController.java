package cn.edu.nenu.controller;

import cn.edu.nenu.domain.User;
import cn.edu.nenu.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AccountController Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-04-09 13:50
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    UserService userService;
    /**
     * 根据主键获取实体
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id")Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok(user); //json字符串
    }

    @PostMapping
    public ResponseEntity createJSON(@RequestBody String json){
        return null;
    }


    /**
     * RESTful API， 工作基本思路，及优势和缺点
     *   - 理解RESTful架构<a>http://www.ruanyifeng.com/blog/2011/09/restful.html</>
     *   - RESTful API 设计指南<a>http://www.ruanyifeng.com/blog/2014/05/restful_api.html</a>
     *  列表：GET,/users -> 列出所有用户
     *  新建：POST，/users -> 新建一个用户
     *  获取某一个：GET，/users/id -> 获取主键为id的用户信息
     *  更新：PUT，/users/id -> 更新主键为id的用户信息
     *  删除：DELETE，/users/id -> 删除主键为id的用户信息
     *  分页： ?limit=10 返回10条记录
     *  偏移： ?offset=10 从结果集的第10开始返回相应的数据集合
     *  排序： ?sortby=name&order=asc 指定某一属性作为排序字段，排序规则为asc
     *  页码： ?page=2&per_page=100 从第二页开始，每页记录数为100
     *
     */

}

package cn.edu.nenu.controller;

import cn.edu.nenu.config.Constants;
import cn.edu.nenu.config.HttpServlet;
import cn.edu.nenu.domain.User;
import cn.edu.nenu.domain.User;
import cn.edu.nenu.domain.User;
import cn.edu.nenu.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import static cn.edu.nenu.config.Constants.PAGE_SIZE;

/**
 * UserController Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-03-04 22:54
 */
@CommonsLog
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model, ServletRequest request, HttpSession httpSession){

        Map<String, Object> searchParams = HttpServlet.getParametersStartingWith(request, "s_");
        Page<User> users = userService.getEntityPage(searchParams, pageNumber, PAGE_SIZE);
        for(User user:users){
            if(user.getUsername().equals(httpSession.getAttribute("username")))
                user.setStatus(1);
        }
        model.addAttribute("users", users);
        model.addAttribute("PAGE_SIZE", PAGE_SIZE);
        model.addAttribute("searchParams", HttpServlet.encodeParameterStringWithPrefix(searchParams, "s_"));
        String path = "/WEB-INF/views/user/list.jsp";
        return "user/list";
    }

    @GetMapping(value = "create")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("action", "create");
        return "user/form";
    }

    @PostMapping(value = "create")
    public String create(@Valid User newUser, RedirectAttributes redirectAttributes) {
        LocalDateTime date = LocalDateTime.now(Clock.systemUTC());
        newUser.setCreatedAt(date);
        newUser.setStatus(0);
        userService.save(newUser);
        redirectAttributes.addFlashAttribute("message", "创建用户成功");
        return "redirect:/user/";
    }

    @GetMapping(value = "update/{id}")
    public String updateForm(@PathVariable("id") Long pkId, Model model){
        User user =  userService.findOne(pkId);
        model.addAttribute("user",user);
        model.addAttribute("action", "update");
        return "user/form";
    }

    /**
     * 页面编辑后，保存
     */
    @PostMapping(value = "update")
    public String update(@Valid User user, RedirectAttributes redirectAttributes){
        Long pkId = user.getId();
        User newDict = userService.findOne(pkId);
        newDict.setName(user.getName());
        newDict.setPassword(user.getPassword());
        newDict.setSex(user.getSex());
        newDict.setUsername(user.getUsername());

        userService.save(newDict);
        redirectAttributes.addFlashAttribute("message", "更改用户信息成功");
        return "redirect:/user/";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long pkId, RedirectAttributes redirectAttributes) {
        String message = "删除用户成功";
        try {
            userService.remove(pkId);
        }catch (Exception e){
            message = "删除用户失败，该用户被使用";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/user/";
    }
    
    @PostMapping(value = "delete")
    public String deleteBatch(ServletRequest request,RedirectAttributes redirectAttributes){
        String[] chkIds = request.getParameterValues("chkIds");
        for (String id:chkIds){
            userService.remove(Long.valueOf(id));
        }
        return "redirect:/user/";
    }
}
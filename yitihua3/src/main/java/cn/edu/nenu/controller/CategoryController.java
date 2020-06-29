package cn.edu.nenu.controller;

import cn.edu.nenu.config.Constants;
import cn.edu.nenu.config.HttpServlet;
import cn.edu.nenu.domain.Category;
import cn.edu.nenu.service.CategoryService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
 * CategoryController Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-03-04 22:54
 */
@CommonsLog
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model, ServletRequest request, HttpSession httpSession){

        Map<String, Object> searchParams = HttpServlet.getParametersStartingWith(request, "s_");
        Page<Category> categorys = categoryService.getEntityPage(searchParams, pageNumber, PAGE_SIZE);
        model.addAttribute("categorys", categorys);
        model.addAttribute("PAGE_SIZE", PAGE_SIZE);
        model.addAttribute("searchParams", HttpServlet.encodeParameterStringWithPrefix(searchParams, "s_"));
        String path = "/WEB-INF/views/category/list.jsp";
        return "category/list";
    }

    @GetMapping(value = "create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("action", "create");
        return "category/form";
    }

    @PostMapping(value = "create")
    public String create(@Valid Category newCategory, RedirectAttributes redirectAttributes) {
        categoryService.save(newCategory);
        redirectAttributes.addFlashAttribute("message", "创建栏目成功");
        return "redirect:/category/";
    }

    @GetMapping(value = "update/{id}")
    public String updateForm(@PathVariable("id") Integer pkId, Model model){
        Category category =  categoryService.findOne(pkId);
        model.addAttribute("category",category);
        model.addAttribute("action", "update");
        return "category/form";
    }

    /**
     * 页面编辑后，保存
     */
    @PostMapping(value = "update")
    public String update(@Valid Category category, RedirectAttributes redirectAttributes){
        Integer pkId = category.getId();
        Category newCategory = categoryService.findOne(pkId);
        LocalDateTime date = LocalDateTime.now(Clock.systemUTC());
        newCategory.setName(category.getName());
        newCategory.setSort(category.getSort());
        newCategory.setStatus(category.getStatus());
        categoryService.save(newCategory);
        redirectAttributes.addFlashAttribute("message", "更改栏目信息成功");
        return "redirect:/category/";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Integer pkId, RedirectAttributes redirectAttributes) {
        String message = "删除栏目成功";
        try {
            categoryService.remove(pkId);
        }catch (Exception e){
            message = "删除栏目失败，该栏目被使用";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/category/";
    }

    @PostMapping(value = "delete")
    public String deleteBatch(ServletRequest request,RedirectAttributes redirectAttributes){
        String[] chkIds = request.getParameterValues("chkIds");
        for (String id:chkIds){
            categoryService.remove(Integer.valueOf(id));
        }
        return "redirect:/category/";
    }
}
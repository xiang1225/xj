package cn.edu.nenu.controller;

import cn.edu.nenu.domain.Storage;
import cn.edu.nenu.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import static cn.edu.nenu.config.Constants.PAGE_SIZE;

/**
 * StorageController Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-28 13:58
 */
@Controller
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    StorageService storageService;

    @RequestMapping("")
    public String list(@RequestParam(value = "page",defaultValue = "1")int pageNumber,
                       ServletRequest request, Model model){
        String description = request.getParameter("description");
        Page<Storage> storages = storageService.getPage(pageNumber,PAGE_SIZE,description);
        model.addAttribute("list",storages);
        model.addAttribute("PAGE_SIZE", PAGE_SIZE);
        model.addAttribute("seachParam",description);
        return "storage/list"; //文件地址
    }

    @GetMapping("/create")
    public String createForm(Model model){
        Storage storage = new Storage();
        model.addAttribute("storage",storage);
        return "storage/form"; //文件地址
    }

    @PostMapping("/create")
    public String create(@Valid Storage storage, RedirectAttributes redirectAttributes){
        storageService.save(storage);
        redirectAttributes.addAttribute("message","操作成功");
        return "redirect:/storage/"; //跳转地址
    }
}

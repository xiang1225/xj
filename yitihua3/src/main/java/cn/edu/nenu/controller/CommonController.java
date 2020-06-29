package cn.edu.nenu.controller;

import cn.edu.nenu.config.CommonUtil;
import cn.edu.nenu.domain.Storage;
import cn.edu.nenu.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * CommonController Class
 *
 * 文件上传和下载
 *
 * 操作步骤：
 *  1. 在spring-mvc.xml文件中配置bean（multipartResolver）
 *  2. 引入jar包：commons-fileupload 1.3.3,commons-io 2.6
 *  3.创建Controller，
 *  4.创建JSP页面
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-14 13:54
 */
@Controller
@Slf4j
public class CommonController {

    @Autowired
    StorageService storageService;

    @RequestMapping("/file/index")
    public String index(){
        return "common/file";
    }

    /**
     * 从前端页面接收文件参数
     * @param file
     * @return
     */
    @RequestMapping("/file/upload")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file) {

        Storage storage = storageService.findOne(1);

        // 文件不为空
        if(!file.isEmpty()) {
            // 文件存放路径
            //String path = request.getServletContext().getRealPath("/");
            /**
             * 相对路径生成规则：
             *  1. 根据时间生成路径
             *      - yyyy / mm / dd
             *      - yyyymm
             *      - yyyy
             *  2. 根据业务规则生成
             *      - 存储某一学校的学生图片，学校代码/
             *      - 考试任务/
             *  3. 根据文件类型
             *      - pdf
             *      - word
             *
             *  文件上传后，关于文件名的说明
             *  1. 保证文件名唯一，否则会出现同一文件名冲突
             *  2. 新文件名生成规则。
             *      - UUID
             *      - 时间戳
             *      - 业务+时间戳
             *      - 业务，例如：订单编号，书籍编号，学生学籍号，（身份证号，由于法规要求，已经不推荐使用身份证号等个人敏感信息作为关键信息）
             */

            String path = storage.getPath()+"/"; //与java程序同机器的一个路径 / java程序部署路径 / 网络路径
            // 目标文件,使用原文件名
            File destFile = new File(path,file.getOriginalFilename());//新的文件名可能是随机字符串，202005141510000
            // 转存文件
            try {
                file.transferTo(destFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return "common/success";  //跳转到上传成功页面，新建jsp页面
    }

    /**
     * 文件下载
     * @return
     */
    @RequestMapping("/file/download")
    public ResponseEntity download(){
        Storage storage = storageService.findOne(1);
        String getPathById="xshell-console-log"; //根据参数获取文件路径
        String filePath = storage.getPath()+"/"+getPathById;
        Resource res = CommonUtil.loadFileAsResource(filePath);
        String contentType = null;
        try {
            Path path = Paths.get(res.getFile().getAbsolutePath());
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            log.error("Could not determine file type. 无法获取文件类型。",e);
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null){
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+res.getFilename()+"\"")
                .body(res);
    }
}


/**
 *
 * https://spring.io/guides/gs/uploading-files/
 *
 * https://www.baeldung.com/spring-file-upload
 *
 * https://www.baeldung.com/spring-apache-file-upload
 *
 *
 *
 *
 *
 */

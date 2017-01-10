package cn.edu.hust.controller;

import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xiaolei03 on 17/1/10.
 */
@Controller
@RequestMapping(value = "/home/admin/import")
public class AdminImportController {

    /**
     * 进入主页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "admin_import_student_info";
    }

    /**
     * 上传文件
     * @return
     */
    @RequestMapping(value = "/upload", method= RequestMethod.POST)
    @ResponseBody
    public CommonResponse upload(@RequestParam("file") MultipartFile file) {
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new FailResponse();
        } catch (IOException e) {
            e.printStackTrace();
            return new FailResponse();
        }

        return new SuccessResponse();
    }

}

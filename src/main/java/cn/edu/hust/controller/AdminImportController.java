package cn.edu.hust.controller;

import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.service.AdminImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by xiaolei03 on 17/1/10.
 */
@Controller
@RequestMapping(value = "/home/admin/import")
public class AdminImportController {
    @Autowired
    private AdminImportService adminImportService;

    @Value("${studentInfo.import.template.path}")
    private String STUDENTINFO_IMPORT_TEMPLOATE_PATH;

    /**
     * 进入主页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "admin_import_student_info";
    }

    @RequestMapping(value = "/template/download")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        String fileName = STUDENTINFO_IMPORT_TEMPLOATE_PATH;

        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName).getBytes(), "iso-8859-1"));
        adminImportService.downloadTemplate(response.getOutputStream(), fileName);
    }

    /**
     * 导入学生信息
     * @return 200:成功  300:格式有误 301:学号重复  500:失败
     */
    @RequestMapping(value = "/upload", method= RequestMethod.POST)
    @ResponseBody
    public CommonResponse upload(@RequestParam("file") MultipartFile file) {
        BufferedOutputStream out = null;

        String fileName = "etas_student_info_import.xls";
        try {
            // save file
            out = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
            out.write(file.getBytes());
            out.flush();
            out.close();

            return adminImportService.importStudentInfos(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new FailResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new FailResponse();
        } finally {
            // 删除此文件
            File fileObj = new File(fileName);
            fileObj.delete();
        }
    }

}

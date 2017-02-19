package cn.edu.hust.controller;

import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminExportRequest;
import cn.edu.hust.model.request.AdminExportSearchRequest;
import cn.edu.hust.service.AdminExportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by xiaolei03 on 17/2/18.
 * 导出
 */
@Controller
@RequestMapping(value = "/home/admin/export")
public class AdminExportController {
    @Autowired
    private AdminExportService adminExportService;


    /**
     * 进入主页
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "admin_export_thesis_info";
    }

    /**
     * 查询
     */
    @RequestMapping(value = "/search")
    public String search(@RequestBody AdminExportSearchRequest adminExportSearchRequest, Model model) {
        List<ThesisBasicInfo> thesisBasicInfoList = adminExportService.search(adminExportSearchRequest);
        model.addAttribute("thesisBasicInfoList", thesisBasicInfoList);
        return "admin_export_thesis_info_divd_table";
    }

    /**
     * 导出Excel文件
     */
    @RequestMapping(value = "/excel")
    public void exportExcel(@RequestParam String userIds, HttpServletResponse response) throws IOException {
        String fileName = "basic_info_table.xls";

        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName).getBytes(), "iso-8859-1"));

        HSSFWorkbook wb = adminExportService.exportExcel(userIds.split(","));
        wb.write(response.getOutputStream());
    }
}

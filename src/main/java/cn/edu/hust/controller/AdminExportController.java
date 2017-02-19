package cn.edu.hust.controller;

import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminExportRequest;
import cn.edu.hust.model.request.AdminExportSearchRequest;
import cn.edu.hust.service.AdminExportService;
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

import java.io.IOException;
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
    public ResponseEntity<byte[]> exportExcel(@RequestParam String userIds) {
        byte[] buf = adminExportService.exportExcel(userIds.split(","));

        String exportExcelFileName = "basic_info_table.xls";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", exportExcelFileName);

        return new ResponseEntity<byte[]>(buf, httpHeaders, HttpStatus.CREATED);
    }
}

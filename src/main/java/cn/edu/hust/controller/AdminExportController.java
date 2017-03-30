package cn.edu.hust.controller;

import cn.edu.hust.model.HustDepartment;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminExportSearchRequest;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.service.AdminExportService;
import cn.edu.hust.service.HustDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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

    @Autowired
    private HustDepartmentService hustDepartmentService;

    @Value("${file.upload.directory}")
    private String FILE_UPLOAD_DIRECTORY;

    private static final String LW_POSTFIX = ".pdf";

    /**
     * 进入主页
     */
    @RequestMapping(value = "/index")
    public String index(Model model) {
        List<HustDepartment> hustDepartmentList = hustDepartmentService.getAllDepartments();
        model.addAttribute("hustDepartmentList", hustDepartmentList);

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
        String fileName = "基本信息表.xls";

        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName).getBytes(), "iso-8859-1"));

        adminExportService.exportExcel(userIds.split(","), response.getOutputStream());
    }

    /**
     * 导出推荐表
     * @param userIds
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/tjb")
    public void exportTjb(@RequestParam String userIds, HttpServletResponse response) throws IOException {
        String fileName = "优秀论文推荐表.zip";

        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName).getBytes(), "iso-8859-1"));

        adminExportService.exportTjbPackage(userIds.split(","), response.getOutputStream());
    }

    /**
     * 导出PDF文件
     */
    @RequestMapping(value = "/pdf")
    public void exportPdf(@RequestParam String userIds, HttpServletResponse response) throws IOException {
        String fileName = "论文pdf文件包.zip";

        response.reset();
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName).getBytes(), "iso-8859-1"));

        OutputStream outputStream = response.getOutputStream();

        adminExportService.exportPdfPackage(userIds.split(","), outputStream);
    }

    /**
     * 更新序号
     */
    @RequestMapping(value = "/xh/update")
    @ResponseBody
    public CommonResponse updateXh(@RequestBody AdminExportSearchRequest adminExportSearchRequest) throws IOException {
        String applyYear = adminExportSearchRequest.getApplyYear();

        try {
            if (adminExportService.updateXh(applyYear)) {
                return new SuccessResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new FailResponse();
    }
}

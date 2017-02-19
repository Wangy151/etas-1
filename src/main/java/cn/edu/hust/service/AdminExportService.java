package cn.edu.hust.service;

import cn.edu.hust.dao.ThesisExportDao;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminExportSearchRequest;
import cn.edu.hust.utils.SqlUtil;
import cn.edu.hust.utils.ZipUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolei03 on 17/1/5.
 * 导出
 */
@Service
public class AdminExportService {
    @Autowired
    private ThesisExportDao thesisExportDao;

    /**
     * 查询
     * @param adminExportSearchRequest
     * @return
     */
    public List<ThesisBasicInfo> search(AdminExportSearchRequest adminExportSearchRequest) {
        return thesisExportDao.getThesisBasicInfoList(adminExportSearchRequest);
    }

    public String getAdminExportSearchSql(AdminExportSearchRequest adminExportSearchRequest) {
        String querySql = " SELECT apply_year, department, student_type, zzxm, zzxh, lwtm, dsxm, apply_status FROM thesis_basic_info ";

        String applyYear = adminExportSearchRequest.getApplyYear();
        String applyStatus = adminExportSearchRequest.getApplyStatus();
        String studentType = adminExportSearchRequest.getStudentType();
        String department = adminExportSearchRequest.getDepartment();

        querySql += " WHERE apply_status = '" + applyStatus + "' ";

        if (!StringUtils.isEmpty(applyYear)) {
            querySql += " AND apply_year = '" + applyYear + "' ";
        }

        if (!StringUtils.isEmpty(studentType)) {
            querySql += " AND student_type = '" + studentType + "' ";
        }

        if (!StringUtils.isEmpty(department)) {
            querySql += " AND department = '" + department + "' ";
        }

        return querySql;
    }

    /**
     * 导出excel
     * @param userIds
     * @return
     */
    public HSSFWorkbook exportExcel(String[] userIds) {
        String[] excelTitle = {"SSDM", "SSMC", "XXDM", "XXMC", "SZYX"
                , "XH", "ZZXH", "XM", "XB", "CSNY"
                , "MZ", "DSXM", "LWTM", "LWYWTM", "YJFX"
                , "GJC", "LWYS", "ZPB", "LW", "RXNY"
                , "HXWRQ", "YJXKM", "YJXKMC", "EJXKM", "EJXKMC"
                , "GDLB", "GDFS", "ZZZC", "LXR", "BZ"};


        List<ThesisBasicInfo> thesisBasicInfoList = thesisExportDao.getExportExcel(SqlUtil.arrayToSql(userIds));

        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sheet = wb.createSheet("基本信息表");
        HSSFSheet sheet = wb.createSheet("basic_info_table");

        // 标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelTitle.length; i ++) {
            row.createCell(i).setCellValue(excelTitle[i]);
        }

        int rowNum  = 1;
        for (ThesisBasicInfo model : thesisBasicInfoList) {
            HSSFRow rowObj = sheet.createRow(rowNum++);
            rowObj.createCell(0).setCellValue(model.getSsdm());
            rowObj.createCell(1).setCellValue(model.getSsmc());
            rowObj.createCell(2).setCellValue(model.getXxdm());
            rowObj.createCell(3).setCellValue(model.getXxmc());
            rowObj.createCell(4).setCellValue(model.getCplx());
            rowObj.createCell(5).setCellValue(model.getXh());
            rowObj.createCell(6).setCellValue(model.getZzxh());
            rowObj.createCell(7).setCellValue(model.getZzxm());
            rowObj.createCell(8).setCellValue(model.getXb());
            rowObj.createCell(9).setCellValue(model.getCsny());
            rowObj.createCell(10).setCellValue(model.getMz());
            rowObj.createCell(11).setCellValue(model.getDsxm());
            rowObj.createCell(12).setCellValue(model.getLwtm());
            rowObj.createCell(13).setCellValue(model.getLwywtm());
            rowObj.createCell(14).setCellValue(model.getYjfx());
            rowObj.createCell(15).setCellValue(model.getLwzwgjz());
            rowObj.createCell(16).setCellValue(model.getLwys());
            rowObj.createCell(17).setCellValue(model.getLwtjblj());
            rowObj.createCell(18).setCellValue(model.getLwywlj());
            rowObj.createCell(19).setCellValue(model.getRxny());
            rowObj.createCell(20).setCellValue(model.getHdxwrq());
            rowObj.createCell(21).setCellValue(model.getYjxkdm());
            rowObj.createCell(22).setCellValue(model.getYjxkmc());
            rowObj.createCell(23).setCellValue(model.getEjxkdm());
            rowObj.createCell(24).setCellValue(model.getEjxkmc());
            rowObj.createCell(25).setCellValue(model.getGdlb());
            rowObj.createCell(26).setCellValue(model.getGdfs());
            rowObj.createCell(27).setCellValue(model.getZzzc());
            rowObj.createCell(28).setCellValue(model.getXxlxr());
            rowObj.createCell(29).setCellValue(model.getBz());
        }

        return wb;
    }

    public String getAdminExportExcelSql(String whereInSql) {
        String querySql = " SELECT ssdm, ssmc, xxdm, xxmc, cplx, " +
                " xh, zzxh, zzxm, xb, csny, " +
                " mz, dsxm, lwtm, lwywtm, yjfx, " +
                " lwzwgjz, lwys, lwtjblj, lwywlj, " +
                " rxny, hdxwrq, yjxkdm, yjxkmc, ejxkdm, " +
                " ejxkmc, gdlb, gdfs, zzzc, xxlxr, bz " +
                " FROM thesis_basic_info " +
                " WHERE zzxh IN (" + whereInSql + ")";

        return querySql;
    }

    /**
     * 到处pdf
     * @param userIds
     * @param outputStream
     * @throws IOException
     */
    public void exportPdf(String[] userIds, OutputStream outputStream) throws IOException {
        String[] thesisPaths = thesisExportDao.getExportPdf(SqlUtil.arrayToSql(userIds));
        List<String> srcFileList = new ArrayList<String>();
        for (String thesisPath : thesisPaths) {
            srcFileList.add(thesisPath + ".pdf");
        }
        ZipUtil.doCompress(srcFileList, outputStream);
    }

    public String getAdminExportPdfSql(String whereInSql) {
        return " SELECT lwywlj FROM thesis_basic_info WHERE zzxh IN (" + whereInSql + ")";
    }
}

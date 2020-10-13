package cn.edu.hust.service;

import cn.edu.hust.dao.ThesisExportDao;
import cn.edu.hust.model.DoctorThesisApply;
import cn.edu.hust.model.MasterThesisApply;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminExportSearchRequest;
import cn.edu.hust.utils.SqlUtil;
import cn.edu.hust.utils.VelocityUtil;
import cn.edu.hust.utils.ZipUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaolei03 on 17/1/5.
 * 导出
 */
@Service
public class AdminExportService {
    @Autowired
    private ThesisExportDao thesisExportDao;

    @Autowired
    private VelocityEngine velocityEngine;

    @Value("${file.upload.directory}")
    private String FILE_UPLOAD_DIRECTORY;

    private static final String LW_POSTFIX = ".pdf";

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
    public void exportExcel(String[] userIds, OutputStream outputStream) throws IOException {
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

        wb.write(outputStream);
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
     * 导出word压缩包
     * @param userIds
     * @param outputStream
     */
    public void exportTjbPackage(String[] userIds, OutputStream outputStream) throws IOException {
        List<ThesisBasicInfo> thesisBasicInfoList = thesisExportDao.getLwtjbljList(SqlUtil.arrayToSql(userIds));

        List<String> masterUserIds = new ArrayList<String>();
        List<String> doctorUserIds = new ArrayList<String>();
        Map<String, String> tjbPathMap = new HashMap<String, String>();
        for (ThesisBasicInfo thesisBasicInfo : thesisBasicInfoList) {
            tjbPathMap.put(thesisBasicInfo.getZzxh(), thesisBasicInfo.getLwtjblj());
            if ("硕士".equalsIgnoreCase(thesisBasicInfo.getStudentType())) {
                masterUserIds.add(thesisBasicInfo.getZzxh());
            } else if ("博士".equalsIgnoreCase(thesisBasicInfo.getStudentType())) {
                doctorUserIds.add(thesisBasicInfo.getZzxh());
            }
        }

        // 硕士
        if (masterUserIds.size() > 0) {
            List<MasterThesisApply> masterThesisApplyList = thesisExportDao.getMasterTjbList(SqlUtil.listToSql(masterUserIds));
            for (MasterThesisApply model : masterThesisApplyList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("model", model);

                String docFileName = tjbPathMap.get(model.getZzxh()) + ".doc";
                PrintWriter writer = new PrintWriter(new File(docFileName));
                VelocityEngineUtils.mergeTemplate(velocityEngine, "tjb_template_master.vm", "UTF-8", map, writer);
                writer.flush();
                writer.close();

                File file = new File(docFileName);
                FileInputStream fileInputStream = new FileInputStream(file);
                ZipUtil.doCompress(fileInputStream, docFileName, outputStream);
                fileInputStream.close();
                file.delete();
            }
        }

        // 博士
        if (doctorUserIds.size() > 0) {
            List<DoctorThesisApply> doctorThesisApplyList = thesisExportDao.getDoctorTjbList(SqlUtil.listToSql(doctorUserIds));
            for (DoctorThesisApply model : doctorThesisApplyList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("model", model);

                String docFileName = tjbPathMap.get(model.getZzxh()) + ".doc";
                PrintWriter writer = new PrintWriter(new File(docFileName));
                VelocityEngineUtils.mergeTemplate(velocityEngine, "tjb_template_doctor.vm", "UTF-8", map, writer);
                writer.flush();
                writer.close();

                File file = new File(docFileName);
                FileInputStream fileInputStream = new FileInputStream(file);
                ZipUtil.doCompress(fileInputStream, docFileName, outputStream);
                fileInputStream.close();
                file.delete();
            }
        }

    }

    public String getLwtjbSql(String whereInSql) {
        String querySql = " SELECT zzxh, lwtjblj, student_type FROM thesis_basic_info " +
                " WHERE zzxh IN (" + whereInSql + ")";

        return querySql;
    }

    public String getMasterTjbSql(String whereInSql) {
        String querySql = " SELECT * FROM master_thesis_apply " +
                " WHERE zzxh IN (" + whereInSql + ")";
        return querySql;
    }

    public String getDoctorTjbSql(String whereInSql) {
        String querySql = " SELECT * FROM doctor_thesis_apply " +
                " WHERE zzxh IN (" + whereInSql + ")";
        return querySql;
    }

    /**
     * 导出pdf
     * @param userIds
     * @param outputStream
     * @throws IOException
     */
    public void exportPdfPackage(String[] userIds, OutputStream outputStream) throws IOException {
        String[] thesisPaths = thesisExportDao.getExportPdf(SqlUtil.arrayToSql(userIds));
        List<String> srcFileList = new ArrayList<String>();
        for (String thesisPath : thesisPaths) {
            srcFileList.add(FILE_UPLOAD_DIRECTORY + File.separator + thesisPath + LW_POSTFIX);
        }
        ZipUtil.doCompress(srcFileList, outputStream);
    }

    public String getAdminExportPdfSql(String whereInSql) {
        return " SELECT lwywlj FROM thesis_basic_info WHERE zzxh IN (" + whereInSql + ")";
    }

    /**
     * 更新序号
     * @param applyYear 申请年份
     * @return
     */
    public boolean updateXh(String applyYear) {
        List<ThesisBasicInfo> thesisBasicInfoList = thesisExportDao.getUpdateXhStudentInfo(applyYear, "通过审核");
        // reset  每次更新序号前将文件名重置至最初状态
        try {
            this.resetLwywlj(thesisBasicInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        List<ThesisBasicInfo> masterThesisBasicInfoList = new ArrayList<ThesisBasicInfo>();
        List<ThesisBasicInfo> doctorThesisBasicInfoList = new ArrayList<ThesisBasicInfo>();
        for (ThesisBasicInfo thesisBasicInfo : thesisBasicInfoList) {
            if ("硕士".equalsIgnoreCase(thesisBasicInfo.getStudentType())) {
                masterThesisBasicInfoList.add(thesisBasicInfo);
            } else if ("博士".equalsIgnoreCase(thesisBasicInfo.getStudentType())) {
                doctorThesisBasicInfoList.add(thesisBasicInfo);
            }
        }

        // 处理硕士
        try {
            this.doUploadXh(masterThesisBasicInfoList, "YS");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // 处理博士
        try {
            this.doUploadXh(doctorThesisBasicInfoList, "YB");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void resetLwywlj(List<ThesisBasicInfo> thesisBasicInfoList) throws Exception {
        for (ThesisBasicInfo thesisBasicInfo : thesisBasicInfoList) {
            String zzxh = thesisBasicInfo.getZzxh();
            String pathPrefix = thesisBasicInfo.getXxdm() + "_" + thesisBasicInfo.getEjxkdm() + "_" + zzxh;
            String newLwywlj = pathPrefix + "_LW";

            String lwywlj = thesisBasicInfo.getLwywlj();
            if (!newLwywlj.equals(lwywlj)) {
                thesisBasicInfo.setLwywlj(newLwywlj);

                // rename
                File file = new File(FILE_UPLOAD_DIRECTORY + File.separator + lwywlj + LW_POSTFIX);
                File renamedFile = new File(FILE_UPLOAD_DIRECTORY + File.separator + newLwywlj + LW_POSTFIX);
                if (file.exists()) {
                    file.renameTo(renamedFile);
                } else {
                    throw new Exception("论文文件不存在: " + lwywlj);
                }
            }
        }

    }

    @Transactional
    private void doUploadXh(List<ThesisBasicInfo> thesisBasicInfoList, String xhPrefix) throws Exception {
        int count = 1;

        for (ThesisBasicInfo thesisBasicInfo : thesisBasicInfoList) {
            String index = String.format("%03d", count++);
            String xh = xhPrefix + index;
            String pathPrefix = thesisBasicInfo.getXxdm() + "_" + thesisBasicInfo.getEjxkdm() + "_" + xh;
            String newLwtjblj = pathPrefix + "_ZPB";
            String newLwywlj = pathPrefix + "_LW";

            String lwywlj = thesisBasicInfo.getLwywlj();
            // rename
            File file = new File(FILE_UPLOAD_DIRECTORY + File.separator + lwywlj + LW_POSTFIX);
            File renamedFile = new File(FILE_UPLOAD_DIRECTORY + File.separator + newLwywlj + LW_POSTFIX);
            if (file.exists()) {
                file.renameTo(renamedFile);
            } else {
                throw new Exception("论文文件不存在: " + lwywlj);
            }

            String zzxh = thesisBasicInfo.getZzxh();
            int affectRows = thesisExportDao.updateStudentXh(xh, newLwtjblj, newLwywlj, zzxh);
            if (affectRows <= 0) {
                throw new Exception("数据库中不存在该学号：" + zzxh);
            }

        }

    }
}

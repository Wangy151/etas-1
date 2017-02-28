package cn.edu.hust.service;

import cn.edu.hust.dao.StudentInfoImportDao;
import cn.edu.hust.model.StudentInfoImport;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminReviewRequest;
import cn.edu.hust.model.request.AdminSearchRequest;
import cn.edu.hust.model.response.AdminImportResponse;
import cn.edu.hust.model.response.CommonResponse;
import cn.edu.hust.model.response.FailResponse;
import cn.edu.hust.model.response.SuccessResponse;
import cn.edu.hust.utils.FileUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaolei03 on 17/1/5.
 */
@Service
public class AdminImportService {
    @Autowired
    private StudentInfoImportDao studentInfoImportDao;

    public void downloadTemplate(OutputStream outputStream, String fileName) throws IOException {
        outputStream.write(FileUtil.getFileByteArray(fileName));
        outputStream.flush();
    }

    /**
     * 管理员导入学生信息(excel文件)
     *
     * @param fileName
     * @return
     */
    @Transactional
    public CommonResponse importStudentInfos(String fileName) throws IOException {
        // 1. 解析excel, 放至list中
        List<StudentInfoImport> studentInfoImportList = new ArrayList<StudentInfoImport>();
        List<String> xhlist = new ArrayList<String>(); // 学号

//        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:web-info.xls")));
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(new File(fileName)));
        HSSFSheet sheet = wb.getSheetAt(0);

        int totalNum = 0;
        // 第一行为标题, 忽略
        for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
            HSSFRow row = sheet.getRow(rowNum);
            StudentInfoImport studentInfoImport = new StudentInfoImport();
            if (row.getLastCellNum() < 9) {
                continue;
            }

            try {
                // validate
                if (!checkCellValid(row)) {
                    return new AdminImportResponse().withErrorRowNum(rowNum + 1).withCode(300).withMsg("格式有误");
                }

                String xh = this.getCellValue(row.getCell(0)); // 学号
                if (xhlist.contains(xh)) {
                    return new AdminImportResponse().withErrorRowNum(rowNum + 1).withCode(301).withMsg("重复学号");
                } else {
                    xhlist.add(xh);
                }

                String ejxkdm = this.getCellValue(row.getCell(3)); // 二级学科代码

                studentInfoImport.setXh(this.getCellValue(row.getCell(0)));
                studentInfoImport.setName(this.getCellValue(row.getCell(1)));
                studentInfoImport.setCsrq(this.getCellValue(row.getCell(2)));
                studentInfoImport.setEjxkdm(ejxkdm);
                studentInfoImport.setEjxkmc(this.getCellValue(row.getCell(4)));
                studentInfoImport.setDs(this.getCellValue(row.getCell(5)));
                studentInfoImport.setLwtm(this.getCellValue(row.getCell(6)));
                studentInfoImport.setRxnf(this.getCellValue(row.getCell(7)));
                studentInfoImport.setHxwsj(this.getCellValue(row.getCell(8)));
                studentInfoImport.setDbsj(this.getCellValue(row.getCell(9)));

                String yjxkdm = ejxkdm.substring(0, 4); // 一级学科代码
                String yjxkmc = studentInfoImportDao.getYjxkmc(yjxkdm); // 一级学科名称
                studentInfoImport.setYjxkdm(yjxkdm);
                studentInfoImport.setYjxkmc(yjxkmc);
                studentInfoImportList.add(studentInfoImport);
            } catch (IllegalStateException e) {
                return new AdminImportResponse().withErrorRowNum(rowNum + 1).withCode(300).withMsg("格式有误");
            }

            totalNum++;
        } // end for

        wb.close();

        // 2. 删除所有旧记录
        studentInfoImportDao.deleteAllRecords();

        // 3. 批量插入数据
        for (StudentInfoImport studentInfoImport : studentInfoImportList) {
            studentInfoImportDao.insertRecord(studentInfoImport);
        }

        return new AdminImportResponse().withTotalNum(totalNum).withCode(200).withMsg("成功");
    }


    /**
     * 简单对长度进行validate
     *
     * @param row
     * @return
     */
    private boolean checkCellValid(HSSFRow row) {
        // 空值
        if (StringUtils.isEmpty(row.getCell(0)) || StringUtils.isEmpty(row.getCell(1))
                || StringUtils.isEmpty(row.getCell(2)) || StringUtils.isEmpty(row.getCell(3))
                || StringUtils.isEmpty(row.getCell(4)) || StringUtils.isEmpty(row.getCell(5))
                || StringUtils.isEmpty(row.getCell(6)) || StringUtils.isEmpty(row.getCell(7))
                || StringUtils.isEmpty(row.getCell(8)) || StringUtils.isEmpty(row.getCell(9))) {
            return false;

        }

        // 入学年份
        String rxny = this.getCellValue(row.getCell(7));
        if (rxny.length() != 4) {
            return false;
        }

        // 获取学位时间
        String hqxwsj = this.getCellValue(row.getCell(8));
        // 答辩时间
        String dbsj = this.getCellValue(row.getCell(9));
        if (hqxwsj.length() != 8 || dbsj.length() != 8) {
            return false;
        }

        return true;
    }

    private String getCellValue(HSSFCell cell) {
        int type = cell.getCellType();
        System.out.println("type===" + type);

        if (type == 0) {
            // 数字类型
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        } else if (type == 1) {
            // 文本类型
            return cell.getStringCellValue();
        } else {
            throw new IllegalStateException();
        }
    }

}

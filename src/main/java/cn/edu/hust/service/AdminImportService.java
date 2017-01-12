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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

            // validate
            if (!checkCellValid(row)) {
                return new AdminImportResponse().withErrorRowNum(rowNum + 1).withCode(300).withMsg("格式有误");
            }

            String xh = row.getCell(0).getStringCellValue(); // 学号
            if (xhlist.contains(xh)) {
                return new AdminImportResponse().withErrorRowNum(rowNum + 1).withCode(301).withMsg("重复学号");
            } else {
                xhlist.add(xh);
            }

            studentInfoImport.setXh(row.getCell(0).getStringCellValue());
            studentInfoImport.setName(row.getCell(1).getStringCellValue());
            studentInfoImport.setCsrq(row.getCell(2).getStringCellValue());
            studentInfoImport.setEjxkdm(row.getCell(3).getStringCellValue());
            studentInfoImport.setEjxkmc(row.getCell(4).getStringCellValue());
            studentInfoImport.setDs(row.getCell(5).getStringCellValue());
            studentInfoImport.setLwtm(row.getCell(6).getStringCellValue());
            studentInfoImport.setRxnf(row.getCell(7).getStringCellValue());
            studentInfoImport.setHxwsj(row.getCell(8).getStringCellValue());
            studentInfoImport.setDbsj(row.getCell(9).getStringCellValue());
            studentInfoImportList.add(studentInfoImport);

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
        String rxny = row.getCell(7).getStringCellValue();
        if (rxny.length() != 4) {
            return false;
        }

        // 获取学位时间
        String hqxwsj = row.getCell(8).getStringCellValue();
        // 答辩时间
        String dbsj = row.getCell(9).getStringCellValue();
        if (hqxwsj.length() != 8 || dbsj.length() != 8) {
            return false;
        }

        return true;
    }


}

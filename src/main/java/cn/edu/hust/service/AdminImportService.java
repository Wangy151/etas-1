package cn.edu.hust.service;

import cn.edu.hust.dao.StudentInfoImportDao;
import cn.edu.hust.model.StudentInfoImport;
import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.AdminReviewRequest;
import cn.edu.hust.model.request.AdminSearchRequest;
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
     * @param fileName
     * @return
     */
    @Transactional
    public boolean importStudentInfos(String fileName) throws IOException {
        // 1. 解析excel, 放至list中
        List<StudentInfoImport> studentInfoImportList = readExcel(fileName);
        // 删除此文件
        File file = new File(fileName);
        if (!file.delete()) {
            return false;
        }

        // 2. 删除所有旧记录
        studentInfoImportDao.deleteAllRecords();

        // 3. 批量插入数据
        for (StudentInfoImport studentInfoImport : studentInfoImportList) {
            studentInfoImportDao.insertRecord(studentInfoImport);
        }

        return true;
    }

    public List<StudentInfoImport> readExcel(String fileName) throws IOException {
        List<StudentInfoImport> studentInfoImportList = new ArrayList<StudentInfoImport>();

        //Excel文件
//        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:web-info.xls")));
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(new File(fileName)));
        //Excel工作表
        HSSFSheet sheet = wb.getSheetAt(0);
        // 第一行为标题,忽略
        for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
            HSSFRow row = sheet.getRow(rowNum);
            StudentInfoImport studentInfoImport = new StudentInfoImport();
            if (row.getLastCellNum() < 9) {
                continue;
            }
            studentInfoImport.setXh(null == row.getCell(0) ? "" : row.getCell(0).getStringCellValue());
            studentInfoImport.setName(null == row.getCell(1) ? "" : row.getCell(1).getStringCellValue());
            studentInfoImport.setCsrq(null == row.getCell(2) ? "" : row.getCell(2).getStringCellValue());
            studentInfoImport.setEjxkdm(null == row.getCell(3) ? "" : row.getCell(3).getStringCellValue());
            studentInfoImport.setEjxkmc(null == row.getCell(4) ? "" : row.getCell(4).getStringCellValue());
            studentInfoImport.setDs(null == row.getCell(5) ? "" : row.getCell(5).getStringCellValue());
            studentInfoImport.setLwtm(null == row.getCell(6) ? "" : row.getCell(6).getStringCellValue());
            studentInfoImport.setRxnf(null == row.getCell(7) ? "" : row.getCell(7).getStringCellValue());
            studentInfoImport.setHxwsj(null == row.getCell(8) ? "" : row.getCell(8).getStringCellValue());
            studentInfoImport.setDbsj(null == row.getCell(9) ? "" : row.getCell(9).getStringCellValue());
            studentInfoImportList.add(studentInfoImport);
        }
        wb.close();

        return studentInfoImportList;
    }

}

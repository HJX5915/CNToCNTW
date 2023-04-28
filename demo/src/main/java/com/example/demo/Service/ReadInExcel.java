package com.example.demo.Service;

import com.example.demo.POJO.ExcelData;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadInExcel {
    @Autowired
    LocalTranslationByOpencc localTranslationByOpencc;
    @Autowired
    ExcelData excelData;
    @Autowired
    WriteToExcel writeToExcel;
    public void getExcelData(String filePath, String sheetName){
        List<ExcelData> allData = new ArrayList<>();
        XSSFWorkbook workbook = null;  //用来解析Excel表
        XSSFSheet sheet = null;  // sheet页面内容
        XSSFRow row = null;     //每行的内容
        XSSFCell cell = null;   //每列的内容

        try {
            workbook = new XSSFWorkbook(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //根据Excel的sheet名称获取当前sheet内容
        sheet = workbook.getSheet(sheetName);
        if(sheet != null){
            //遍历每行元素
            for(int i = 0; i < sheet.getPhysicalNumberOfRows(); i++){
                excelData = new ExcelData();
                row = sheet.getRow(i);
                //遍历每列元素
                for(int j = 0; j < 	row.getPhysicalNumberOfCells(); j++) {
                    String row_cell_data = row.getCell(j).toString();
                    if(j==0){
                        excelData.setCell1(row_cell_data);
                    }
                    if(j==1){
                        excelData.setCell2(row_cell_data);
                    }
                }
                allData.add(excelData);
            }
        }
        try {
            localTranslationByOpencc = new LocalTranslationByOpencc();
            localTranslationByOpencc.trans(allData);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

package com.example.demo.Service;

import com.example.demo.POJO.ExcelData;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.ls.LSInput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteToExcel {

    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static XSSFCell cell;
    private static File file;

    //创建sheet页
    public static void setSheet(String sheetName) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }
    //创建表头
    public static void createHead(List<String> headList) {
        //创建表头，也就是第一行
        row = sheet.createRow(0);
        for (int i = 0; i < headList.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(headList.get(i));
        }
    }
    //创建表内容
    public static void createContent(List<List<String>> contentList) {
        //创建表内容，从第二行开始
        for (int i = 0; i < contentList.size(); i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < contentList.get(i).size(); j++) {
                row.createCell(j).setCellValue(contentList.get(i).get(j));
            }
        }
    }
    //写入文件
    public static void writeToFile(String filePath){
        file = new File(filePath);
        //将文件保存到指定的位置
        try {
            workbook.write(new FileOutputStream(file));
            System.out.println("写入成功");
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeInfo(List<ExcelData> dataInfo) {
        List<List<String>> writeToExcelInfo = new ArrayList<>();
//        List<String> trsWord = new ArrayList<>();
        for (ExcelData e:dataInfo){
            List<String> trsWord = new ArrayList<>();
            trsWord.add(e.getCell2());
            trsWord.add(e.getCell3());
            writeToExcelInfo.add(trsWord);
        }
        //表头测试数据
        List<String> headList = new ArrayList<>();
        headList.add("2052");
        headList.add("1028");
//        List<List<String>> contentList = getContent();//内容测试数据
        setSheet("WorkSheet");                        //创建sheet页
        createHead(headList);                         //设置表头
        createContent(writeToExcelInfo);                   //设置内容
        writeToFile("d://TrsTCHN.xlsx");         //写入文件
    }
}

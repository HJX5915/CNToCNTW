package com.example.demo.Service;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo.POJO.ExcelData;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GSTranslation {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ExcelData excelData;
    @Autowired
    WriteToExcel writeToExcel;
    public void PostInfo(List<ExcelData> excelData){
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        String url = "https://www.lddgo.net/api/Transfer";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //cookie没啥影响
//        headers.set("Cookie","Hm_lvt_75a21f203d323a988b1d8ce0eeae4de5=1682061510,1682502700; __gads=ID=e1e874a51af72a11-228c4dbc5adf0058:T=1682061510:RT=1682061510:S=ALNI_MbTk7xlj6xlGb5Ebg7Vj_XQgxC-Xw; __gpi=UID=00000be41b17467d:T=1682061510:RT=1682561787:S=ALNI_MZiGnDUg3_TijSYd3095C-FN9jHZQ; Hm_lpvt_75a21f203d323a988b1d8ce0eeae4de5=1682561787");
        JSONObject body = new JSONObject();
        int i =0;
        List<ExcelData> trsInfo = new ArrayList<>();
        for (ExcelData e:excelData) {
            ExcelData trsInfoForExcel = new ExcelData();
            restTemplate = new RestTemplate();
            if(e.getCell2().isEmpty() ||e.getCell2().equals("")){
                trsInfoForExcel.setCell2("1");
                trsInfoForExcel.setCell3("");
                trsInfo.add(trsInfoForExcel);
                continue;
            }else{
                body.put("data",e.getCell2());
            }
            body.put("from","s");
            body.put("to","t");
            HttpEntity request = new HttpEntity<>(body, headers);
            try{
                ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);
                trsInfoForExcel.setCell2(e.getCell2());
                trsInfoForExcel.setCell3((String) response.getBody().get("data"));
                System.out.println("This row cell2:"+e.getCell2()+"---cell3:"+(String) response.getBody().get("data"));
            }catch (Exception ex){
                try{
                    Thread.sleep(15000);
                }catch (InterruptedException ie){
                    System.out.println(ie);
                }
                System.out.println(ex);
            }
            trsInfo.add(trsInfoForExcel);
            i++;
        }
        new WriteToExcel().writeInfo(trsInfo);
        System.out.println("???"+trsInfo.toString());

    }

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
            PostInfo(allData);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}



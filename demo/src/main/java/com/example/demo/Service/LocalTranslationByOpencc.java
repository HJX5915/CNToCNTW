package com.example.demo.Service;

import com.example.demo.POJO.ExcelData;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

public class LocalTranslationByOpencc {
    @Autowired
    ExcelData excelData;
    @Autowired
    WriteToExcel writeToExcel;
    public void trans(List<ExcelData> excelDataList){
        List<ExcelData> trsInfo = new ArrayList<>();
        for (ExcelData e:excelDataList) {
            ExcelData trsInfoForExcel = new ExcelData();
            if(e.getCell2().isEmpty() ||e.getCell2().equals("")){
                trsInfoForExcel.setCell2("1");
                trsInfoForExcel.setCell3("");
                trsInfo.add(trsInfoForExcel);
                continue;
            }else{
                trsInfoForExcel.setCell2(e.getCell2());
                String trsWord = ZhConverterUtil.toTraditional(e.getCell2());
                trsInfoForExcel.setCell3(trsWord);
                trsInfo.add(trsInfoForExcel);
            }

        }
        System.out.println(trsInfo.toString());
        new WriteToExcel().writeInfo(trsInfo);
    }
}

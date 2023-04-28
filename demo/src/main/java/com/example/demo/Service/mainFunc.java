package com.example.demo.Service;


public class mainFunc {
    public static void main(String[] args) {
//        GSTranslation GST = new GSTranslation();
//"C:\\Users\\caspar.he\\Desktop\\Trasn\\TPM\\trs.xlsx","sheet1";
//        GST.getExcelData("C:\\Users\\caspar.he\\Desktop\\Trasn\\TPM\\trs.xlsx","sheet1");
        ReadInExcel readInExcel = new ReadInExcel();
        readInExcel.getExcelData("C:\\Users\\caspar.he\\Desktop\\Trasn\\TPM\\trs.xlsx","sheet1");
    }
}

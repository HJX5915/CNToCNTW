package com.example.demo.POJO;

public class ExcelData {
    private String cell1;
    private String cell2;
    private String cell3;

    public String getCell1() {
        return cell1;
    }

    public void setCell1(String cell1) {
        this.cell1 = cell1;
    }

    @Override
    public String toString() {
        return "ExcelData{" +
                "cell1='" + cell1 + '\'' +
                ", cell2='" + cell2 + '\'' +
                ", cell3='" + cell3 + '\'' +
                '}';
    }

    public String getCell2() {
        return cell2;
    }

    public void setCell2(String cell2) {
        this.cell2 = cell2;
    }

    public String getCell3() {
        return cell3;
    }

    public void setCell3(String cell3) {
        this.cell3 = cell3;
    }
}

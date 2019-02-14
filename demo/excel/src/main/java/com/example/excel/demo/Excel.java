package com.example.excel.demo;


import com.example.excel.entity.Insurance;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
public class Excel {

    private static Row row = null;

    public static void main(String[] args) throws IOException {
        String url = "D:/微信下载/全民购/全民购2.0/全民购2.0三方业务产品对账文件字段结构(2)(1).xlsx";
        method2(url);
    }

    private static void method2(String url) {
        //用来存放表中数据
        List<Insurance> insurancesList = new ArrayList<>();
        Workbook wb = null;
        String cellData = null;
        String filePath = url;
        wb = readExcel(filePath);
        if (wb != null) {
            //开始解析
            //读取sheet 0
            Sheet sheet = wb.getSheetAt(0);
            //第一行
            int firstRowIndex = sheet.getFirstRowNum();
            //最后一行
            int lastRowIndex = sheet.getLastRowNum();
            //遍历行
            for (int rIndex = firstRowIndex + 1; rIndex <= lastRowIndex; rIndex++) {
                System.out.println("rIndex: " + rIndex);
                row = sheet.getRow(rIndex);
                if (row != null) {
                    //第一列
                    int firstCellIndex = row.getFirstCellNum();
                    //最后一列
                    int lastCellIndex = row.getLastCellNum();
                    //遍历列
                    Insurance insurance = new Insurance();
                    if (StringUtils.isEmpty(getCellValue(0))) {
                        break;
                    }
                    insurance.setNumber(getCellValue(0));
                    insurance.setName(getCellValue(1));
                    insurance.setOrder(getCellValue(2));
                    insurance.setInsuranceName(getCellValue(3));
                    insurance.setClassification(getCellValue(4));
                    insurance.setType(getCellValue(5));
                    insurance.setCompany(getCellValue(6));
                    insurance.setYearDate(Integer.valueOf(getCellValue(7)));

                    Date data = row.getCell(8).getDateCellValue();
                    System.out.println(data);
                    insurance.setInsuranceDateTime(data);

                    insurance.setNumberCard(getCellValue(16));
                   /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
                    try {
                        System.out.println(getCellValue(8));
                        insurance.setInsuranceDateTime(sdf.parse(getCellValue(8)));
                    } catch (ParseException e) {
                        throw new RuntimeException("日期格式错误！");
                    }*/
                    insurancesList.add(insurance);
                }
            }
            for (Insurance insurance : insurancesList) {
                log.info("表格单行内容：insurance={}", insurance);
            }
        }
    }

    private static String getCellValue(int i) {
        Cell cell = row.getCell(i);
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    private static void method() {
        //用来存放表中数据
        List<Insurance> insurancesList = new ArrayList<>();
        Workbook wb = null;
        String cellData = null;
        String filePath = "D:/微信下载/全民购/全民购2.0/全民购2.0三方业务产品对账文件字段结构(2)(1).xlsx";
        wb = readExcel(filePath);
        if (wb != null) {
            //开始解析
            //读取sheet 0
            Sheet sheet = wb.getSheetAt(0);
            //第一行
            int firstRowIndex = sheet.getFirstRowNum();
            //最后一行
            int lastRowIndex = sheet.getLastRowNum();
            //遍历行
            for (int rIndex = firstRowIndex + 1; rIndex <= lastRowIndex; rIndex++) {
                System.out.println("rIndex: " + rIndex);
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    //第一列
                    int firstCellIndex = row.getFirstCellNum();
                    //最后一列
                    int lastCellIndex = row.getLastCellNum();
                    //遍历列
                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {
                        Cell cell = row.getCell(cIndex);
                        if (cell != null) {
                            //设置单元格类型为String
                            cell.setCellType(CellType.STRING);
                            //获取单元格内容
                            String str = cell.getStringCellValue();
                            log.info("单元格内容：" + str);
                        }
                    }
                }
            }


        }
    }


    //读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
//              is = getInputStream(file);
                if (".xls".equals(extString)) {
                    return wb = new HSSFWorkbook(is);
                } else if (".xlsx".equals(extString)) {
                    return wb = new XSSFWorkbook(is);
                }
                return wb = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 文件与字节数组字符串相互转换
     * （转换为字符串方便传输）
     *
     * @param file
     * @return
     * @throws IOException
     */
    private static InputStream getInputStream(File file) throws IOException {
        //文件转字节数组
        FileInputStream is1 = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = is1.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        is1.close();
        bos.close();
        byte[] fileByte = bos.toByteArray();
        //字节数组转字符串
        Base64.getEncoder().encodeToString(fileByte);
        //字节数组转io流，转文件
        InputStream is = new ByteArrayInputStream(fileByte);
        return is;
    }

}

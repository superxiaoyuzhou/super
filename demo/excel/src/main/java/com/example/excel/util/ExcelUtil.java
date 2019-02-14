package com.example.excel.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 读取解析Excel文件
 */
public class ExcelUtil {

    /**
     * 解析Excel文件
     * @param file
     * @param keys 可以为null,不为空指定集合的key值，顺序必须与Excel文件第一列对应
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> readExcel(MultipartFile file, String[] keys) throws IOException {
        return readExcel(file.getInputStream(), file.getOriginalFilename(),keys);
    }

    /**
     * 读取表格
     *
     * @param is       文件输入流
     * @param fileName 文件名，用来获取文件后缀
     * @param keys 可以为null,不为空指定集合的key值，顺序必须与Excel文件第一列对应
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> readExcel(InputStream is, String fileName,String[] keys) throws IOException {
        //根据输入流获取Excel对象
        Workbook wb = getWorkbook(is, fileName);
        //通过Excel对象解析文件
        List<Map<String, String>> list = getListMaps(wb,keys);
        return list;
    }

    /**
     * 默认后缀为.xlsx格式
     *
     * @param is
     * @param keys 可以为null,不为空指定集合的key值，顺序必须与Excel文件第一列对应
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> readExcel(InputStream is,String[] keys) throws IOException {
        return readExcel(is, ".xlsx",keys);
    }

    /**
     * 文件读取的字节数组
     * 默认后缀为.xlsx格式
     *
     * @param fileByte
     * @param keys 可以为null,不为空指定集合的key值，顺序必须与Excel文件第一列对应
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> readExcel(byte[] fileByte,String[] keys) throws IOException {
        //字节数组转io流，转文件
        InputStream is = new ByteArrayInputStream(fileByte);
        return readExcel(is, ".xlsx",keys);
    }

    /**
     * 获取Excel解析对象
     *
     * @param is
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream is, String fileName) throws IOException {
        Workbook wb;
        String extString = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(extString)) {
            return wb = new HSSFWorkbook(is);
        } else if (".xlsx".equals(extString)) {
            return wb = new XSSFWorkbook(is);
        } else {
            throw new RuntimeException("文件格式错误!(必须为“.xls”或“.xlsx”文件。)");
        }
    }

    private static List<Map<String, String>> getListMaps(Workbook wb,String[] columns) {
        List<Map<String, String>> list = new LinkedList<>();
        if (wb != null) {
            //用来存放表中数据
            list = new ArrayList<>();
            //获取第一个sheet
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行()
            Row row1 = sheet.getRow(0);
            //其它行
            Row row = null;
            //获取最大列数
            int colnum = 0;
            //获取第一行值的数组
            if(columns == null){
                colnum = row1.getPhysicalNumberOfCells();
                columns = new String[colnum];
                for (int i = 0; i < colnum; i++) {
                    Cell cell = row1.getCell(i);
                    columns[i] = cell.getStringCellValue();
                }
            }
            colnum = columns.length;
            //获取值
            String cellData = null;
            Map<String, String> map = null;
            for (int i = 1; i < rownum; i++) {
                map = new LinkedHashMap<>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        cellData = getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                } else {
                    break;
                }
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 根据cell表格,列值的类型解析获取单元格值
     *
     * @param cell
     * @return
     */
    public static String getCellFormatValue(Cell cell) {
        String cellValue = null;
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return formater.format(date);
                }
//                return String.valueOf(cell.getNumericCellValue());
            }
            cell.setCellType(CellType.STRING);
            cellValue = cell.getStringCellValue();

        } else {
            cellValue = "";
        }
        return cellValue;
    }
}

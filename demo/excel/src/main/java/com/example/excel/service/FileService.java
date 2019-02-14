package com.example.excel.service;

import com.piter.excel.entity.PromoteFeeSplit;
import com.piter.excel.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FileService {

    public Object upload(MultipartFile file) throws IOException {
        List<Map<String, String>> list = ExcelUtil.readExcel(file,null);
        List<PromoteFeeSplit> promoteFeeSplits = new ArrayList<>();
        PromoteFeeSplit promoteFeeSplit = null;
        for (Map<String, String> map : list){
            promoteFeeSplit = new PromoteFeeSplit();
            String str = (String)map.get("联系方式");
            log.info(str);
        }
        return list;

    }





    /**
     * 根据cell表格列值的类型解析获取值
     *
     * @param cell
     * @return
     */
    public static String getCellFormatValue(Cell cell) {
        String cellValue = null;
        if (cell != null) {
            /*//判断cell类型
            CellType cellTypeEnum = cell.getCellType();
            switch(cellTypeEnum){
                case NUMERIC:
                    cellValue = cell.getNumericCellValue();
                    break;
                case BOOLEAN:
                    cellValue = cell.getBooleanCellValue();
                    break;
                case ERROR:
                case _NONE:
                case FORMULA:
                case BLANK:
                default:
                    cellValue = cell.getStringCellValue();
            }*/
            log.info(String.valueOf(cell.getCellType()));
            if (false/*DateUtil.isCellDateFormatted(cell)*/) {
                //转换为日期格式YYYY-mm-dd
                Date date = cell.getDateCellValue();
                SimpleDateFormat sfd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                System.out.println(sfd.format(date));
            } else {
                cell.setCellType(CellType.STRING);
                cellValue = cell.getStringCellValue();
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }




}

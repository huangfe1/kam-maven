package com.dreamer.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * Created by huangfei on 28/04/2017.
 */
public class HfExcel {

    private static int XLS = 0;

    private static int XLSX = 1;

    /**
     * 创建多sheet
     *
     * @return XSSF -读写OOXML格式档案的功能 2007以后的版本 0
     * HSSF -Horrible SpreadSheet Format 2007以前的版本 1
     */
    private Workbook exportExcelWithType(List<String> sheetNames, List<List> sheetHeads, List<List<List>> sheetDatas, int type) {
        Workbook workbook = null;
        if (type == XLS) {
            workbook = new XSSFWorkbook();
        } else if (type == XLSX) {
            workbook = new HSSFWorkbook();
        }
        exportExcel(workbook, sheetNames, sheetHeads,sheetDatas);
        return workbook;
    }

    /**
     * 创建单sheet
     *
     * @param sheetNames
     * @param headNames
     * @param rowDatas
     * @return
     */
    private Workbook exportExcelWithType(String sheetName, List headNames, List<List> rowDatas, int type) {
        //sheet名
        List<String> sheetNames = new ArrayList<String>();
        sheetNames.add(sheetName);
        //headNames
        List<List> headNameses = new ArrayList<List>();
        headNameses.add(headNames);
        //数据
        List<List<List>> sheetDatas = new ArrayList<List<List>>();
        sheetDatas.add(rowDatas);
        return exportExcelWithType(sheetNames, headNameses, sheetDatas, type);
    }

    /**
     * 读取excel
     *
     * @param stream
     * @param columns
     * @param sheetIndex
     * @param titleRow
     * @return
     */
    private List<Map<String, String>> readExcel(InputStream stream, String[] columns, int sheetIndex, int titleRow) {
        List<Map<String,String>> rowDatas = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(stream);
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Iterator<Row> iterator = sheet.rowIterator();
            int rowCount = 0;
            while (iterator.hasNext()){
                Row row = iterator.next();
                if(rowCount==0){
                    continue;
                }
                Map<String,String> map = new HashMap<>();
                for (int i=0;i<columns.length;i++){
                    map.put(columns[i],getCellValue(row,i));
                }
                rowDatas.add(map);
            }
            IOUtils.closeQuietly(stream);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(stream);
        }
        return rowDatas;
    }

    private String getCellValue(Row row, int index) {
        Cell cell = row.getCell(index);
        String value = cell.getStringCellValue();
        return value;
    }

    //测试
    public void export() throws IOException {
        String sheetName = "sheet1";
        List sheetHeads = new ArrayList();
        sheetHeads.add("第一列");
        sheetHeads.add("第二列");
        List<List> sheetData = new ArrayList();
        for (int i = 0; i < 3; i++) {
            List<String> rowDatas = new ArrayList();
            for (int j = 0; j < 2; j++) {
                rowDatas.add(i + "-" + j);
            }
            sheetData.add(rowDatas);
        }
        HfExcel hfExcel = new HfExcel();
        File file = new File("/Users/Desktop/test.xls");
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Workbook workbook = hfExcel.exportExcelWithType(sheetName, sheetHeads, sheetData, HfExcel.XLS);
        workbook.write(fileOutputStream);
    }

    //-----------------------------------//

    /**
     * 生成excel文件
     *
     * @param workbook
     * @param sheetNames
     * @param sheetHeads
     * @param sheetDatas
     */
    private void exportExcel(Workbook workbook, List<String> sheetNames, List<List> sheetHeads, List<List<List>> sheetDatas) {
        for (int i = 0; i < sheetNames.size(); i++) {
            String sheetName = sheetNames.get(i);
            List<String> headNames = sheetHeads.get(i);
            List rowDatas = sheetDatas.get(i);
            Sheet sheet = workbook.createSheet(sheetName);

            Cell cell;
            //设置样式
            Font font = createFont(workbook);
            CellStyle cellStyle = createCellStyle(workbook);
            cellStyle.setFont(font);
            //头部标题行 0行  创建头部
            createHeadRow(sheet, headNames);
            createDataRow(sheet, rowDatas);
        }
    }

    /**
     * 创建单元格样式
     *
     * @param workbook
     * @return
     */
    private CellStyle createCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        return cellStyle;
    }

    /**
     * 创建字体
     *
     * @param workbook
     * @return
     */
    private Font createFont(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);//字体
        font.setFontHeightInPoints((short) 16);//字号
        return font;
    }

    /**
     * 创建头部行
     *
     * @param sheet
     * @param headName 头部标题
     */
    private void createHeadRow(Sheet sheet, List<String> headNames) {
        Row row = sheet.createRow(0);//0行 标题行
        for (int i = 0; i < headNames.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headNames.get(i));
        }
    }

    /**
     * 创建数据行
     *
     * @param sheet
     * @param rowDatas 所有的数据
     */
    private void createDataRow(Sheet sheet, List<List> rowDatas) {
        for (int i = 0; i < rowDatas.size(); i++) {
            List<String> rowData = rowDatas.get(i);
            Row row = sheet.createRow(i + 1);//0行 标题行
            for (int j = 0; j < rowData.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowData.get(i));
            }
        }
    }


}


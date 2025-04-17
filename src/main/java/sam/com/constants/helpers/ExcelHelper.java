package sam.com.constants.helpers;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import sam.com.constants.Utils.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelHelper {

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook wb;
    private Sheet sh;
    private Cell cell;
    private Row row;
    private CellStyle cellstyle;
    private Color mycolor;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();
    private static final String DEFAULT_EXCEL_PATH = "src/test/resources/DataExcel/ExcelData.xlsx";
    private static final String EXCEL_SHEET_NAME = "Sheet 1";

    public static ExcelHelper getExcelHelper(String SHEET_NAME) {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(SHEET_NAME);
        return excelHelper;
    }

    public void setExcelFile(String SHEET_NAME) {
        try {
            File f = new File(DEFAULT_EXCEL_PATH);

            if (!f.exists()) {
                throw new Exception("File doesn't exist.");// ném ra lỗi và dừng lại tại dòng code đó.
            }

            fis = new FileInputStream(DEFAULT_EXCEL_PATH);
            wb = WorkbookFactory.create(fis);// thư viện giúp đọc data trong excel
            sh = wb.getSheet(SHEET_NAME);// hàm của apache poi support

            if (sh == null) {
                throw new Exception("Sheet name doesn't exist.");
            }

            this.excelFilePath = DEFAULT_EXCEL_PATH;

            //adding all the column header names to the map 'columns'
            sh.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            LogUtils.error(e.getMessage());
        }
    }

    public String getCellData(int columnIndex, int rowIndex) {
        try {
            cell = sh.getRow(rowIndex).getCell(columnIndex);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    //Gọi ra hàm này dùng cho rõ ràng phải làm file excel có tên cột mới dùng hàm này
    public String getCellData(String columnName, int rowIndex) {// int la vi tri index
        Integer colum = columns.get(columnName);
        return getCellData(colum, rowIndex);
    }

    //set by column index
    public void setCellData(String text, int columnIndex, int rowIndex) {
        try {
            row = sh.getRow(rowIndex);
            if (row == null) {
                row = sh.createRow(rowIndex);
            }
            cell = row.getCell(columnIndex);

            if (cell == null) {
                cell = row.createCell(columnIndex);
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
            LogUtils.info("set data is successful");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //set by column name
    public void setCellData(String text, String columnName, int rowIndex) {
        try {
            row = sh.getRow(rowIndex);
            if (row == null) {
                row = sh.createRow(rowIndex);
            }
            cell = row.getCell(columns.get(columnName));

            if (cell == null) {
                cell = row.createCell(columns.get(columnName));
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
            LogUtils.info("set data is successful");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static List<Map<String, String>> getAllData() {
        List<Map<String, String>> dataList = new ArrayList<>();

        try {
            // Load workbook và sheet
            Workbook workbook = WorkbookFactory.create(new File(DEFAULT_EXCEL_PATH));
            Sheet sheet = workbook.getSheet(EXCEL_SHEET_NAME);

            // Lấy dòng tiêu đề (header)
            int rowCount = sheet.getLastRowNum();
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();

            // Duyệt từng dòng dữ liệu
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> dataMap = new HashMap<>();
                for (int j = 0; j < colCount; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell dataCell = row.getCell(j);

                    String header = headerCell.getStringCellValue().trim();
                    String value = (dataCell == null) ? "" : dataCell.toString().trim();

                    dataMap.put(header, value);
                }

                dataList.add(dataMap);
            }

            workbook.close();

        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi đọc dữ liệu Excel: " + e.getMessage(), e);
        }

        return dataList;
    }
}





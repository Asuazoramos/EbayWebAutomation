package database;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConnectToExcelFiles {

    public static XSSFWorkbook wb = null;
    public static XSSFSheet sheet = null;
    public static Cell cell = null;
    public static FileOutputStream fio = null;
    public static int numberOfRows, numberOfCol, rowNum;

    public static String[] getCellValuesFromSheet(String path, int sheetIndex) throws IOException {
        String[] data = {};
        File file = new File(path);
        try (FileInputStream fis = new FileInputStream(file)) {
              wb = new XSSFWorkbook(fis);
            sheet = wb.getSheetAt(sheetIndex);
            numberOfRows = sheet.getLastRowNum();
            numberOfCol = sheet.getRow(0).getLastCellNum();
            data = new String[numberOfRows + 1];

            for (int i = 0; i < data.length; i++) {
                XSSFRow rows = sheet.getRow(i);
                for (int j = 0; j < numberOfCol; j++) {
                    XSSFCell cell = rows.getCell(j);
                    String cellData = getCellValue(cell);
                    data[i] = cellData;
                }
            }
            wb.close();
            return data;
        }

    }



    public static String getCellValue(XSSFCell cell) {
        Object value = null;

        switch (cell.getCellTypeEnum()) {
            case _NONE:
                break;
            case NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
        }
        return value.toString();

    }

    public void writeBack(String value, int rowNum) throws IOException {
        wb = new XSSFWorkbook();
        sheet = wb.createSheet();
        Row row = sheet.createRow(rowNum);
        row.setHeightInPoints(10);

        fio = new FileOutputStream(new File("ExcelFile.xls"));
        wb.write(fio);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            row.createCell(i);
            cell.setCellValue(value);
        }
        fio.flush();
        fio.close();
        wb.close();

    }

    public String getData( String sheetName,int row, int column){
        sheet=wb.getSheet(sheetName);
        String data=sheet.getRow(row).getCell(column).getStringCellValue();
        return data;
    }

    public static void main(String[] args) throws IOException {
        getCellValuesFromSheet("/Users/siembrahielotrucho/Documents/EbayTestAutomation/Ebay/data/Ebay-items-for-search.xlsx", 0);
    }


    public static String[] getCellValuesFromSheet(String s) {
        return new String[0];
    }
}

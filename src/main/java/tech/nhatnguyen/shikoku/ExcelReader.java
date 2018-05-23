package tech.nhatnguyen.shikoku;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelReader {
    public static HashMap<String, String> storeValues = new HashMap();


    public static HashMap<String, String> dataMap(String filepath, String sheetName, String index) {
        int index_row = Integer.parseInt(index) - 1;
        return data(filepath, sheetName).get(index_row);
    }

    public static List<HashMap<String, String>> data(String filepath, String sheetName) {
        List<HashMap<String, String>> mydata = new ArrayList<>();
        try {
            File file = new File(ExcelReader.class.getClassLoader().getResource(filepath).getFile());
            FileInputStream fs = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            Row HeaderRow = sheet.getRow(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row currentRow = sheet.getRow(i);
                HashMap<String, String> currentHash = new HashMap<String, String>();
                for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                    Cell currentCell = currentRow.getCell(j);
                    switch (currentCell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            currentHash.put(HeaderRow.getCell(j).getStringCellValue(), "");
                            break;
                    }
                }
                mydata.add(currentHash);
            }
            System.out.println(mydata);
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mydata;
    }
}
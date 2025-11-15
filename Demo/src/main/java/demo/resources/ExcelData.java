package demo.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	
	public static List<Object> getExcelData(String filePath, String TestCaseName, String SheetName, String columnName) throws IOException {
		
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		List<Object> list = new ArrayList<Object>();
		
		int sheets = workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++) {
			String sheetName = workbook.getSheetName(i);
			if(sheetName.equalsIgnoreCase(SheetName)) {
				int count = 0;
				int column = 0;
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows  = sheet.rowIterator();
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();
				
				while(cells.hasNext()) {
					Cell cell = cells.next();
					String headerName = cell.getStringCellValue();
					
					if(headerName.equalsIgnoreCase(columnName)) {
						column = count;
					}
					count++;
				}
				while(rows.hasNext()) {
					Row row = rows.next();
					String testCaseName = row.getCell(column).getStringCellValue();
					if(testCaseName.equalsIgnoreCase(TestCaseName)) {
						Iterator<Cell> cell = row.cellIterator();
						while(cell.hasNext()) {
							Cell c = cell.next();
							String cellValue = c.getStringCellValue();
							list.add(cellValue);
						}
					}
				}
			}
		}
		return list;
	}

}

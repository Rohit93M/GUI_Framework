package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcel {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\ROHIT M\\OneDrive\\Desktop\\New folder\\testScriptData02.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		for (int i = 1; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			String col1Data = row.getCell(0).toString();
			String col2Data = row.getCell(1).toString();
			System.out.println(col1Data + "\t" + col2Data);
		}
		workbook.close();
	}
}

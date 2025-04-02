
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetname) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetname);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}

	public String getCellData(String sheetname, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetname);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public int getCellCount(String sheetname, int rownum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetname);
		ws.getRow(rownum);
		int cellCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return cellCount;
	}

	public void setCellData(String sheetname, int rownum, int colcount, String data) throws IOException {

		File xlfile = new File(path);

		if (!xlfile.exists()) {
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);

		if (wb.getSheetIndex(sheetname) == -1) {
			wb.createSheet(sheetname);
			ws = wb.getSheet(sheetname);

			if (ws.getRow(rownum) == null) {
				ws.createRow(rownum);
				row = ws.getRow(rownum);

				cell = row.createCell(colcount);
				cell.setCellValue(data);
				fo = new FileOutputStream(path);
				wb.write(fo);
				wb.close();
				fo.close();
				fi.close();
			}
		}
	}

	public void fillGreenColour(String sheetname, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetname);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		wb.close();
		fi.close();
		fo.close();
	}

	public void fillRedColour(String sheetname, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetname);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		wb.close();
		fi.close();
		fo.close();
	}
}


/*
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility {

    private String path;
    private FileInputStream fi;
    private FileOutputStream fo;
    private XSSFWorkbook wb;
    private XSSFSheet ws;
    private XSSFRow row;
    private XSSFCell cell;
    private CellStyle style;

    // Constructor to initialize file path
    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetname) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetname);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }

    public int getCellCount(String sheetname, int rownum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetname);
        row = ws.getRow(rownum);
        int cellCount = (row != null) ? row.getLastCellNum() : 0; // Handle null rows
        wb.close();
        fi.close();
        return cellCount;
    }

    public String getCellData(String sheetname, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetname);
        row = ws.getRow(rownum);
        cell = (row != null) ? row.getCell(colnum) : null;

        DataFormatter formatter = new DataFormatter();
        String data = (cell != null) ? formatter.formatCellValue(cell) : "";

        wb.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetname, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);

        if (!xlfile.exists()) {
            wb = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            wb.write(fo);
            wb.close();
            fo.close();
        }

        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetname);
        
        if (ws == null) { 
            ws = wb.createSheet(sheetname);
        }

        if (ws.getRow(rownum) == null) {
            ws.createRow(rownum);
        }
        
        row = ws.getRow(rownum);
        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fo.close();
        fi.close();
    }

    public void fillGreenColour(String sheetname, int rownum, int colnum) throws IOException {
        applyCellColor(sheetname, rownum, colnum, IndexedColors.GREEN);
    }

    public void fillRedColour(String sheetname, int rownum, int colnum) throws IOException {
        applyCellColor(sheetname, rownum, colnum, IndexedColors.RED);
    }

    private void applyCellColor(String sheetname, int rownum, int colnum, IndexedColors color) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetname);
        row = ws.getRow(rownum);
        cell = (row != null) ? row.getCell(colnum) : null;

        if (cell != null) {
            style = wb.createCellStyle();
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);

            fo = new FileOutputStream(path);
            wb.write(fo);
            fo.close();
        }

        wb.close();
        fi.close();
    }
}
*/

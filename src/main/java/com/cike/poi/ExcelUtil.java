package com.cike.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static void getExcel(Map<String[], List<DataTable>> map)
			throws Exception {
		// 得到数据库表信息
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("数据库");
		int i = 0;
		for (String[] s : map.keySet()) {
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
			// 创建一行
			XSSFRow row = sheet.createRow(i);
			// 创建一列
			XSSFCell cell = (XSSFCell) row.createCell(0);
			cell.setCellValue(s[0] + "(" + s[1] + ")");
			CellStyle style = workbook.createCellStyle();
			style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(style);
			// 创建一行
			XSSFRow row2 = sheet.createRow(i + 1);
			// 创建一列
			XSSFCell cell2 = (XSSFCell) row2.createCell(0);
			cell2.setCellValue("列名");
			cell2 = (XSSFCell) row2.createCell(1);
			cell2.setCellValue("类型");
			cell2 = (XSSFCell) row2.createCell(2);
			cell2.setCellValue("说明");
			cell2 = (XSSFCell) row2.createCell(3);
			cell2.setCellValue("备注");
			i = i + 2;
			for (DataTable d : map.get(s)) {
				// 创建一行
				XSSFRow row3 = sheet.createRow(i);
				// 创建一列
				XSSFCell cell3 = (XSSFCell) row3.createCell(0);
				cell3.setCellValue(d.getField());
				cell3 = (XSSFCell) row3.createCell(1);
				cell3.setCellValue(d.getType());
				cell3 = (XSSFCell) row3.createCell(2);
				String key = "";
				if (d.getKey() != null) {
					if (d.getKey().equals("PRI")) {
						key = "主键";
					}
					if (d.getKey().equals("MUL")) {
						key = "外键";
					}
				}
				cell3.setCellValue(key);
				cell3 = (XSSFCell) row3.createCell(3);
				cell3.setCellValue(d.getComment());
				i++;
			}
		}
		FileOutputStream out = new FileOutputStream(new File("d://"
				+ Config.FILENAME + ".xlsx"));
		workbook.write(out);
		out.close();
		System.out.println("生成成功！");
	}

}

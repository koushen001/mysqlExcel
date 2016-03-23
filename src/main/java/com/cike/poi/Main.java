package com.cike.poi;

public class Main {

	public static void main(String[] args) {
		try {
			ExcelUtil.getExcel(DataUtil.getTable());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

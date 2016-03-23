package com.cike.poi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {
	/**
	 * 得到数据库表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String[], List<DataTable>> getTable() throws Exception {
		// 加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 获得数据库连接
		Connection connect = DriverManager.getConnection(Config.DATABASEIP
				+ Config.DATABASE, Config.USERNAME, Config.PASSWORD);
		// 查询所有的表
		String sql = "SELECT TABLE_NAME,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='"
				+ Config.DATABASE + "'";
		PreparedStatement statement = connect.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		Map<String[], List<DataTable>> map = new HashMap<String[], List<DataTable>>();
		// 得到所有的表
		while (resultSet.next()) {
			String tName = resultSet.getString(1);
			List<DataTable> list = new ArrayList<DataTable>();
			String sql2 = "show full columns from " + resultSet.getString(1);
			PreparedStatement statement2 = connect.prepareStatement(sql2);
			ResultSet resultSet2 = statement2.executeQuery();
			while (resultSet2.next()) {
				DataTable d = new DataTable();
				d.setField(resultSet2.getString(1));
				d.setType(resultSet2.getString(2));
				d.setKey(resultSet2.getString(5));
				d.setComment(resultSet2.getString(9));
				list.add(d);
			}
			map.put(new String[] { tName, resultSet.getString(2) }, list);
		}
		return map;
	}
}

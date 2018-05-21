package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	Connection connection = null;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public DAO() {
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MYBATIS", "MYBATIS$");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String[]> select(String query) {
		Statement statement = null;
		ResultSet resultSet = null;
		List<String[]> list = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			int colCnt = resultSet.getMetaData().getColumnCount();
			list = new ArrayList<String[]>();

			while(resultSet.next()) {
				String[] row = new String[colCnt];

				for(int i = 0; i < colCnt; i++) {
					row[i] = resultSet.getString(i + 1);
					System.out.println(row[i]);
				}
				
				list.add(row);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	public String[] getRow(String email) {
		String ss = "SELECT EMAIL, PW  FROM ADMIN WHERE EMAIL = '" + email + "'";
		List<String[]> list = select(ss);
		String[] row = null;
		
		if(list != null)
			row = list.get(0);
		
		return row;
	}
}

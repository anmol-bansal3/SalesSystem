package com.wipro.sales.util;
import java.sql.*;
public class DBUtil {
	public static Connection getDBConnection() throws Exception{
		Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SalesSystem","root","password");
		return con;
	}
}

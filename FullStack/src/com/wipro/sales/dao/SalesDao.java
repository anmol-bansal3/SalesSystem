package com.wipro.sales.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.text.*;
import com.wipro.sales.bean.Sales;
import com.wipro.sales.util.DBUtil;


public class SalesDao {
	public int insertSales(Sales sales) throws Exception{
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		String record = "INSERT INTO `TBL_SALES`(`salesID`, `productID`, `salesDate`, `quantitySold`, `salesPricePerUnit`)" + "VALUES ('"+Sales.getSalesID()+"','"+Sales.getProductID()+"','"+Sales.salesDate()+"','"+Sales.getQuantitySold()+"','"+Sales.getSalesPricePerUnit()+"')";
		if(st.executeUpdate(record) == 1) {
			return 1;
		}
		return 0;
	}
	public String generateSalesID(java.util.Date salesDate) throws Exception{
		DateFormat df = new SimpleDateFormat("yy");
	    String formattedDate = df.format(Calendar.getInstance().getTime());
	    
	    Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT `id` FROM `TBL_SALES`");
		
		String salesId = formattedDate + rs.getInt("id");
		
		return salesId;	
	}
	public Arraylist<SalesReport> getSalesReport() { 
		
	}
}

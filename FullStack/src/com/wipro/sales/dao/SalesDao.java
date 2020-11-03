package com.wipro.sales.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.text.*;
import com.wipro.sales.bean.*;
import com.wipro.sales.util.DBUtil;

public class SalesDao {
	public int insertSales(Sales sales) throws Exception{
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		Sales s1 = new Sales();
		java.sql.Date sqlDate = new java.sql.Date(s1.salesDate.getTime());
		String record = "INSERT INTO `TBL_SALES`(`salesID`, `productID`, `salesDate`, `quantitySold`, `salesPricePerUnit`)" + "VALUES ('"+s1.getSalesID()+"','"+s1.getProductID()+"','"+sqlDate+"','"+s1.getQuantitySold()+"','"+s1.getSalesPricePerUnit()+"')";
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
		
		String salesId = "" + formattedDate + rs.getInt("id");
		return salesId;	
	}
	
	public ArrayList<SalesReport> getSalesReport() throws Exception{ 
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		st.executeQuery("CREATE VIEW V_SALES_REPORT AS SELECT `salesID`, `salesDate`, tst.`productID`, `productName`, `quantitySold`, `productUnitPrice`, `salesPricePerUnit`, (salesPricePerUnit - productUnitPrice) as `profitAmount`  FROM `TBL_SALES` tsa, `TBL_STOCK` tst ORDER BY profitAmount DESC, salesID ASC");
		ResultSet rs = st.executeQuery("SELECT * FROM `V_SALES_REPORT`");
		
		ArrayList<SalesReport> sales = new ArrayList<SalesReport>();
		while(rs.next()){
			SalesReport temp = new SalesReport();
			temp.setSalesID(rs.getString("salesID"));
			temp.setProductID(rs.getString("productID"));
			temp.setProductName(rs.getString("productName"));
			temp.setQuantitySold(rs.getInt("quantitySold"));
			temp.setProductUnitPrice(rs.getDouble("productUnitPrice"));
			temp.setSalesPricePerUnit(rs.getDouble("salesPricePerUnit"));
			temp.setProfitAmount(rs.getDouble("salesPricePerUnit")-rs.getDouble("productUnitPrice"));
			
			sales.add(temp);
		}
		return sales;
	}
}

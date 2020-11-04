package com.wipro.sales.service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.wipro.sales.bean.*;
import com.wipro.sales.dao.*;
import com.wipro.sales.util.DBUtil;

public class Administrator {
	public String insertStock(Product stockobj) throws Exception {
		if(stockobj.getProductName() == null)
			return "Data not Valid for insertion \n";
		else if(stockobj.getProductName().length() < 2) 
			return "Length should be greater than 2 \n";
		else {
			StockDao d1 = new StockDao();
			d1.insertStock(stockobj);
			
			String result = d1.generateProductID(stockobj.getProductName());
			if(result != null) {
				stockobj.setProductID(result);
				return "Completed insert stock\n";
			}
			else {
				return "Data not Valid for insertion\n";
			}
		}
	}

	public String deleteStock(String ProductID) throws Exception {
		StockDao d1 = new StockDao();
		if(d1.deleteStock(ProductID) == 1) {
			return "deleted\n";
		}
		else {
			return "record cannot be deleted\n";
		}
	}
	
	@SuppressWarnings("unused")
	public String insertSales(Sales salesobj) throws Exception {
		
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		SalesDao sda = new SalesDao();
		StockDao sdo = new StockDao();
		
		java.sql.Date sqlDate = new java.sql.Date(salesobj.salesDate.getTime());
		Date curDate = new Date(new java.util.Date().getTime());
		
		String record = "SELECT tst.`productID` FROM `TBL_STOCK` tst, `TBL_SALES` tsa WHERE tst.`productID` = '" + salesobj.getProductID() + "'";
		ResultSet rs1 = st.executeQuery(record);
		
		try {
			rs1.next();
				rs1.getString("productID");
			
		}
		catch(SQLException e){
			return "Unknown Product for sales\n";
		}
		
		if(salesobj == null) {
			return "Object not valid for insertion\n";
		}
		
		String select = "select `quantityOnHand` from TBL_STOCK WHERE productID ='" + salesobj.getProductID() + "'";
		ResultSet rs = st.executeQuery(select);
		
		while(rs.next())
	    	if (rs.getInt("quantityOnHand") < salesobj.getQuantitySold()) {
	      		return "Not enough stock on hand for sale\n";
	    	}
		
		if (sqlDate.compareTo(curDate) > 0) {
			return "Invalid Date\n";
		}
		
		if (sda.insertSales(salesobj) == 0) {
		  return "Error in Sales insertion\n";
		}
		salesobj.setSalesID(sda.generateSalesID(salesobj.salesDate, salesobj.getProductID()));
		st.executeUpdate("UPDATE `TBL_SALES` SET `salesID`='" + salesobj.getSalesID() + "' WHERE `productID` ='" + salesobj.getProductID() + "'");
		
		
		if (sdo.updateStock(salesobj.getProductID(), salesobj.getQuantitySold()) == -1) {
		   return "Error in stock updation\n";
		}

		return "Sales Completed\n";
	}
	
	public ArrayList<SalesReport> getSalesReport() throws Exception {
		SalesDao d1 = new SalesDao();
		return d1.getSalesReport();
	}
}
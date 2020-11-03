package com.wipro.sales.service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.wipro.sales.bean.*;
import com.wipro.sales.dao.*;
import com.wipro.sales.util.DBUtil;

public class Administrator {
	public String insertStock(Product stockobj) throws Exception {
		if(stockobj.getProductID() == "" || stockobj.getProductID() == "")
			return "Data not Valid for insertion";
		else if(stockobj.getProductID().length() > 2) 
			return "Data not Valid for insertion";
		else {
			StockDao d1 = new StockDao();
			String result = d1.generateProductID(stockobj.getProductName());
			if(result != null) {
				stockobj.setProductID(result);
				d1.insertStock(stockobj);
				return result;
			}
			else {
				return "Data not Valid for insertion";
			}
		}
	}

	public String deleteStock(String ProductID) throws Exception {
		StockDao d1 = new StockDao();
		if(d1.deleteStock(ProductID) == 1) {
			return "deleted";
		}
		else {
			return "record cannot be deleted";
		}
	}
	
	public String insertSales(Sales salesobj) throws Exception {
		
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		SalesDao sda = new SalesDao();
		StockDao sdo = new StockDao();
		Sales s1 = new Sales();
		Date date = s1.salesDate;
		java.sql.Date sqlDate = new java.sql.Date(s1.salesDate.getTime());
		Date curDate = new Date(new java.util.Date().getTime());
		
		String record = "SELECT tst.`productID` FROM `TBL_STOCK` tst, `TBL_SALES` tsa WHERE tst.`productID` = '" + salesobj.getProductID() + "'";
		
		if(salesobj.getProductID() == null || salesobj.getSalesID() == null) {
			return "Object not valid for insertion";
		}
		if(st.executeQuery(record) == null) {
			return "Unknown Product for sales";
		}
		
		String select = "select `quantityOnHand` from TBL_STOCK where product_id ='" + s1.getProductID() + "'";
		ResultSet rs = st.executeQuery(select);
	    if (rs.getInt("quantityOnHand") < s1.getQuantitySold()) {
	      return "Not enough stock on hand for sale";
	    }
		
		if (sqlDate.compareTo(curDate) > 0) {
			return "Invalid Date";
		}
		
		
		s1.setSalesID(sda.generateSalesID(date));
		
		if (sda.insertSales(salesobj) == -1) {
		  return "Error in Sales insertion";
		}

		if (sdo.updateStock(s1.getProductID(), s1.getQuantitySold()) == -1) {
		   return "Error in stock updation";
		}

		return "Sales Completed";
	}
	
	public ArrayList<SalesReport> getSalesReport() throws Exception {
		SalesDao d1 = new SalesDao();
		return d1.getSalesReport();
	}
}
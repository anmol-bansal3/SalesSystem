package com.wipro.sales.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.wipro.sales.bean.*;
import com.wipro.sales.util.DBUtil;

public class StockDao {
	public int insertStock(Product sales) throws Exception{
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		
		String record = "INSERT INTO `TBL_STOCK`(`productID`, `productName`, `quantityOnHand`, `productUnitPrice`, `reorderLevel`)" + "VALUES ('null', '"+sales.getProductName()+"','"+sales.getQuantityOnHand()+"','"+sales.getProductUnitPrice()+"','"+sales.getReorderLevel()+"')";
		if(st.executeUpdate(record) == 1) {
			return 1;
		}
		return 0;
	}
	
	public String generateProductID(String Name) throws Exception{
	    Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT `id` FROM `TBL_STOCK` WHERE `productName`='" + Name + "'");
		
		String productId = "NULL";
		while(rs.next()) {
			productId = "" + Name.charAt(0) + Name.charAt(1) + rs.getInt("id");
		}
		st.executeUpdate("UPDATE `TBL_STOCK` SET `productID`='" + productId + "' WHERE `productName` ='" + Name + "'");
		return productId;	
	}
	
	public int updateStock(String productId, int soldQty) throws Exception {
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM `TBL_STOCK` WHERE `productID`='" + productId + "'");
		int value = 0;
		if(rs.next())
			value = rs.getInt("quantityOnHand")-soldQty;
		String record = "UPDATE `TBL_STOCK` SET `quantityOnHand`='" + value + "'WHERE `productID`='" + productId + "'";
		if(st.executeUpdate(record) == 1) {
			return 1;
		}
		return 0;
	}
	
	public int deleteStock(String productID) throws Exception {
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		String delete = "DELETE FROM `TBL_STOCK` WHERE `productID`='" + productID + "'";
		if(st.executeUpdate(delete) == 1) {
			return 1;
		}
		return 0;
	}
	
	public Product getStock(String productID) throws Exception {
		Product temp = new Product();
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM `TBL_STOCK` WHERE `productID`='" + productID + "'");
		while(rs.next()) {
			temp.setProductID(rs.getString("productID"));
			temp.setProductName(rs.getString("productName"));
			temp.setQuantityOnHand(rs.getInt("quantityOnHand"));
			temp.setProductUnitPrice(rs.getDouble("productUnitPrice"));
			temp.setReorderLevel(rs.getInt("reorderLevel"));
		}
		return temp;
	}
}

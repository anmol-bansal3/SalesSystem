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
		
		String record = "INSERT INTO `TBL_STOCK`(`productID`, `productName`, `quantityOnHand`, `productUnitPrice`, `reorderLevel`)" + "VALUES ('"+sales.getProductID()+"','"+sales.getProductName()+"','"+sales.getQuantityOnHand()+"','"+sales.getProductUnitPrice()+"','"+sales.getReorderLevel()+"')";
		if(st.executeUpdate(record) == 1) {
			return 1;
		}
		return 0;
	}
	
	public String generateProductID(String productName) throws Exception{
	    Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT `id` FROM `TBL_STOCK` WHERE `productName`='" + productName + "'");
		
		String productId = "" + productName.charAt(0) + productName.charAt(1) + rs.getInt("id");
		return productId;	
	}
	
	public int updateStock(String productId, int soldQty) throws Exception {
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM `TBL_STOCK` WHERE `productID`='" + productId + "'");
		int value = rs.getInt("quantityOnHand")-soldQty;
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
		
		temp.setProductID(rs.getString("productID"));
		temp.setProductName(rs.getNString("productName"));
		temp.setQuantityOnHand(rs.getInt("quantityOnHand"));
		temp.setProductUnitPrice(rs.getDouble("productUnitPrice"));
		temp.setReorderLevel(rs.getInt("reorderLevel"));
		
		return temp;
	}
}

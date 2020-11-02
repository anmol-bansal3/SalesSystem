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
		Product p1 = new Product();
		String record = "INSERT INTO `TBL_STOCK`(`productID`, `productName`, `quantityOnHand`, `productUnitPrice`, `reorderLevel`)" + "VALUES ('"+p1.getProductID()+"','"+p1.getProductName()+"','"+p1.getQuantityOnHand()+"','"+p1.getProductUnitPrice()+"','"+p1.getReorderLevel()+"')";
		if(st.executeUpdate(record) == 1) {
			return 1;
		}
		return 0;
	}
	public String generateProductID(String productName) throws Exception{
	    Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT `id` FROM `TBL_STOCK`");
		Product p1 = new Product();
		String name = p1.getProductName();
			
		String productId = "" + name.charAt(0) + name.charAt(1) + rs.getInt("id");
		return productId;	
	}
	public int updateStock(String productId, int soldQty) throws Exception {
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM `TBL_STOCK`");
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

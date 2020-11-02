package com.wipro.sales.bean;
import java.util.*;

public class Sales {
	private static String salesID, productID;
	java.util.Date date = new java.util.Date();
	java.sql.Date salesDate = new java.sql.Date(date.getTime());
	
	private static int quantitySold;
	private double salesPricePerUnit;
	public static String getSalesID() {
		return salesID;
	}
	public void setSalesID(String salesID) {
		this.salesID = salesID;
	}
	public static String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public static int getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	public static double getSalesPricePerUnit() {
		return salesPricePerUnit;
	}
	public void setSalesPricePerUnit(double salesPricePerUnit) {
		this.salesPricePerUnit = salesPricePerUnit;
	}
	public static String salesDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

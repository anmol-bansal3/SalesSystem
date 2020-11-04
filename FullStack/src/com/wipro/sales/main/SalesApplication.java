package com.wipro.sales.main;

import java.util.*;
import com.wipro.sales.bean.*;
import com.wipro.sales.dao.*;
import com.wipro.sales.service.Administrator;

public class SalesApplication {
	public static void main(String args[]) throws Exception {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		Administrator a1 = new Administrator();
		
		while(true){
			System.out.println("1.) Insert Stock");
			System.out.println("2.) Delete Stock");
			System.out.println("3.) Insert Sales");
			System.out.println("4.) View Sales Report");
			System.out.println("5.) Get Stocks");
			System.out.println("Press any other key to exit.");
			int t = scan.nextInt();
			
			if(t == 1) {
				Product p1 = new Product();
				System.out.print("Name of the Product: ");
				String name = scan.next();
				p1.setProductName(name);
//				System.out.println();
				System.out.print("Set product unit price: ");
				p1.setProductUnitPrice(scan.nextDouble());
//				System.out.println();
				System.out.println("Set quantity on Hand: ");
				p1.setQuantityOnHand(scan.nextInt());
//				System.out.println();
				System.out.println("Set reorder level: ");
				p1.setReorderLevel(scan.nextInt());
//				System.out.println();
				
				String result = a1.insertStock(p1);
				System.out.print(result);
			}
			else if(t == 2) {
				System.out.println("Select ProductID for deletion: ");
				String result = a1.deleteStock(scan.next());
				System.out.print(result);
			}
			else if(t == 3) {
				Sales s1 = new Sales();
				System.out.println("Name of the product: ");
				s1.setProductID(scan.next());
//				System.out.println();
				System.out.println("Set Quantity Sold: ");
				s1.setQuantitySold(scan.nextInt());
//				System.out.println();
				System.out.println("Set sales price per unit: ");
				s1.setSalesPricePerUnit(scan.nextDouble());
//				System.out.println();
				
				String result = a1.insertSales(s1);
				System.out.print(result);
			}
			else if(t == 4) {
				ArrayList<SalesReport> result = a1.getSalesReport();
				for(int i = 0; i < result.size(); i++) {
					System.out.println("SalesId: "+ result.get(i).getSalesID() + "\nProductId: "+ result.get(i).getProductID() + "\nProductName: "+ result.get(i).getProductName() + "\nQuantitySold: "+ result.get(i).getQuantitySold() + "\nProductUnitPrice: "+ result.get(i).getProductUnitPrice() + "\nSalesPricePerUnit: "+ result.get(i).getSalesPricePerUnit() + "\nProfitAmount: "+ result.get(i).getProfitAmount() + "\n");
				}
			}
			else if(t == 5) {
				StockDao s1 = new StockDao();
				System.out.println("Select ProductID: ");
				Product result = s1.getStock(scan.next());
				System.out.println("productID: "+ result.getProductID() + "\nProductName: "+ result.getProductName() + "\nProductUnitPrice: "+ result.getProductUnitPrice() + "\nQuantityOnHand: " + result.getQuantityOnHand() + "\n");
			}
			else {
				break;
			}
		}
	}
}

package com.wipro.sales.main;

import java.util.*;
import com.wipro.sales.bean.*;
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
			System.out.println("Press any other key to exit.");
			int t = scan.nextInt();
			
			if(t == 1) {
				Product p1 = new Product();
				String name = scan.next();
				p1.setProductName(name);
				p1.setProductUnitPrice(scan.nextDouble());
				p1.setQuantityOnHand(scan.nextInt());
				p1.setReorderLevel(scan.nextInt());
				
				String result = a1.insertStock(p1);
				System.out.print(result);
			}
			else if(t == 2) {
				a1.deleteStock(scan.next());
			}
			else if(t == 3) {
				Sales s1 = new Sales();
				s1.setQuantitySold(scan.nextInt());
				s1.setSalesPricePerUnit(scan.nextDouble());
				s1.setProductID(scan.next());
				
				String result = a1.insertSales(s1);
				System.out.print(result);
			}
			else if(t == 4) {
				ArrayList<SalesReport> result = a1.getSalesReport();
				for(int i = 0; i < result.size(); i++) {
					System.out.println("SalesId: "+ result.get(i).getSalesID() + "\nProductId: "+ result.get(i).getProductID() + "\nProductName: "+ result.get(i).getProductName() + "\nQuantitySold: "+ result.get(i).getQuantitySold() + "\nProductUnitPrice: "+ result.get(i).getProductUnitPrice() + "\nSalesPricePerUnit: "+ result.get(i).getSalesPricePerUnit() + "\nProfitAmount: "+ result.get(i).getProfitAmount() + "\n");
				}
			}
			else {
				break;
			}
		}
	}
}

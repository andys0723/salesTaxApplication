package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utility.FileUtility;

import model.ConcreteItem;
import model.ShoppingBakets;

public class WriteFileFromShoppingBasketsService {
	private static WriteFileFromShoppingBasketsService instance = null;
	public WriteFileFromShoppingBasketsService(){
		
	}
	
	public static WriteFileFromShoppingBasketsService getInstance(){
		if(instance==null){
			instance =  new WriteFileFromShoppingBasketsService();
		}
		return instance;
	}
	
	public void write(File file, ShoppingBakets<ConcreteItem> shoppingBaskets){
        List<String> resultList = new  ArrayList<String>(); 
        SaleTaxCalcultorService saleTaxCalculator = SaleTaxCalcultorService.getInstance();
        for(ConcreteItem item:shoppingBaskets){
        	StringBuilder tmp = new StringBuilder();
        	tmp.append(item.getQuantity())
        	.append(" ")
        	.append(item.getName())
        	.append(" : ")
        	.append(saleTaxCalculator.calculateItemsPriceWithTax(item))
        	.append(System.getProperty("line.separator"));
        	resultList.add(tmp.toString());
        }
        resultList = writeTotalSaleTax(shoppingBaskets, resultList, saleTaxCalculator);
        resultList = writeTotalCost(shoppingBaskets, resultList, saleTaxCalculator);                
		try {
			FileUtility.write(file, resultList);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}

	private List<String> writeTotalSaleTax(ShoppingBakets<ConcreteItem> shoppingBaskets,
			List<String> resultList, SaleTaxCalcultorService saleTaxCalculator) {
		StringBuilder totalSaleTaxBuilder = new StringBuilder();
        totalSaleTaxBuilder.append(saleTaxCalculator.calculateTotalSaleTax(shoppingBaskets));
        resultList.add("Sales Taxes: " + totalSaleTaxBuilder.toString() +"\n");
        return resultList;
	}

	private List<String> writeTotalCost(ShoppingBakets<ConcreteItem> shoppingBaskets,
			List<String> resultList, SaleTaxCalcultorService saleTaxCalculator) {
		StringBuilder totalSaleTaxBuilder = new StringBuilder();
        totalSaleTaxBuilder.append(saleTaxCalculator.calculateTotalCostWithTax(shoppingBaskets));
        resultList.add("Total: " + totalSaleTaxBuilder.toString() +"\n");
        return resultList;
	}	
}

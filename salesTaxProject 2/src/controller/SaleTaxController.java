package controller;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  There are two services will be called in saleTaxController
 */

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.ConcreteItem;
import model.ShoppingBakets;


import service.ReadFileToShoppingBasketsService;
import service.WriteFileFromShoppingBasketsService;

public class SaleTaxController {
	
	private static SaleTaxController instance;
	
	private SaleTaxController(){
		
	}
	
	public static SaleTaxController getInstance(){
		if(instance == null){
			instance = new SaleTaxController();
		}
		return instance;
	}

   public void printReceipt(File inputfile,File outputFile){
	   ShoppingBakets<ConcreteItem> shoppingBaskets = null;
	   ReadFileToShoppingBasketsService fileToShoppingBasketService;
	   fileToShoppingBasketService = ReadFileToShoppingBasketsService.getInstance();
	   WriteFileFromShoppingBasketsService writefileService = WriteFileFromShoppingBasketsService.getInstance();
	   try {
		// convert eachline in file into item object and store into item list
		shoppingBaskets = fileToShoppingBasketService.load(inputfile);
		// store calculated item back into output file
	    writefileService.write(outputFile, shoppingBaskets);	   		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       

   }

}

package service;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  FileUtility is used to read each line in input file.
 *  Once each line is read, item is created by calling item factory
 *  At the end, list of items is stored into list
 */
import java.io.File;
import java.io.IOException;
import java.util.List;

import exception.ReadItemLineException;

import utility.FileUtility;

import model.ConcrateItemFactory;
import model.ConcreteItem;
import model.Item;
import model.ShoppingBakets;

public class ReadFileToShoppingBasketsService {

   private static ReadFileToShoppingBasketsService instance;
   
   private ReadFileToShoppingBasketsService(){
	   
   }
    
   public static ReadFileToShoppingBasketsService getInstance(){
	   if(instance==null){
		   instance = new ReadFileToShoppingBasketsService();
	   }
	   
	   return instance;
   }
   
   public ShoppingBakets<ConcreteItem> load(File file) throws IOException{
	   ShoppingBakets<ConcreteItem> shoppingBaskets = ShoppingBakets.getNewShoppingBaskets() ;
       List<String> resultList;	   
	   resultList = FileUtility.read(file);
	   ConcrateItemFactory concrateItemFactory = ConcrateItemFactory.getInstance();
	   for(String result:resultList){
		   try {
			Item item;
			item = concrateItemFactory.createItem(result);
			shoppingBaskets.addElement((ConcreteItem) item);
		} catch (ReadItemLineException e) {	
			e.printStackTrace();
		}
	   }
	   return shoppingBaskets;
	   
   }

}

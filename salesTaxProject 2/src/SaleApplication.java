import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import controller.SaleTaxController;

import exception.ReadItemLineException;

import model.ConcrateItemFactory;
import model.ConcreteItem;
import model.Item;
import model.ShoppingBakets;
import model.TaxRateType;

import service.ReadFileToShoppingBasketsService;
import service.SaleTaxCalcultorService;
import service.WriteFileFromShoppingBasketsService;
import tax.BasicSaleTaxWithRounding;
import tax.ExemptTaxWithRounding;
import tax.ImportedTaxWithRounding;
import utility.FileUtility;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  Main function create console for user to input file name
 */

public class SaleApplication {

	/**
	 * @param args
	 * @throws ReadItemLineException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ReadItemLineException, IOException {


        BufferedReader br = null;
        Reader r = new InputStreamReader(System.in);
        br = new BufferedReader(r);
        String str = null;
        String outStr = null;
        try {
            do{
                System.out.println("Please select file.(input1, input2, input3)");
                str = br.readLine();
                
                if(StringUtils.equalsIgnoreCase(str, "input1")
                	|| StringUtils.equalsIgnoreCase(str, "input2")
                	|| StringUtils.equalsIgnoreCase(str, "input3")){
                	outStr = StringUtils.replaceChars(str, 'i', 'o');
                    File inputFile = new File("."+File.separator +"data"+File.separator + str);        
                    File outputFile = new File("."+File.separator +"data"+File.separator + outStr);
                    //Once input file is determined, SaleTaxController is called for purchasing items
            		SaleTaxController saleTaxController = SaleTaxController.getInstance();
            		saleTaxController.printReceipt(inputFile, outputFile);                   	
                }else{
                    System.out.println("Wrong file name");                    
                }

            } while (!str.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(br != null) br.close();
            }catch(Exception ex){}
        }		


	}

}

package service;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  implement required calculation in concrete class
 *  Based on tax rate type applied in each item, different rate will be calculated
 */
import java.math.BigDecimal;
import java.util.Set;

import tax.BasicSaleTaxWithRounding;
import tax.ExemptTaxWithRounding;
import tax.ImportedTaxWithRounding;
import model.ConcreteItem;
import model.Item;
import model.ShoppingBakets;
import model.TaxRateType;

public class SaleTaxCalcultorService implements CaclcultorService{

   private static SaleTaxCalcultorService instance = null;
   
   private SaleTaxCalcultorService(){
	   
   }
	
   public static SaleTaxCalcultorService getInstance(){
	   if(instance == null){
		   instance = new SaleTaxCalcultorService();
	   }
	   
	   return instance;
   }
   
   @Override   
   public BigDecimal calculateItemsPriceWithoutTax(ConcreteItem item){
	 return item.getPrice().multiply(new BigDecimal(item.getQuantity()));   
     
   }
   @Override
   public BigDecimal calculateItemsPriceWithTax(ConcreteItem item){
	 return item.getPrice().multiply(new BigDecimal(item.getQuantity())).add(calculateSaleTax( item));   
     
   }

   @Override   
   public BigDecimal calculateTotalSaleTax(ShoppingBakets<ConcreteItem> itemList){
	   BigDecimal totalSaleTax = new BigDecimal(0.00);
	   for(ConcreteItem item:itemList){
		   totalSaleTax = calculateSaleTax(item).add(totalSaleTax);
	   }
	   return totalSaleTax;
   }
   @Override
   public BigDecimal calculateTotalCostWithTax(ShoppingBakets<ConcreteItem> itemList){
	   BigDecimal totalcostWithTax = new BigDecimal(0.00);
	   for(ConcreteItem item:itemList){
		   totalcostWithTax = item.getPrice().add(calculateSaleTax(item)).add(totalcostWithTax);
	   }
	   return totalcostWithTax;
   }   
   
   @Override   
   public BigDecimal calculateSaleTax(ConcreteItem item){
	   Set<TaxRateType>taxRateTypeSet =  item.getTaxRateType();
	   BigDecimal finalSaleTax;
	   if(taxRateTypeSet.contains(TaxRateType.BASIC_SALE_TAX)){
	        BasicSaleTaxWithRounding basckSaleTax = new BasicSaleTaxWithRounding();
		    if(taxRateTypeSet.contains(TaxRateType.IMPORT_TAX)){
		         ImportedTaxWithRounding  ImportedTax = new ImportedTaxWithRounding(basckSaleTax);
		         finalSaleTax = ImportedTax.getTaxWithRounding(item);	         
		    }else{
		    	 finalSaleTax = basckSaleTax.getTaxWithRounding(item);		    	
		    }

	   }else{
	         ExemptTaxWithRounding exemptTax = new ExemptTaxWithRounding();
			    if(taxRateTypeSet.contains(TaxRateType.IMPORT_TAX)){
			         ImportedTaxWithRounding  ImportedTax = new ImportedTaxWithRounding(exemptTax);
			         finalSaleTax = ImportedTax.getTaxWithRounding(item);			         
			    }else{
			    	finalSaleTax = exemptTax.getTaxWithRounding(item);			    	
			    }	         
	   }
	return finalSaleTax;
	   
   }

}

package model;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  Concrate item will be created in this class. In addition, exempt tax type is defined
 *  before object is really created. TaxRateType of item will be assigned.
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import exception.ReadItemLineException;

public class ConcrateItemFactory implements ItemFactory {
    
    static final List<String> exemptList =  new ArrayList<String>();
	static {
		exemptList.add("book");
		exemptList.add("chocolate");
		exemptList.add("pill");			
	}
	
	private static ConcrateItemFactory instance;
	
	private ConcrateItemFactory(){
		
	}
	
	public static ConcrateItemFactory getInstance(){
		if(instance== null){
			instance = new ConcrateItemFactory(); 
		}
		return instance;
	}
	
	
	@Override
	public Item createItem(String readLine) throws ReadItemLineException {
		Scanner itemScanner = new Scanner(readLine);
		Item item = null;
		BigDecimal price = null;
	    StringBuilder name = null;
		int quantity;
		Set<TaxRateType> taxRateType = null;
		if (StringUtils.isBlank(readLine)) {
			throw new ReadItemLineException("Line is empty");
		}

		if (itemScanner.hasNextInt()) {
			quantity = itemScanner.nextInt();
		} else {
			throw new IllegalStateException("This token is not quanitity");
		}

		name = lookUpItemAt(itemScanner);

		if (itemScanner.hasNext("at")) {
			itemScanner.next("at");
		} else {
			throw new IllegalStateException("at token cannot be found ");
		}

		if (itemScanner.hasNextBigDecimal()) {
			price = itemScanner.nextBigDecimal();
		}
		taxRateType = determineTaxRateType(name.toString());
		item = new ConcreteItem(name.toString(), quantity, price, taxRateType);

		return item;
	}

	StringBuilder lookUpItemAt(Scanner itemScanner) {
		StringBuilder name = new StringBuilder();
		while (!itemScanner.hasNext("at") && itemScanner.hasNext()) {
			name.append(itemScanner.next()).append(" ");
		}
		return name;
	}
	
	private Set<TaxRateType> determineTaxRateType(String name){
		Set<TaxRateType> taxRateType = new HashSet<TaxRateType>();
		taxRateType.add(TaxRateType.BASIC_SALE_TAX);
		if(name.contains("imported")){
			taxRateType.add(TaxRateType.IMPORT_TAX);
			determineExemptType(name,taxRateType);			
		}else{
			determineExemptType(name,taxRateType);
		}
		return taxRateType;
	}
	
	private Set<TaxRateType> determineExemptType(String name, Set<TaxRateType> taxRateType){
		for(String exemptItem:exemptList){
			if(name.contains(exemptItem)){
				taxRateType.remove(TaxRateType.BASIC_SALE_TAX);
				taxRateType.add(TaxRateType.EXEMPT_TAX);
				break;
			}
		}		
		return taxRateType;
	}

}

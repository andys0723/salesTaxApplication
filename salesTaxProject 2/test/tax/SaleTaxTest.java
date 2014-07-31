package tax;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import junit.framework.Assert;

import model.ConcrateItemFactory;
import model.ConcreteItem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exception.ReadItemLineException;

public class SaleTaxTest {

	@Test
	public void basicSaleTaxTest() throws ReadItemLineException {
		ConcrateItemFactory concrateItemFactory = spy(ConcrateItemFactory.getInstance());		
		ConcreteItem item = (ConcreteItem)concrateItemFactory.createItem("1 bottle of perfume at 18.99");
		BasicSaleTaxWithRounding basicSaleTaxWithRounding = spy(new BasicSaleTaxWithRounding());
		Assert.assertTrue("The tax is incorrect.", basicSaleTaxWithRounding.getTaxWithRounding(item)
        		.compareTo(new BigDecimal(1.90).setScale(2, RoundingMode.HALF_UP))==0);		
		      
	}

	@Test
	public void ExemptTaxTest() throws ReadItemLineException {
		ConcrateItemFactory concrateItemFactory = spy(ConcrateItemFactory.getInstance());		
		ConcreteItem item = (ConcreteItem)concrateItemFactory.createItem("1 book at 12.49");
		ExemptTaxWithRounding exemptSaleTaxWithRounding = spy(new ExemptTaxWithRounding());
        Assert.assertTrue("The tax is incorrect.", exemptSaleTaxWithRounding.getTaxWithRounding(item)
        		.compareTo(new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP))==0);		
		      
	}

	@Test
	public void basicSaleTaxPlusImportedTest() throws ReadItemLineException {
		ConcrateItemFactory concrateItemFactory = spy(ConcrateItemFactory.getInstance());		
		ConcreteItem item = (ConcreteItem)concrateItemFactory.createItem("1 imported bottle of perfume at 47.50");
		BasicSaleTaxWithRounding basicSaleTaxWithRounding = spy(new BasicSaleTaxWithRounding());
		ImportedTaxWithRounding importedTaxWithRounding = spy(new ImportedTaxWithRounding(basicSaleTaxWithRounding));
		Assert.assertTrue("The tax is incorrect.", importedTaxWithRounding.getTaxWithRounding(item)
        		.compareTo(new BigDecimal(7.15).setScale(2, RoundingMode.HALF_UP))==0);		
		      
	}
	
	@Test
	public void exemptTaxPlusImportedTest() throws ReadItemLineException {
		ConcrateItemFactory concrateItemFactory = spy(ConcrateItemFactory.getInstance());		
		ConcreteItem item = (ConcreteItem)concrateItemFactory.createItem("1 imported box of chocolates at 10.00");
		ExemptTaxWithRounding exemptSaleTaxWithRounding = spy(new ExemptTaxWithRounding());
		ImportedTaxWithRounding importedTaxWithRounding = spy(new ImportedTaxWithRounding(exemptSaleTaxWithRounding));
		Assert.assertTrue("The tax is incorrect.", importedTaxWithRounding.getTaxWithRounding(item)
        		.compareTo(new BigDecimal(0.55).setScale(2, RoundingMode.HALF_UP))==0);		
		      
	}	
	
}

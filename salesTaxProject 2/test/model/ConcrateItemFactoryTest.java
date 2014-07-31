package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;
import java.util.Scanner;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exception.ReadItemLineException;

public class ConcrateItemFactoryTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	private ConcrateItemFactory concrateItemFactory;
	
	@Test
	public void readingBlankLineMustThrowException() throws ReadItemLineException{
		concrateItemFactory = spy(ConcrateItemFactory.getInstance());
		thrown.expect(ReadItemLineException.class);
		concrateItemFactory.createItem("");
		
	}
	
	@Test
	public void noQuantityMustThrowException() throws  ReadItemLineException  {
		concrateItemFactory = spy(ConcrateItemFactory.getInstance());
		thrown.expect(IllegalStateException.class);
		concrateItemFactory.createItem("book at 12.49");
	}

	@Test
	public void atMustReturnBlankString()  {
		concrateItemFactory = spy(ConcrateItemFactory.getInstance());
		Scanner scanner = new Scanner("at next");
		final String given = concrateItemFactory.lookUpItemAt(scanner).toString();
		assertEquals("", given.toString());
	}
	
	@Test
	public void noAtMustReturnNextItem()  {
		concrateItemFactory = spy(ConcrateItemFactory.getInstance());
		Scanner scanner = new Scanner("next");
		final String given = concrateItemFactory.lookUpItemAt(scanner).toString();
		assertEquals("next ", given.toString());
	}
    private void assertCreatedItemCorrectly(int expectedQuantity, String expectedDescription, BigDecimal expectedPrice, 
            Set<TaxRateType> expectedTaxRates, ConcreteItem item) {
        Assert.assertEquals("The quantity is incorrect.", expectedQuantity, item.getQuantity());
        Assert.assertEquals("The description is incorrect.", expectedDescription, item.getName());
        Assert.assertTrue("The price is incorrect.", expectedPrice.compareTo(item.getPrice())==0);
        Assert.assertTrue("The tax rate type is incorrect.", expectedTaxRates.containsAll(item.getTaxRateType()));
    }

	@Test
	public void testImportedItem() throws ReadItemLineException{
		concrateItemFactory = spy(ConcrateItemFactory.getInstance());
		ConcreteItem item = (ConcreteItem)concrateItemFactory.createItem("1 imported box of chocolates at 10.00");
	    Set<TaxRateType> expectedTaxes = EnumSet.of(TaxRateType.IMPORT_TAX, TaxRateType.EXEMPT_TAX);
		assertCreatedItemCorrectly(1,"imported box of chocolates ",new BigDecimal(10.00).setScale(2, RoundingMode.HALF_UP),expectedTaxes,item);
		
	}

	@Test
	public void testBasicItem() throws ReadItemLineException{
		concrateItemFactory = spy(ConcrateItemFactory.getInstance());
		ConcreteItem item = (ConcreteItem)concrateItemFactory.createItem("1 music CD at 14.99");
	    Set<TaxRateType> expectedTaxes = EnumSet.of(TaxRateType.BASIC_SALE_TAX);
		assertCreatedItemCorrectly(1,"music CD ",new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP),expectedTaxes,item);
		
	}
	
	@Test
	public void testExemptItem() throws ReadItemLineException{
		concrateItemFactory = spy(ConcrateItemFactory.getInstance());
		ConcreteItem item = (ConcreteItem)concrateItemFactory.createItem("1 packet of headache pills at 9.75");
	    Set<TaxRateType> expectedTaxes = EnumSet.of(TaxRateType.EXEMPT_TAX);
		assertCreatedItemCorrectly(1,"packet of headache pills ",new BigDecimal(9.75).setScale(2, RoundingMode.HALF_UP),expectedTaxes,item);
		
	}

	@Test
	public void testExemptImportedItem() throws ReadItemLineException{
		concrateItemFactory = spy(ConcrateItemFactory.getInstance());
		ConcreteItem item = (ConcreteItem)concrateItemFactory.createItem("5 box of imported chocolates at 11.25");
	    Set<TaxRateType> expectedTaxes = EnumSet.of(TaxRateType.EXEMPT_TAX,TaxRateType.IMPORT_TAX);
		assertCreatedItemCorrectly(5,"box of imported chocolates ",new BigDecimal(11.25).setScale(2, RoundingMode.HALF_UP),expectedTaxes,item);
		
	}	
}

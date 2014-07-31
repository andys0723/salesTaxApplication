package service;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  define required calculation in interface
 */
import java.math.BigDecimal;

import model.ConcreteItem;
import model.Item;
import model.ShoppingBakets;

public interface CaclcultorService {
	BigDecimal calculateItemsPriceWithoutTax(ConcreteItem item);
	BigDecimal calculateItemsPriceWithTax(ConcreteItem item);
	BigDecimal calculateTotalSaleTax(ShoppingBakets<ConcreteItem> itemList);
	BigDecimal calculateTotalCostWithTax(ShoppingBakets<ConcreteItem> itemList);
	BigDecimal calculateSaleTax(ConcreteItem item);




}

package tax;

import java.math.BigDecimal;

import model.ConcreteItem;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  Tax decorator that add additional tax rate onto either basic sale tax or exempt tax
 */
public class TaxDecoratorWithRounding extends TaxWithRounding{

	protected TaxWithRounding decoratedTax;
	
	public TaxDecoratorWithRounding(TaxWithRounding decoratedTax){
		this.decoratedTax = decoratedTax;
	}
	
	@Override
	public BigDecimal getTax(ConcreteItem item) {
		return this.decoratedTax.getTax(item);
	}
	
	@Override
	public BigDecimal getTaxWithRounding(ConcreteItem item) {
		return roundOff(this.decoratedTax.getTax(item));
	}

}

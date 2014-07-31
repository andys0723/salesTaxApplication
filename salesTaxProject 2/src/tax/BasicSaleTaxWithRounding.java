package tax;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  Basic Sale Tax is kind of Tax 
 */
import java.math.BigDecimal;

import model.ConcreteItem;
import model.TaxRateType;

public class BasicSaleTaxWithRounding extends TaxWithRounding{

	@Override
	public BigDecimal getTax(ConcreteItem item) {
        return item.getPrice().multiply(new BigDecimal(TaxRateType.BASIC_SALE_TAX.getTaxRate()));
	}

	@Override
	public BigDecimal getTaxWithRounding(ConcreteItem item) {
		return roundOff(item.getPrice().multiply(new BigDecimal(TaxRateType.BASIC_SALE_TAX.getTaxRate())));
	}



}

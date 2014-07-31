package tax;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  Exempt Tax is kind of Tax 
 */
import java.math.BigDecimal;

import model.ConcreteItem;
import model.TaxRateType;

public class ExemptTaxWithRounding extends TaxWithRounding{
	@Override
	public BigDecimal getTax(ConcreteItem item) {
		
        return item.getPrice().multiply(new BigDecimal(TaxRateType.EXEMPT_TAX.getTaxRate()));
	}

	@Override
	public BigDecimal getTaxWithRounding(ConcreteItem item) {
		return roundOff(item.getPrice().multiply(new BigDecimal(TaxRateType.EXEMPT_TAX.getTaxRate())));
	}

}

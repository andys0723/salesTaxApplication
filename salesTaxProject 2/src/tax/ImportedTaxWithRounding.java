package tax;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  imported is added by extends TaxDecorateWithRounding
 */
import java.math.BigDecimal;

import model.ConcreteItem;
import model.TaxRateType;

public class ImportedTaxWithRounding extends TaxDecoratorWithRounding {

	public ImportedTaxWithRounding(TaxWithRounding TaxDecoratorWithRounding) {
		super(TaxDecoratorWithRounding);
	}

	@Override
	public BigDecimal getTax(ConcreteItem item) {
		return super.getTax(item).add(item.getPrice().multiply(new BigDecimal(TaxRateType.IMPORT_TAX.getTaxRate()))) ;
	}

	@Override
	public BigDecimal getTaxWithRounding(ConcreteItem item) {
		return roundOff(super.getTax(item).add(item.getPrice().multiply(new BigDecimal(TaxRateType.IMPORT_TAX.getTaxRate()))));
	}
}

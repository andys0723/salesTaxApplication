package tax;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  Abstract class implements Tax interface
 */
import java.math.BigDecimal;
import java.math.RoundingMode;

import model.ConcreteItem;
import model.Item;

public abstract class  TaxWithRounding implements Tax{
	  private static final BigDecimal INDEX = new BigDecimal("0.05");
	  public abstract BigDecimal getTax(ConcreteItem item);
	  public abstract BigDecimal getTaxWithRounding(ConcreteItem item);
	  protected static BigDecimal roundOff(BigDecimal value) {
		  return value.divide(INDEX).setScale(0, RoundingMode.UP).multiply(INDEX);		  
	  }
}

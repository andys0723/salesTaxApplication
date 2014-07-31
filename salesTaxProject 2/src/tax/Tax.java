package tax;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  Tax interface
 */
import java.math.BigDecimal;

import model.ConcreteItem;


public interface Tax {
  public BigDecimal getTax(ConcreteItem item);
}

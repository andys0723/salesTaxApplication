package model;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  Define concrete item
 */
import java.math.BigDecimal;
import java.util.Set;

public class ConcreteItem implements Item{
    
	private final String     name;
	private final BigDecimal price;
	private final int        quantity;
	private final Set<TaxRateType> taxRateType;
	public ConcreteItem( String name,  int quantity,  BigDecimal price , Set<TaxRateType> saleType){
       this.name 	 = name;
       this.price    = price;
       this.taxRateType = saleType;
       this.quantity = quantity;
	}
	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public Set<TaxRateType> getTaxRateType() {
		return taxRateType;
	}	
	
	@Override
	public int hashCode(){
		int hash = 1;
		hash = hash * 3 + this.name.hashCode();
		hash = hash * 7 + this.price.hashCode();
		return hash;
	}
	
	@Override
	public boolean equals(Object obj ){
	   boolean result = false;
	   if(this ==  obj)
		   return true;
	   if(!(obj  instanceof ConcreteItem)){
		  return false;   
	   }
	   ConcreteItem item =  (ConcreteItem)obj;
	   if(item.name.equals(this.name)
			   && item.price.equals(this.price)
			   && item.quantity == this.quantity){
		   result = true;
	   }
	return result;
	  	
	}
	
	@Override
	public String toString(){
		return "Item's name is: "  + this.name  + "\n" 
			 + "Item's price is: " + this.price + "\n";
	}
	public int getQuantity() {
		return quantity;
	}

	
}

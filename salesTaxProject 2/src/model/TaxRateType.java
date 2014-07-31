package model;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  Define tax type and tax rate in this file so that user is easy to define
 */
public enum TaxRateType {
    
	BASIC_SALE_TAX(.10f),
	EXEMPT_TAX(.0f),
	IMPORT_TAX(.05f);
	
	float tax;
	private TaxRateType(float tax){
		this.tax = tax;
	}
	
	public float getTaxRate(){
		return tax;
	}
}

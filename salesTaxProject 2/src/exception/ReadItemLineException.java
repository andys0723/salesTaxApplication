package exception;

public class ReadItemLineException extends Exception{
	private static final long serialVersionUID = 4748009532189619262L;

	public ReadItemLineException(String message){
		super(message);
	}
	
	public String getReadItemLieException(){
		return super.getMessage();
	}
}

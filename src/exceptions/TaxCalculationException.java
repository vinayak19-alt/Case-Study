package exceptions;

public class TaxCalculationException extends  RuntimeException{

    public TaxCalculationException(){
        super("Tax cannot be calculated");
    }
    public TaxCalculationException(String message){
        super(message);
    }
}

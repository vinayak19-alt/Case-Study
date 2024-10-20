package exceptions;

public class PayrollGenerationException extends RuntimeException {

    public PayrollGenerationException(){
        super("Payroll can't be generated");
    }
    public PayrollGenerationException(String message){
        super(message);
    }
}

package exceptions;

public class InvalidInputException extends RuntimeException{

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(){
        super("Please check the input value");
    }
}

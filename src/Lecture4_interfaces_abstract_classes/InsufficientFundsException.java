package Lecture4_interfaces_abstract_classes;

public class InsufficientFundsException extends Exception{
    
    public InsufficientFundsException(){
        super("Error, you do not have enough funds for this withdrawal request");
    }

    public InsufficientFundsException(String Message){
        super(Message);

    }


}
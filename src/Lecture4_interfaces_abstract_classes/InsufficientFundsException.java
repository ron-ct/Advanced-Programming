package Lecture4_interfaces_abstract_classes;

/**
 * Custom exception to handle cases where a bank account has insufficient funds
 * for a requested withdrawal.
 * This exception is thrown when withdrawal amount exceeds the balance in the BankAccount
 * It provides default message as well as default message
 */
public class InsufficientFundsException extends Exception{
    /**
     * Constructs an InsufficientFundsException with a default error message
     * indicating insufficient funds for the withdrawal.
     */
    public InsufficientFundsException(){
        super("Error, you do not have enough funds for this withdrawal request");
    }

    /**
     * Constructs an InsufficientFundsException with a specified custom error message.
     *
     * @param Message the custom message providing details about the exception
     */
    public InsufficientFundsException(String Message){
        super(Message);

    }

}
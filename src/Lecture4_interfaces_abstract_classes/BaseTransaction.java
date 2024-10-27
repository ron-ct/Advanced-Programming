package Lecture4_interfaces_abstract_classes;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class BaseTransaction implements TransactionInterface {
    private final double amount;
    private final Calendar date;
    private final String transactionID;

    /**
     * 
     * @param amount must be a double that is a positive number
     * @param date must be a calendar object, cannot be null
     * initializes the fields of the transaction
     * creates objects of this
    */
    public BaseTransaction(double amount, Calendar date){
        this.amount = amount;
        this.date = (Calendar) date.clone();
        int uniqNum = (int) (Math.random() * 10000);
        this.transactionID = date.toString()+uniqNum;

    }

    // Method to get the transaction amount
    public double getAmount(){
        return amount;

    }

    // Method to get the transaction date
    public Calendar getDate(){
        return (Calendar) date.clone(); //judicious defensive copying

    }

    // Method to get a unique identifier for the transaction
    public String getTransactionID(){
        return transactionID;

    }

    public void printTransactionDetails(){
        System.out.println("TransactionID: " + getTransactionID());
        System.out.println("Amount: " + getAmount());
        System.out.println("Date: " + getDate().getTime());
        System.out.println("-----------------------------------------------------------------------");
    }

    public void apply(BankAccount ba) throws InsufficientFundsException{
        //placeholder. does not modify the balance is just a general implementation.
        //specific implementation is in the subclasses
        System.out.println("BaseTransaction's apply method called. No change in the balance for this type of transaction ");
        
    }
}
package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class DepositTransaction extends BaseTransaction {

    /**
     * 
     * @param amount is a positive number >= 0
     * @param date not null
    */
    public DepositTransaction(double amount, @NotNull Calendar date){
        super(amount, date);
    }


    /**
     * @param amt is a positive number >=0
     * @return true if amt is positive
     */
    private boolean checkAmount(double amt){
        if (amt < 0){
           return false;
        }
        else{
            return  true;
        }
    }

    // Method to print a transaction receipt or details
    @Override
    public void printTransactionDetails(){
        System.out.println("Deposit Transaction: "+ this.toString());
    }

    //Method to apply the transaction to the bank account

    /**
     * @param ba must be a BankAccount object and not null
     *
     */
    @Override
    public void apply(BankAccount ba){
        if (checkAmount(getAmount())){
            double curr_balance = ba.getBalance();
            double new_balance = curr_balance + getAmount();
            ba.setBalance(new_balance);
        }
        
    }
}

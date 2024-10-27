package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

/**
 * Represents a deposit transaction to a bank account
 * This class implements BaseTransaction and applies funds to specified BankAccount
 * The DepositTransaction is irreversible
 */
public class DepositTransaction extends BaseTransaction {

    /**
     * Constructs a DepositTransaction with specified amount and date
     * @param amount the amount to deposit, must be positive
     * @param date date transaction occurs, not null
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
        super.printTransactionDetails();
    }

    //Method to apply the transaction to the bank account

    /**
     * Applies a deposit transaction to the specified bank account.
     * This method verifies the deposit amount using the checkAmount method.
     * If the amount is valid, it adds the deposit amount to the current balance of the bank
     * account and updates the balance.
     * @param ba the BankAccount to which the deposit amount is added
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

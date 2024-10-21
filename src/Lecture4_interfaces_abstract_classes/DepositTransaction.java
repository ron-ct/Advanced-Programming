package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.OverrideOnly;

import java.util.Calendar;

public class DepositTransaction extends BaseTransaction {

    public DepositTransaction(int amount, @NotNull Calendar date){
        super(amount, date);
    }

    private boolean checkAmount(int amt){
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
        System.out.println("Deposit Trasaction: "+this.toString());
    }

    //Method to apply the transaction to the bank account
    @Overide
    public void apply(BankAccount ba){
        double curr_balance = ba.getBalance();
        double new_balance = curr_balance + getAmount();
        ba.setBalance(new_balance);
    }
}
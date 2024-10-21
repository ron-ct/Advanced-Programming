package Lecture4_interfaces_abstract_classes;
import java.util.*;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import Lecture4_interfaces_abstract_classes.BankAccount;

public class WithdrawalTransaction extends BaseTransaction {
    public WithdrawalTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
    }

    private boolean checkAmount(int amt) {
        if (amt < 0) {
            return false;
        } else {
            return true;
        }
    }



    // Method to print a transaction receipt or details
    public void printTransactionDetails() {
        System.out.println("Withdrawal Trasaction: " + this.toString());
    }
    double new_balance;

    /*
    Oportunity for assignment: implementing different form of withdrawal
     */
    @Overide
     public void apply(BankAccount ba) {
        double curr_balance = ba.getBalance();
        if (curr_balance > getAmount()) {
            new_balance = curr_balance - getAmount();
            ba.setBalance(new_balance);
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to reverse your Transaction? (Y) or (N)");
        boolean userAnswer = sc.nextLine().toLowerCase() == "y";
        if (userAnswer){
            reverse(ba);                       
        }


    }

    /*
    Assignment 1 Q3: Write the Reverse method - a method unique to the WithdrawalTransaction Class
     */
    
         // Method to reverse the transaction
    public boolean reverse(BankAccount ba){
        double amount_transaction = getAmount();
        double latest_balance = new_balance;
        new_balance = latest_balance - amount_transaction;
        ba.setBalance(new_balance);
        return true;
    }



}

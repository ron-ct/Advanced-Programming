package Lecture4_interfaces_abstract_classes;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public class WithdrawalTransaction extends BaseTransaction {
    public WithdrawalTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
    }

    private boolean checkAmount(int amt) {
        return amt >= 0;
    }

    // Method to print a transaction receipt or details
    public void printTransactionDetails() {
        System.out.println("Withdrawal Transaction: " + this.toString());
    }

    double new_balance;


    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        try {
             double curr_balance = ba.getBalance();
             if (curr_balance < getAmount()) {
                    // If balance is insufficient, throw InsufficientFundsException
                    throw new InsufficientFundsException("Insufficient balance to withdraw " + getAmount());
                }

                new_balance = curr_balance - getAmount();
                ba.setBalance(new_balance);

                // Ask user if they want to reverse the transaction
                Scanner sc = new Scanner(System.in);
                System.out.println("Do you wish to reverse your transaction? (Y) or (N)");
                String userAnswer = sc.nextLine().toLowerCase();

                // Using equals to compare string values
                if (userAnswer.equals("y")) {
                reverse(ba);
                }
            }
      catch (InsufficientFundsException e) {
            // Handle the insufficient funds exception
            System.out.println("Error: " + e.getMessage());
        }
    }


    // Method to reverse the transaction
    public boolean reverse(BankAccount ba) {
        double amount_transaction = getAmount();
        double latest_balance = new_balance;

        // Add the withdrawn amount back to the balance to reverse the transaction
        new_balance = latest_balance + amount_transaction;
        ba.setBalance(new_balance);
        return true;
    }
}

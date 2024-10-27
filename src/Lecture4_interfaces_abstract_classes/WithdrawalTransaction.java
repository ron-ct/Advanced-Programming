package Lecture4_interfaces_abstract_classes;
import java.util.*;
import org.jetbrains.annotations.NotNull;

/**
 * The WithdrawalTransaction class represents a transaction to withdraw funds from a bank account.
 * This transaction can be reversed of necessary
 * Extends the BasteTransaction class and provides specific implementation for withdrawal transactions
 * @see BaseTransaction
 */
public class WithdrawalTransaction extends BaseTransaction {
    /**
     *Constructs a WithdrawalTransaction with specified amount and date
     * @param amount the amount to withdraw.  amount must be  positive
     * @param date the date the transaction occurs
     */
    public WithdrawalTransaction(double amount, @NotNull Calendar date) {
        super(amount, date);
    }

    /**
     * 
     * @param amt is double
     * @return boolean
    */
    private boolean checkAmount(double amt) {
        if (amt < 0){
            return false;
        }
        else{
            return true;
        }
    }

    // Method to print a transaction receipt or details
    public void printTransactionDetails() {
        super.printTransactionDetails();
    }

    double new_balance;



    /**
     * Applies the withdrawal transaction to the specified bank account
     * If the account has insufficient funds, it throws an InsufficientFundsException
     * @param ba the BankAccount to which this transaction applies
     * @throws InsufficientFundsException if the balance in the account is insufficient for the withdrawal
     */
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

                System.out.println("Dear Customer, your bank balance is: KES"+ ba.getBalance());

            // Ask user if they want to reverse the transaction
            Scanner sc = new Scanner(System.in);  // Do not close this if you're reading input multiple times
            System.out.println("Do you wish to reverse your transaction? (Y) or (N)");

            if (sc.hasNextLine()) {  // Check if there's a next line to avoid NoSuchElementException
                String userAnswer = sc.nextLine().trim().toLowerCase();

                // Compare the string using .equals
                if (userAnswer.equals("y")) {
                    boolean result = reverse(ba);  // Call the reverse method if the user selects "Y"
                    if(result){
                        System.out.println("Successfully reversed your transaction of KES "+getAmount());
                        return;
                    }
                }
                else {System.out.println("You have successfully withdrawn KES" +getAmount()+" from your bank account.");
                }
            } else {
                System.out.println("No input provided.");
            }


        }
      catch (InsufficientFundsException e) {
            // Handle the insufficient funds exception
            System.out.println("Error: " + e.getMessage());
        }
    }

    //overloaded Method apply
    /**
     * Attempts to apply a withdrawal transaction to the specified bank account
     * This method withdraws the full transaction if the account balance is sufficient
     * If the bank account balance is insufficient and the InsufficientFundsException Flag is set to true,
     * it withdraws the entire available balance
     * the remaining amount that could not be withdrawn is displayed as a message to the user
     * Throws InsufficientFunds exception if no funds are available, balance < 1
     * @param ba the BankAccount from which the withdrawal is requested
     * @param isFullWithdraw if true, withdraws entire available balance when
     *                       balance is less than requested amount
     * @throws InsufficientFundsException if there are no funds in the bank account, if account balance < 1
     */
    public void apply(BankAccount ba, boolean isFullWithdraw) throws InsufficientFundsException{
        try{
            double bank_balance = ba.getBalance();
            double withdrawal_request_amount = getAmount();

            if (bank_balance < 1){
                throw new InsufficientFundsException("There are currently no funds to Withdraw");
            }

            if (bank_balance < withdrawal_request_amount){
                if(isFullWithdraw){
                    System.out.println("Withdrawing all your remaining balance KES" + bank_balance);
                    double remainder_withdrawal = withdrawal_request_amount - bank_balance;
                    ba.setBalance(0);
                    System.out.println("KES" + remainder_withdrawal + " cannot be withdrawn due to lack of funds.");
                    System.out.println("You have successfully withdrawn KES"+ bank_balance + ". Your Bank balance is KES"+ba.getBalance());
                }
                
            }
            else{ apply(ba); }
        }
        catch(InsufficientFundsException e){
            e.getMessage();
        }
    }


    // Method to reverse the transaction
    /**
     * Reverses a withdrawal transaction on the specified bank account
     * This method adds the original withdrawal amount back to the current balance of
     * the bank account, effectively reversing the transaction
     * @param ba the BankAccount object on which the bank account balance is reversed
     * @return true to indicate that transaction was successfully reversed
     */
    public boolean reverse(BankAccount ba) {
        double withdrawal_request_amount = getAmount();
        double latest_balance = ba.getBalance();

        // Add the withdrawn amount back to the balance to reverse the transaction
        new_balance = latest_balance + withdrawal_request_amount;
        ba.setBalance(new_balance);
        System.out.println("The bank balance is now KES "+ ba.getBalance());
        return true;
    }
}

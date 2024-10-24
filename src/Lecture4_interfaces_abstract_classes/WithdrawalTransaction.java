package Lecture4_interfaces_abstract_classes;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public class WithdrawalTransaction extends BaseTransaction {
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

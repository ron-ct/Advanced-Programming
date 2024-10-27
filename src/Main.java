//import Lecture1_adt.*; // Import all classes from Lecture1_adt package to be used in this client code

import java.util.Calendar;
import java.util.GregorianCalendar;

import Lecture4_interfaces_abstract_classes.*;

import static java.lang.System.out;

/**
 * The Main class provides utility methods for testing various types of bank transactions
 * (deposit, withdrawal, and base transactions). Each test verifies transaction behavior
 * and balance updates within the BankAccount class.
 */
public class Main {

    /**
     * Prints the current balance of the specified bank account.
     *
     * @param ba the BankAccount object whose balance is being printed
     */
    public static void getBankAccBalance(BankAccount ba){
        
        System.out.println("The Bank Account Balance is: KES " + ba.getBalance());

    }

    /**
     * Sets the balance of the specified bank account to the given amount and prints the updated balance.
     *
     * @param ba the BankAccount object whose balance is being set
     * @param amount the amount to set as the new balance
     */
    public static void setBankAccBalance(BankAccount ba, double amount){
        ba.setBalance(amount);
        System.out.println("Balance is now: " + ba.getBalance());
    }

    /**
     * Tests the behavior of a BaseTransaction, applies it to the specified BankAccount,
     * and prints the transaction details.
     *
     * @param amt the amount of the transaction
     * @param date the date of the transaction
     * @param ba the BankAccount to which the transaction will be applied
     * @throws InsufficientFundsException if funds are insufficient for the transaction
     */
    public static void testBaseTransaction(double amt,Calendar date, BankAccount ba) throws InsufficientFundsException {
        BaseTransaction bt = new BaseTransaction(amt, date);
        bt.apply(ba);
        bt.printTransactionDetails();
    }


    //Casting subtype object to the base type object and testing behaviour of the apply() method
    /**
     * Demonstrates casting DepositTransaction and WithdrawalTransaction instances to the
     * BaseTransaction type and tests the apply() method and printTransactionDetails() methods.
     *
     * @param ba the BankAccount object to which transactions are applied
     * @throws InsufficientFundsException if a withdrawal amount exceeds available funds
     */
    public static void testBehaviourTypeCastBaseType(BankAccount ba) throws InsufficientFundsException {
        //create a deposit transaction object
        DepositTransaction dt = new DepositTransaction(10310, new GregorianCalendar());
        //create a withdrawal transaction object
        WithdrawalTransaction wt = new WithdrawalTransaction(5420, new GregorianCalendar());

        //cast the Deposit transaction object to BaseTransaction
        BaseTransaction BaseDeposit = (BaseTransaction) dt;
        //cast the Withdrawal transaction object into BaseTransaction
        BaseTransaction BaseWithdraw = (BaseTransaction) wt;
        BaseDeposit.apply(ba);
        BaseDeposit.printTransactionDetails();
        out.println("The new balance is: " + ba.getBalance());
        out.println("-------------------------------------------------");

        out.println("Before Withdrawal, Balance is: " + ba.getBalance());
        try{
            BaseWithdraw.apply(ba);
        }
        catch (InsufficientFundsException e){
            System.out.println("Error: " + e.getMessage());
        }
        BaseWithdraw.printTransactionDetails();
        System.out.println("Your Bank balance is KES"+ba.getBalance());

        //Test the InsufficientFundsException
        // Insufficient Balance for Withdrawal but casting scenario
        WithdrawalTransaction largeW = new WithdrawalTransaction(771897118912515.0, new GregorianCalendar() );
        BaseTransaction largeWDBase = (BaseTransaction) largeW;
        try {
            largeWDBase.apply(ba);  // This should trigger InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
        largeWDBase.printTransactionDetails();
        System.out.println("Your Bank Balance is KES" +ba.getBalance());

    }

    /**
     * Creates and applies a new DepositTransaction, displaying the account balance before and after the deposit.
     *
     * @param amount the deposit amount
     * @param date the date of the deposit
     * @param ba the BankAccount to which the deposit is applied
     */
    public static void testCreateNewDeposit(double amount, Calendar date, BankAccount ba){
        DepositTransaction Student1Payment = new DepositTransaction(amount, date);
        Student1Payment.printTransactionDetails();
        getBankAccBalance(ba);
        Student1Payment.apply(ba);
        getBankAccBalance(ba);


    }

    /**
     * Creates and applies a new WithdrawalTransaction, displaying the account balance before and after the withdrawal.
     *
     * @param amount the withdrawal amount
     * @param date the date of the withdrawal
     * @param ba the BankAccount from which funds are withdrawn
     * @throws InsufficientFundsException if the withdrawal exceeds available funds
     */
    public static void testCreateNewWithdraw(double amount, Calendar date, BankAccount ba) throws InsufficientFundsException{
        WithdrawalTransaction payRollWithdraw = new WithdrawalTransaction(amount, date);
        payRollWithdraw.printTransactionDetails();
        System.out.println("Currently your bank balance KES"+ ba.getBalance());
        payRollWithdraw.apply(ba);
        System.out.println("Your balance is KES"+ ba.getBalance());

    }

    /**
     * Tests the withdrawal and reverse functionality of a specified WithdrawalTransaction.
     *
     * @param wt the WithdrawalTransaction to apply and possibly reverse
     * @param ba the BankAccount from which funds are withdrawn and potentially restored
     * @throws InsufficientFundsException if the withdrawal exceeds available funds
     */
    public static void testWithdrawAndReversal (WithdrawalTransaction wt, BankAccount ba) throws InsufficientFundsException{
        System.out.println("Testing the withdraw and reversal functionality...");
        System.out.println("Currently your bank balance is: "+ ba.getBalance());
        wt.apply(ba);
        System.out.println("Your bank balance is KES"+ ba.getBalance());
        wt.printTransactionDetails();

    }

    /**
     * Tests withdrawing an amount greater than the available balance, allowing a partial withdrawal and
     * printing any remaining balance.
     *
     * @param maxWithdraw a WithdrawalTransaction with a higher amount than the available balance
     * @param ba the BankAccount from which funds are withdrawn
     * @throws InsufficientFundsException if the transaction exceeds available funds
     */
    public static void testWithdrawOverLimit(WithdrawalTransaction maxWithdraw, BankAccount ba) throws InsufficientFundsException{
        System.out.println("Currently testing Withdraw over limit");
        System.out.println("Dear customer, your available balance is KES"+ba.getBalance());
        maxWithdraw.apply(ba, true);
        getBankAccBalance(ba);
        maxWithdraw.printTransactionDetails();
        

    }


    public static void main(String[] args) throws InsufficientFundsException {
        BankAccount JKUATFees = new BankAccount(1000000.0);
        Calendar d1 = new GregorianCalendar();
        WithdrawalTransaction LargeWithdrawal = new WithdrawalTransaction(100000000000000.0, d1);//I have used a very large withdrawal amount for testing purposes
        WithdrawalTransaction smalleWithdrawalTransaction = new WithdrawalTransaction(10001, d1);

        getBankAccBalance(JKUATFees);
        setBankAccBalance(JKUATFees, 5000000.0);
        testBaseTransaction(26317, d1, JKUATFees);
        testCreateNewDeposit(100000, d1, JKUATFees);
        testCreateNewWithdraw(10001.0, d1, JKUATFees);
        testWithdrawAndReversal(smalleWithdrawalTransaction, JKUATFees);
        testWithdrawOverLimit(LargeWithdrawal, JKUATFees);
        testBehaviourTypeCastBaseType(JKUATFees);

        }


}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/*
* Client Code for accessing the Lecture1_adt.TransactionInterface.java module
 */

   // public static void testTransaction1() {
   //     Calendar d1 = new GregorianCalendar(); // d1 is an Object [Objects are Reference types]
   //     Lecture1_adt.Transaction1 t1 = new Lecture1_adt.Transaction1(1000, d1); // amount and d1 are arguments

    //    System.out.println(t1.toString());
      //  System.out.println("Lecture1_adt.TransactionInterface Amount: \t " + t1.amount);
//        System.out.println("Lecture1_adt.TransactionInterface Date: \t " + t1.date);
//
        // Please note that the Client Codes can access the data in the class directly through the dot operator
        // This kind of exposure is a threat to both the Representation Independence and Preservation of Invariants
  //  }


/*
    Testing Transaction2 class
     */
//    public static void testTransaction2() {

  //      Calendar d1 = new GregorianCalendar();

    //    Lecture1_adt.Transaction2 t = new Lecture1_adt.Transaction2(1000, d1);
//
  //      Lecture1_adt.Transaction2 modified_t = makeNextPayment(t);
//
 //       System.out.println("\n\nState of the Object T1 After Client Code Tried to Change the Amount");
 //       System.out.println("Lecture1_adt.TransactionInterface Amount: \t "+modified_t.getAmount());
  //      System.out.println("Lecture1_adt.TransactionInterface Date: \t "+modified_t.getDate().getTime());
//
  //      System.out.println("\n\nHow does T2 Look Like?????");
    //    System.out.println("Lecture1_adt.TransactionInterface Amount: \t "+modified_t.getAmount());
      //  System.out.println("Lecture1_adt.TransactionInterface Date: \t "+modified_t.getDate().getTime());

        /* Please note that Although we have solved the problem of Transaction1
        * And client code can no longer use the dot (.) operator to directly access the data
        * There is still some exposure especially if we pass an object of a previous Transaction2 to create a new Transaction2 object
         */

  //  }


/*
//    Testing Transaction3 class
     */
  //  public static void testTransaction3() {

    //    List<Transaction3> allPaymentsIn2024 = makeYearOfPayments(1000);

      //  for (Transaction3 t3 : allPaymentsIn2024) {

            // Display all the 12 Transactions
        //    for (Transaction3 transact : allPaymentsIn2024) {
          //      System.out.println("\n\n  ::::::::::::::::::::::::::::::::::::::::::::\n");
         //       System.out.println("Lecture1_adt.TransactionInterface Amount: \t "+transact.getAmount());
         //       System.out.println("Lecture1_adt.TransactionInterface Date: \t "+transact.getDate().getTime());
//            }
  //      }

        /* Please Check all the 12 transactions displayed and hwo their dates look like
         * Note that Although Transaction3 class resolves to an extent the exposure in Transaction2 class
         * There is still some exposure especially if we pass an object of a previous Transaction3 to create a
         * new Transaction3 object
         */
 //   }


/*
    Testing Transaction3 class
     */
//    public static void testTransaction4() {

        /*
         * Call the function to make all the Twelve transaction in a year of our business
         */

//        List<Transaction4> transactionsIn2024 = makeYearOfPaymentsFinal(1200);

        // Display all the 12 Transactions
//        for (Transaction4 transact : transactionsIn2024) {
//            System.out.println("\n\n  ::::::::::::::::::::::::::::::::::::::::::::\n");
//            System.out.println("Lecture1_adt.TransactionInterface Amount: \t "+transact.getAmount());
//            System.out.println("Lecture1_adt.TransactionInterface Date: \t "+transact.getDate().getTime());
//        }

        // Please Take a look at all the 12 transaction now and compare with the outputs of the Transaction3 class
//    }


  //  public static void main(String[] args) {
        // This is the client code
        // Uncomment the following lines to test the class which you would like to test

        // testTransaction1()
        // testTransaction2()
        // testTransaction3()
        // testTransaction4()
  //  }
//}
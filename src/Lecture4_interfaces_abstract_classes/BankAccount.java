package Lecture4_interfaces_abstract_classes;

public class BankAccount {
    private double balance;
    
    public BankAccount(double balance) {
        this.balance = balance;
    }


    /**
     * 
     * @return balance that is not negative
    */
    public double getBalance() {
        return balance;
    }

    /**
     * 
     * @param balance must be a positive number
    */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}

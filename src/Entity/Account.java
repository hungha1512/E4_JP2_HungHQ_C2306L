package Entity;

import java.io.BufferedWriter;

public class Account {
    private String id;
    private String customerID;
    private double balance;
    private Currency currency;

    public Account(){
        ;
    }

    public Account(String id, String customerID, double balance, Currency currency) {
        this.id = id;
        this.customerID = customerID;
        this.balance = balance;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Account getAccount (Account account){
        return account;
    }

    public String accountToLine(String separator){
        StringBuilder builder = new StringBuilder();
        return builder
                .append(id)
                .append(separator)
                .append(customerID)
                .append(separator)
                .append(balance)
                .append(separator)
                .append(currency)
                .toString();
    }
}
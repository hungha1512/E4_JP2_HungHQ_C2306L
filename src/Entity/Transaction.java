package Entity;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String accountID;
    private double amount;
    private Type type;
    private LocalDateTime dateTime;
    private Status status;

    public Transaction() {
        ;
    }

    public Transaction(String id, String accountID, double amount, Type type, LocalDateTime dateTime, Status status) {
        this.id = id;
        this.accountID = accountID;
        this.amount = amount;
        this.type = type;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Transaction getTransaction(Transaction transaction) {
        return transaction;
    }

    public String transactionToLine(String separator){
        StringBuilder builder = new StringBuilder();
        return builder
                .append(id)
                .append(separator)
                .append(accountID)
                .append(separator)
                .append(amount)
                .append(separator)
                .append(type)
                .append(separator)
                .append(dateTime)
                .append(separator)
                .append(status)
                .toString();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", accountID='" + accountID + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", dateTime=" + dateTime +
                ", status=" + status +
                '}';
    }
}

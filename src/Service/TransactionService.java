package Service;

import Entity.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {
    public static List<Transaction> transactions;

    public TransactionService() {
        ;
    }

    public static boolean checkRequirementTransaction(String accountID, double amount) {
        return amount % 10 == 0 && amount <= AccountService.getAccount(accountID).getBalance();
    }

    public static boolean addTransaction(String accountID, double amount) {
        if (checkRequirementTransaction(accountID, amount)) {
            AccountService.getAccount(accountID).setBalance(AccountService.getAccount(accountID).getBalance() - amount);
            return true;
        } else {
            return false;
        }
    }

    public static void writeTransactionFile(String fileTransactionPath, Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTransactionPath, true))) {
            writer.write(transaction.getId() + "; " +
                    transaction.getAccountID() + "; " +
                    transaction.getAmount() + "; " +
                    transaction.getType() + "; " +
                    transaction.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss")) + "; " +
                    transaction.getStatus().toString() + "\n");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> filterTransactionByDate(String accountID, LocalDateTime startDate, LocalDateTime endDate) {
        return transactions.stream()
                .filter(transaction -> transaction.getAccountID().equals(accountID) && transaction.getDateTime().isAfter(startDate) && transaction.getDateTime().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public static void writeTransactionFileMultipleLines(String fileTransactionPath, List<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTransactionPath, true))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.getId() + "; " +
                        transaction.getAccountID() + "; " +
                        transaction.getAmount() + "; " +
                        transaction.getType() + "; " +
                        transaction.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss")) + "; " +
                        transaction.getStatus().toString() + "\n");
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

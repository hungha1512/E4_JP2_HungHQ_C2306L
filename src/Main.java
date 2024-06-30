import Entity.*;
import Service.AccountService;
import Service.TransactionService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        String flag, choice, accountID, saveFile;
        double amount = 0;
        LocalDateTime startDate, endDate;

        List<Customer> customers = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        TransactionService.transactions = transactions;
        AccountService.accounts = accounts;

        String rootPath = System.getProperty("user.dir");
        String fileAccountPath = rootPath.replace("\\", "/") + "/data/Account.txt";
        String fileCustomerPath = rootPath.replace("\\", "/") + "/data/Customer.txt";
        String fileTransactionPath = rootPath.replace("\\", "/") + "/data/Transaction.txt";

//      Read data from file
//      Read from Account.txt
        try {
            fileReader = new FileReader(fileAccountPath);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 0) {
                    Account account = new Account();
                    String[] splitLine = line.split(";\\s*");
                    account.setId(splitLine[0]);
                    account.setCustomerID(splitLine[1]);
                    account.setBalance(Double.parseDouble(splitLine[2]));
                    account.setCurrency(Currency.valueOf(splitLine[3]));
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//      Read from Customer.txt
        try {
            fileReader = new FileReader(fileCustomerPath);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 0) {
                    Customer customer = new Customer();
                    String[] splitLine = line.split(";\\s*");
                    customer.setId(splitLine[0]);
                    customer.setName(splitLine[1]);
                    customer.setPhone(splitLine[2]);
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//      Read from Transaction.txt
        try {
            fileReader = new FileReader(fileTransactionPath);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 0) {
                    Transaction transaction = new Transaction();
                    String[] splitLine = line.split(";\\s*");
                    transaction.setId(splitLine[0]);
                    transaction.setAccountID(splitLine[1]);
                    transaction.setAmount(Double.parseDouble(splitLine[2]));
                    transaction.setType(Type.valueOf(splitLine[3]));
                    transaction.setDateTime(LocalDateTime.parse(splitLine[4], DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss")));
                    transaction.setStatus(Status.valueOf(splitLine[5]));
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//      Menu
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                System.out.println("-------MENU-------");
                System.out.println("1. Transaction");
                System.out.println("2. Query balance by Account ID");
                System.out.println("3. Query balance by time");
                System.out.print("Your choice: ");
                choice = br.readLine();
                switch (choice) {
                    case "1":
                        Transaction transaction = new Transaction();
                        System.out.print("Enter the account ID: ");
                        accountID = br.readLine();
                        while (!AccountService.existedAccount(accountID)) {
                            System.out.println("Invalid account ID");
                            System.out.print("Enter the account ID: ");
                            accountID = br.readLine();
                        }
                        System.out.print("Enter the amount you want to withdrawal: ");
                        amount = Double.parseDouble(br.readLine());

//                      Add/Set transaction
                        transaction.setAccountID(accountID);
                        transaction.setAmount(amount);
                        Transaction lastTransaction = transactions.getLast();
                        int newID = Integer.parseInt(lastTransaction.getId()) + 1;
                        transaction.setId(String.valueOf(newID));
                        transaction.setDateTime(LocalDateTime.now());
                        transaction.setType(Type.WITHDRAWAL);
                        if (TransactionService.addTransaction(accountID, amount)) {
                            System.out.println("Transaction added");
                            System.out.println("Your new balance: " + AccountService.getAccount(accountID).getBalance());
                            transaction.setStatus(Status.C);
                            transactions.add(transaction);
                        } else {
                            System.out.println("Transaction failed");
                            transaction.setStatus(Status.R);
                            transactions.add(transaction);
                        }
//                      Write to transaction file
                        TransactionService.writeTransactionFile(fileTransactionPath, transaction);
                        break;
                    case "2":
                        System.out.print("Enter account ID: ");
                        accountID = br.readLine();
                        while (!AccountService.existedAccount(accountID)) {
                            System.out.println("Invalid account ID");
                            System.out.print("Enter the account ID: ");
                            accountID = br.readLine();
                        }
                        System.out.println("Your account balance is: " + AccountService.getAccount(accountID).getBalance());
                        break;
                    case "3":
                        System.out.print("Enter account ID: ");
                        accountID = br.readLine();
                        while (!AccountService.existedAccount(accountID)) {
                            System.out.println("Invalid account ID");
                            System.out.print("Enter the account ID: ");
                            accountID = br.readLine();
                        }
                        System.out.print("Enter the start date (yyyy-MM-dd H:mm:ss): ");
                        startDate = LocalDateTime.parse(br.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss"));
                        System.out.print("Enter the end date (yyyy-MM-dd H:mm:ss): ");
                        endDate = LocalDateTime.parse(br.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss"));
                        if (TransactionService.filterTransactionByDate(accountID, startDate, endDate) != null){
                            System.out.println("Transaction found: ");
                            TransactionService.filterTransactionByDate(accountID, startDate, endDate).forEach(System.out::println);
                            System.out.println("Do you want to save the history? Y/N");
                            saveFile = br.readLine();
                            if (saveFile.equalsIgnoreCase("Y")) {
                                String fileName = rootPath.replace("\\", "/") + "/data/" + accountID + "_transaction_history.txt";
                                TransactionService.writeTransactionFileMultipleLines(fileName, TransactionService.filterTransactionByDate(accountID, startDate, endDate));
                                System.out.println("Transactions saved to " + fileName);
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
                System.out.println("Do you want to continue? Y to continue, N to stop.");
                System.out.print("Your choice: ");
                flag = br.readLine();
            } while (flag.equalsIgnoreCase("y"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
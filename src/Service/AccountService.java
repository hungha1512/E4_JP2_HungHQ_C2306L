package Service;

import Entity.Account;

import java.util.List;

public class AccountService {
    public static List<Account> accounts;

    public static boolean existedAccount(String accountID){
        return accounts.stream().anyMatch(account -> account.getId().equals(accountID));
    }

    public static Account getAccount(String accountID){
        return accounts.stream().filter(account -> account.getId().equals(accountID)).findFirst().get();
    }
}

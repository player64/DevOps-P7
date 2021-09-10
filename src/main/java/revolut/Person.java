package revolut;

import java.util.Currency;
import java.util.HashMap;


public class Person {

    private String name;
    //Accounts currency & balance
    // EUR 30
    // USD 50
    // STG 30
    private final HashMap<String, Account> userAccounts = new HashMap<String, Account>();

    public Person(String name) {
        this.name = name;
        //Create a default euro account and add it the our userAccounts container
        this.addAccount("EUR", 0);
    }

    public void addAccount(String currencyCode, double initBalance) {
        if (userAccounts.containsKey(currencyCode)) {
            System.out.println("User already has this currency in its accounts");
            return;
        }
        try {
            Currency accCurrency = Currency.getInstance(currencyCode);
            Account account = new Account(accCurrency, initBalance);
            userAccounts.put(currencyCode, account);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect currency given");
        }
    }

    public void setAccountBalance(String type, double startingBalance) {
        try {
            userAccounts.get(type).setBalance(startingBalance);
        } catch (NullPointerException e) {
            this.addAccount(type, startingBalance);
            System.out.println("The new account has been opened for the user.");
        }
    }

    public double getAccountBalance(String type) {
        return userAccounts.get(type).getBalance();
    }

    public Account getAccount(String account) {
        return userAccounts.get(account);
    }

    public HashMap<String, Account> getUserAccounts() {
        return this.userAccounts;
    }
}

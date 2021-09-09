package revolut;

import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;

    public Account(Currency currency, double balance) {
        this.balance = balance;
        this.accCurrency = currency;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addFunds(double topUpAmount) {
        this.balance += topUpAmount;
    }

    public void removeFunds(double amount) {
        this.balance -= amount;
    }

    public void topUp(double amount, PaymentService service) {
        if (!service.checkTransactionIsValid(amount)) return;
        this.addFunds(amount);
        service.charge(amount);
    }

    public void sendMoneyToFriend(Person person, double amount) {
        if (amount > balance) return;
        try {
            person.getAccount(accCurrency.toString()).addFunds(amount);

        } catch (NullPointerException e) {
            person.addAccount(accCurrency.toString(), amount);
            System.out.println("A user hasn't got account in given currency " + accCurrency + ". The new account has been created");
        }
        removeFunds(amount);
    }
}

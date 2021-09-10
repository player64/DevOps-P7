package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.Person;

public class SendMoneyToFriend {
    Person danny;
    Person friend;

    @Before
    public void setUp() {
        danny = new Person("Danny");
        friend = new Person("Friend");
    }

    @Given("Danny has {double} {string} in his account")
    public void DannyHasAmountOnHisAccount(double dannyStartBalance, String currency) {
        danny.setAccountBalance(currency, dannyStartBalance);
    }

    @And("Catriona has {int} {string} on her account")
    public void catrionaHasEuroOnHisAccount(int catrionaStartBalance, String currency) {
        friend.setAccountBalance(currency, catrionaStartBalance);
    }

    @When("Danny wish to send {double} {string} to Catriona")
    public void dannySentMoney(double amount, String account) {
        danny.getAccount(account).sendMoneyToFriend(friend, amount);
    }

    @Then("Accounts balance in {string} should be updated as Catriona {int}, Danny {int}")
    public void accountsInTheBalancesShouldBeCatrionaDanny(String accountType, int friendBalance, int dannyBalance) {
        Assert.assertEquals(dannyBalance, danny.getAccountBalance(accountType), 0);
        Assert.assertEquals(friendBalance, friend.getAccountBalance(accountType), 0);
    }

    @Given("Danny opens a new {string} account and deposited {int}")
    public void dannyOpensANewAccount(String accountType, int initBalance) {
        danny.addAccount(accountType, initBalance);
    }

    @Then("Number of currency accounts should be {int}")
    public void numberOfCurrencyAccountsShouldBe(int noAccounts) {
        Assert.assertEquals(noAccounts, danny.getUserAccounts().size());
        Assert.assertEquals(noAccounts, friend.getUserAccounts().size());
    }
}


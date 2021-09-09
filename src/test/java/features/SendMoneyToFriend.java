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

    @Given("Danny has {double} euro in his euro account")
    public void DannyHasAmountOnHisAccount(double startingBalance) {
        danny.setAccountBalance("EUR", startingBalance);
    }

    @And("Catriona has {int} euro on her account")
    public void catrionaHasEuroOnHisAccount(int startingBalance) {
        friend.setAccountBalance("EUR", startingBalance);
    }

    @When("Danny wish to send {int} {string} to Catriona")
    public void dannySentMoney(int amount, String account) {
        danny.getAccount(account).sendMoneyToFriend(friend, amount);
    }

    @Then("Danny's balance should be {int} and Catriona's balance should be {int}")
    public void dannyBalanceShouldBeAndFriendAccountShouldBe(int dannyBalance, int friendBalance) {
        Assert.assertEquals(dannyBalance, danny.getAccount("EUR").getBalance(), 0);
        Assert.assertEquals(friendBalance, friend.getAccount("EUR").getBalance(), 0);
    }


    @Then("Accounts in {string} the balances should be Catriona {int}, Danny {int}")
    public void accountsInTheBalancesShouldBeCatrionaDanny(String accountType, int friendBalance, int dannyBalance) {
        Assert.assertEquals(dannyBalance, danny.getAccount(accountType).getBalance(), 0);
        Assert.assertEquals(friendBalance, friend.getAccount(accountType).getBalance(), 0);
    }

    @Given("Danny opens a new {string} account and deposit {int}")
    public void dannyOpensANewAccount(String accountType, int initBalance) {
        danny.addAccount(accountType, initBalance);
    }
}


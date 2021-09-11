package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    PaymentService topUpMethod;
    Person danny;

    @Before
    public void setUp(){
        danny = new Person("Danny");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        danny.setAccountBalance("EUR", startingBalance);
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        this.topUpAmount = topUpAmount;
    }

    @Given("Danny selects his {paymentService} as his topUp method")
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        danny.getAccount("EUR").addFunds(topUpAmount);
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("Danny has a starting balance of {double}")
    public void dannyHasAStartingBalanceOfStartBalance(double startBalance) {
        danny.setAccountBalance("EUR", startBalance);
    }

    @When("Danny now tops up by {double}")
    public void dannyNowTopsUpByTopUpAmount(double topUpAmount) {
        danny.getAccount("EUR").topUp(topUpAmount, topUpMethod);
    }

    @Then("His account should return amount of {int}")
    public void hisAccountShouldReturnAmountOf(int amount) {
        Assert.assertEquals(amount, danny.getAccount("EUR").getBalance(), 0);
    }

    @Then("The balance in his euro account should be <newBalance>")
    public void theBalanceInHisEuroAccountShouldBeNewBalance() {
    }

    @And("Selected payment service method has a limit of {double}")
    public void selectedPaymentServiceMethodHasALimitOfServiceLimit(double serviceLimit) {
        topUpMethod.setLimit(serviceLimit);
    }

    @Then("This topUp should be rejected due to insufficient funds")
    public void thisTopUpShouldBeRejectedDueToInsufficientFunds() {
        Assert.assertEquals(0, danny.getAccount("EUR").getBalance(), 0);
    }

}

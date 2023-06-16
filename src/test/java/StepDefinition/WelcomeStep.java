package StepDefinition;

import Pages.MenuPage;
import Utilities.base;
import io.cucumber.java.en.And;
import org.openqa.selenium.NoSuchElementException;

public class WelcomeStep extends base {

    private MenuPage menuPage = new MenuPage();
    private LoginPageStep loginPageStep = new LoginPageStep();

    public static class AccountDetails{
        public static int currentBalance;
    }

    @And("^user choose account (.+)$")
    public void userChooseAccount(String account) {
        menuPage.selectAccount(account);
        if (!menuPage.verifyAccountNumber(account)){
            throw new NoSuchElementException(account+" account number is not selected");
        }
        logger.info(account+"is selected");
    }

    @And("^user (.+) (.+) into account and verify current balance$")
    public void creditAndVerifyCurrentBalance(String option,int amount) throws InterruptedException {
        loginPageStep.selectMenuFromDashboard(option);
        Thread.sleep(2000);
        AccountDetails.currentBalance =  menuPage.getBalanceAmount();
        logger.info("Current balance before transaction is : "+ AccountDetails.currentBalance);
        AccountDetails.currentBalance = amountCreditAndDebitCalculation(AccountDetails.currentBalance,amount,option);
        logger.info("Current balance after transaction is : "+ AccountDetails.currentBalance);
        verifyCurrentBalance(AccountDetails.currentBalance);
    }

    private int amountCreditAndDebitCalculation(int currentAmount, int amount, String action){
        switch (action.toUpperCase()){
            case"CREDIT":
                menuPage.selectDeposit();
                menuPage.setAmount(Integer.toString(amount));
                menuPage.selectDepositOrWithdraw();
                currentAmount = currentAmount + amount;
                break;
            case"DEBIT":
                menuPage.selectWithdraw();
                menuPage.setAmount(Integer.toString(amount));
                menuPage.selectDepositOrWithdraw();
                currentAmount = currentAmount - amount;
                break;
        }
        return currentAmount;
    }

    private void verifyCurrentBalance(int currentBalance){
        int actual = menuPage.getBalanceAmount();
        if (actual != currentBalance){
            throw new NumberFormatException("The actual balance "+actual+" is not tally with expected balance "+ currentBalance);
        }
        logger.info("The both actual and expected balance are matched");
    }

}

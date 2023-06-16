package Pages;

import Utilities.base;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class MenuPage extends base {

    private static class GuiMap{
        //Manager
        private static By addCustomer = By.xpath("//button[@ng-class='btnClass1']");
        private static By openAccount = By.xpath("//button[@ng-class='btnClass2']");
        private static By customers = By.xpath("//button[@ng-class='btnClass3']");

        //Customer
        private static By accountSelect = By.id("accountSelect");
        private static By accountNumber = By.xpath("(//div[@class='center']//strong)[1]");
        private static By balance = By.xpath("(//div[@class='center']//strong)[2]");
        private static By transaction = By.xpath("//button[@ng-class='btnClass1']");
        private static By deposit = By.xpath("//button[@ng-class='btnClass2']");
        private static By withdrawl = By.xpath("//button[@ng-class='btnClass3']");
        private static By amountEnter = By.xpath("//input[@placeholder='amount']");
        private static By depositOrWithdrawBtn = By.xpath("//button[normalize-space(@class)='btn btn-default']");

    }

    public boolean verifyMenuPageDisplayed() {
        fluentwait(GuiMap.addCustomer);
        return getElement(GuiMap.addCustomer).isDisplayed();
    }

    //Manager
    public void clickAddCustomer(){
        fluentwait(GuiMap.addCustomer);
        getElement(GuiMap.addCustomer).click();
    }

    public void clickOpenAccount(){
        fluentwait(GuiMap.openAccount);
        getElement(GuiMap.openAccount).click();
    }


    public void clickCustomer(){
        fluentwait(GuiMap.customers);
        getElement(GuiMap.customers).click();
    }

    //Customer
    public void selectAccount(String acc){
        fluentwait(GuiMap.accountSelect);
        Select select = new Select(getElement(GuiMap.accountSelect));
        select.selectByVisibleText(acc);
    }

    public void selectTransaction(){
        fluentwait(GuiMap.transaction);
        getElement(GuiMap.transaction).click();
    }

    public void selectDeposit(){
        fluentwait(GuiMap.deposit);
        getElement(GuiMap.deposit).click();
    }

    public void selectWithdraw(){
        fluentwait(GuiMap.withdrawl);
        getElement(GuiMap.withdrawl).click();
    }

    public boolean verifyAccountNumber(String accNo){
        fluentwait(GuiMap.accountNumber);
       return getElement(GuiMap.accountNumber).getText().equals(accNo);
    }

    public boolean isTransactionDisplayed() {
        fluentwait(GuiMap.accountNumber);
        return getElement(GuiMap.accountNumber).isDisplayed();
    }

    public int getBalanceAmount(){
        fluentwait(GuiMap.balance);
        String balanceText = getElement(GuiMap.balance).getText();
        int balanceAmount = Integer.parseInt(balanceText);
        return balanceAmount;
    }

    public boolean isDepositOrWithdrawDisplayed(){
        fluentwait(GuiMap.amountEnter);
        return getElement(GuiMap.amountEnter).isDisplayed();
    }
    public void setAmount(String amount){
        fluentwait(GuiMap.amountEnter);
        getElement(GuiMap.amountEnter).clear();
        getElement(GuiMap.amountEnter).sendKeys(amount);
    }

    public void selectDepositOrWithdraw(){
        fluentwait(GuiMap.depositOrWithdrawBtn);
        getElement(GuiMap.depositOrWithdrawBtn).click();
    }
}

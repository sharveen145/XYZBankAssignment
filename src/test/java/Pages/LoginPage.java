package Pages;

import Utilities.base;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;


public class LoginPage extends base  {

    private static class GuiMap{
        //Manager
        private static By homeBtn = By.xpath("//button[contains(@class,'btn') and contains(@class,'home')]");
        private static By customerLoginBtn = By.xpath("//button[contains(@class,'btn btn-primary') and contains(text(),'"+TextMap.customerLoginText+"')]");
        private static By bankManagerLoginBtn = By.xpath("//button[contains(@class,'btn btn-primary') and contains(text(),'"+TextMap.bankManagerLoginText+"')]");
        //Customer
        private static By yourNameDd = By.id("userSelect");
        private static By loginBtn = By.xpath("//button[contains(text(),'"+TextMap.loginText+"')]");

    }
    public static class TextMap {
        static String customerLoginText ="Customer Login";
        static String bankManagerLoginText ="Bank Manager Login";
        static String loginText ="Login";
    }

    //Manager
    public void clickCustomerLogin() {
        fluentwait(GuiMap.customerLoginBtn);
        getElement(GuiMap.customerLoginBtn).click();
    }

    public void clickBankManagerLogin() {
        fluentwait(GuiMap.bankManagerLoginBtn);
        getElement(GuiMap.bankManagerLoginBtn).click();
    }

    public void clickHome() {
        fluentwait(GuiMap.homeBtn);
        getElement(GuiMap.homeBtn).click();
    }

    public boolean verifyLoginPage(){
        fluentwait(GuiMap.customerLoginBtn);
       return getElement(GuiMap.customerLoginBtn).getText().equals(TextMap.customerLoginText);
    }

    //Customer
    public boolean isNameDisplayed(){
        fluentwait(GuiMap.yourNameDd);
        return getElement(GuiMap.yourNameDd).isDisplayed();
    }

    public void selectUserDropdown(String user){
        fluentwait(GuiMap.yourNameDd);
        Select select = new Select(getElement(GuiMap.yourNameDd));
        select.selectByVisibleText(user);
    }

    public void clickLoginBtn(){
        fluentwait(GuiMap.loginBtn);
        getElement(GuiMap.loginBtn).isDisplayed();
        getElement(GuiMap.loginBtn).click();
    }

}

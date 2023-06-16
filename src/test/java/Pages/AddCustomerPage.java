package Pages;

import Utilities.base;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;

public class AddCustomerPage extends base{

    private static class GuiMap{
        private static By fName = By.xpath("//input[contains(@placeholder,'"+TextMap.firstName+"')]");
        private static By lName = By.xpath("//input[contains(@placeholder,'"+TextMap.lastName+"')]");
        private static By postCode = By.xpath("//input[contains(@placeholder,'"+TextMap.postCode+"')]");
        private static By addCustomerBtn = By.xpath("//button[contains(@class,'btn-default')]");
    }

    public static class TextMap {
        static String firstName ="First Name";
        static String lastName ="Last Name";
        static String postCode ="Post Code";
        static String customerAddedMessage ="Customer added successfully with customer id :";
    }

    public boolean isAddCustomerPageDisplayed(){
        fluentwait(GuiMap.addCustomerBtn);
        return getElement(GuiMap.addCustomerBtn).isDisplayed();
    }

    public void clickAddCustomerBtn(){
        fluentwait(GuiMap.addCustomerBtn);
         getElement(GuiMap.addCustomerBtn).click();
    }

    public void setFirstName(String fName){
        fluentwait(GuiMap.fName);
        getElement(GuiMap.fName).clear();
        getElement(GuiMap.fName).sendKeys(fName);
    }

    public void setLastName(String fName){
        fluentwait(GuiMap.lName);
        getElement(GuiMap.lName).clear();
        getElement(GuiMap.lName).sendKeys(fName);
    }

    public void setPostCode(String fName){
        fluentwait(GuiMap.postCode);
        getElement(GuiMap.postCode).clear();
        getElement(GuiMap.postCode).sendKeys(fName);
    }

    public void handleCustomerAddedAlert() throws InterruptedException {
        Alert alert = getDriver().switchTo().alert();
        Thread.sleep(1000);
        if (!alert.getText().contains(TextMap.customerAddedMessage)){
            throw new NotFoundException(alert.getText()+" is not matched with "+TextMap.customerAddedMessage);
        }
        logger.info("The user created successfully");
        alert.accept();
    }

}

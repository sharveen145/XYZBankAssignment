package Pages;

import Utilities.base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CustomersPage extends base {
    public static class CustomerDetails{
        public static List<String> custFirstNameList = new ArrayList<>();
        public static List<String> custLastNameList = new ArrayList<>();
    }
    private static class GuiMap{
        private static By customerRow = By.xpath("(//tr[contains(@class,'ng-scope')])");//number of rows
        private static By firstName = By.xpath("//td[1]");
        private static By lastName = By.xpath("//td[2]");
        private static By search = By.xpath("//input[contains(@placeholder,'"+TextMap.searchText+"')]");
        private static By deleteBtn = By.xpath("//button[contains(text(),'"+TextMap.deleteText+"')]");
    }

    public static class TextMap {
        static String deleteText ="Delete";
        static String searchText ="Search Customer";
    }

    public boolean isCustomerPageDisplayed(){
        fluentwait(GuiMap.search);
        return getElement(GuiMap.search).isDisplayed();
    }
    public void getCustomerList(){
        List<WebElement> rows = getDriver().findElements(GuiMap.customerRow);
        List<WebElement> fNameLists = getDriver().findElements(GuiMap.firstName);
        List<WebElement> lNameLists = getDriver().findElements(GuiMap.lastName);
        int rowsSize = rows.size();
        for (int i=5; rowsSize>i; i++) {
                if (rows.get(i).getText() != null) {
                    String firstName = fNameLists.get(i+1).getText();
                    String lastName = lNameLists.get(i+1).getText();
                    CustomerDetails.custFirstNameList.add(firstName);
                    CustomerDetails.custLastNameList.add(lastName);
                }
        }
    }


    public void deleteCustomer(String fName, String lName){
        List<WebElement> rows = getDriver().findElements(GuiMap.customerRow);
        List<WebElement> fNameLists = getDriver().findElements(GuiMap.firstName);
        List<WebElement> lNameLists = getDriver().findElements(GuiMap.lastName);
        List<WebElement> deletes = getDriver().findElements(GuiMap.deleteBtn);
        int rowsSize = rows.size();
        for (int i=5; rowsSize>i; i++) {
                if (rows.get(i) != null) {
                    if (fNameLists.get(i+1).getText().equals(fName))
                    {
                        if (lNameLists.get(i+1).getText().equals(lName)){
                            deletes.get(i).click();
                            break;
                        }
                    }
                }
        }
        logger.info("Customer with First Name: "+ fName+" and Last Name: "+lName+" have been deleted");
    }
}

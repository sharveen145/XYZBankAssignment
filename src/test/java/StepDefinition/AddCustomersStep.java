package StepDefinition;

import Pages.AddCustomerPage;
import Utilities.base;
import io.cucumber.java.en.And;

import java.util.ArrayList;
import java.util.List;

public class AddCustomersStep extends base {

    private AddCustomerPage addCustomerPage = new AddCustomerPage();

    public static class newlyAddedCustomerDetails{
        public static List<String> firstNameList = new ArrayList<>();
        public static List<String> lastNameList = new ArrayList<>();
    }

    @And("^user add customer with (.+), (.+), and (.+)$")
    public void userAddCustomer(String fName, String lName, String postCode) throws InterruptedException {
        addCustomerPage.setFirstName(fName);
        addCustomerPage.setLastName(lName);
        addCustomerPage.setPostCode(postCode);
        addCustomerPage.clickAddCustomerBtn();
        addCustomerPage.handleCustomerAddedAlert();
        newlyAddedCustomerDetails.firstNameList.add(fName);
        newlyAddedCustomerDetails.lastNameList.add(lName);
    }
}

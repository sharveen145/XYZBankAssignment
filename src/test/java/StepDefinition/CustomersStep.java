package StepDefinition;

import Pages.CustomersPage;
import Utilities.base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.AssertJUnit;

public class CustomersStep extends base {

    private CustomersPage customersPage = new CustomersPage();

    @And("^verify the users have added successfully$")
    public void verifyAddedCustomers() {
        customersPage.getCustomerList();
        AssertJUnit.assertArrayEquals("All added first name are not matched",AddCustomersStep.newlyAddedCustomerDetails.firstNameList.toArray(),CustomersPage.CustomerDetails.custFirstNameList.toArray());
        AssertJUnit.assertArrayEquals("All added last name are not matched",AddCustomersStep.newlyAddedCustomerDetails.lastNameList.toArray(),CustomersPage.CustomerDetails.custLastNameList.toArray());
        logger.info("Verified all the customers are added successfully...");
    }

    @Then("^delete user with (.+) and (.+)$")
    public void deleteUsers(String firstName, String lastName) {
        customersPage.deleteCustomer(firstName,lastName);

    }

}

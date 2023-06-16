package StepDefinition;

import Pages.AddCustomerPage;
import Pages.CustomersPage;
import Pages.LoginPage;
import Pages.MenuPage;
import Utilities.base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NotFoundException;

import java.util.NoSuchElementException;


public class LoginPageStep extends base {

    private LoginPage loginPage = new LoginPage();
    private MenuPage menuPage = new MenuPage();
    private AddCustomerPage addCustomerPage = new AddCustomerPage();
    private CustomersPage customersPage = new CustomersPage();

    @Given("^navigate to XYZ Bank and Login as (.+)$")
    public void launchAndLoginToXYZBank(String role) throws NoSuchFieldException, InterruptedException {
        getDriver();
        launchApplication();
        if (!loginPage.verifyLoginPage()){
            throw new NoSuchFieldException("Page Not displayed");
        }
        logger.info("Login page is displayed");
        switch (role.toUpperCase()) {
            case "BANK MANAGER":
                loginPage.clickBankManagerLogin();
                if (!menuPage.verifyMenuPageDisplayed()) {
                    throw new NoSuchFieldException("Login not successful as a "+role);
                }
                break;
            case "CUSTOMER":
                loginPage.clickCustomerLogin();
                if (!loginPage.isNameDisplayed()) {
                    throw new NoSuchFieldException("Login not successful as a "+role);
                }
                break;
            default:
                throw new NoSuchFieldException(role+" user role is not valid. Either should be Customer or BankManager");
        }

        logger.info("Login Successfully, Menu page have displayed");
    }

    @Then("^user click on (.+) menu button from the dashboard$")
    public void selectMenuFromDashboard(String option) {
        switch (option.toUpperCase()){
            case "ADD CUSTOMER":
                menuPage.clickAddCustomer();
                if (!addCustomerPage.isAddCustomerPageDisplayed()){
                    throw new NoSuchElementException("Add Customer page is not displayed");
                }
                logger.info("Add Customer page is displayed");
                break;
            case "OPEN ACCOUNT":
                menuPage.clickOpenAccount();
                break;
            case "CUSTOMERS":
                menuPage.clickCustomer();
                if (!customersPage.isCustomerPageDisplayed()){
                    throw new NoSuchElementException("Customers page is not displayed");
                }
                logger.info("Customers page is displayed");
                break;
            case "TRANSACTION":
                menuPage.selectTransaction();
                break;
            case "CREDIT":
                menuPage.selectDeposit();
                if (!menuPage.isDepositOrWithdrawDisplayed()){
                    throw new NoSuchElementException("Deposit page is not displayed");
                }
                logger.info("Deposit page is displayed");
                break;
            case "DEBIT":
                menuPage.selectWithdraw();
                if (!menuPage.isDepositOrWithdrawDisplayed()){
                    throw new NoSuchElementException("Withdraw page is not displayed");
                }
                logger.info("Withdraw page is displayed");
                break;
            default:
            {
                throw new NotFoundException(option+" option is not available.");
            }
        }
    }

    @Then("^close the browser and quit browser$")
    public void closeTheBrowserAndQuitBrowser() {
        getDriver().close();
        getDriver().quit();
    }

    @When("^user select name as (.+) and click Login$")
    public void selectNameAndClickLogin(String name) {
        loginPage.selectUserDropdown(name);
        loginPage.clickLoginBtn();
        if (!menuPage.isTransactionDisplayed()){
            throw new NoSuchElementException("Welcome page not displayed");
        }
        logger.info("Welcome page displayed successfully");
    }
}

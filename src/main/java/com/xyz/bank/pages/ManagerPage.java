
package com.xyz.bank.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class ManagerPage extends BasePage {
    @FindBy(css = "button[ng-click='addCust()']")
    private WebElement addCustomerBtn;

    @FindBy(css = "button[ng-click='openAccount()']")
    private WebElement openAccountBtn;

    @FindBy(css = "button[ng-click='showCust()']")
    private WebElement customersBtn;

    // Add Customer Form
    @FindBy(css = "input[ng-model='fName']")
    private WebElement firstNameInput;

    @FindBy(css = "input[ng-model='lName']")
    private WebElement lastNameInput;

    @FindBy(css = "input[ng-model='postCd']")
    private WebElement postCodeInput;

    @FindBy(css = "button[type='submit']")
    private WebElement submitCustomerBtn;

    // Open Account Form
    @FindBy(css = "#userSelect")
    private WebElement customerSelect;

    @FindBy(css = "#currency")
    private WebElement currencySelect;

    @FindBy(css = "button[type='submit']")
    private WebElement processBtn;

    // Customers Table
    @FindBy(css = "input[ng-model='searchCustomer']")
    private WebElement searchCustomerInput;

    @FindBy(css = ".table-bordered")
    private WebElement customersTable;

    // Error messages container
    @FindBy(css = ".error-messages") // Update this selector based on your app's structure
    private WebElement errorMessages;

    public ManagerPage(WebDriver driver) {
        super(driver);
    }

    public void addCustomer(String firstName, String lastName, String postCode) {
        waitForElementToBeClickable(addCustomerBtn);
        addCustomerBtn.click();

        waitForElementToBeVisible(firstNameInput);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postCodeInput.sendKeys(postCode);
        submitCustomerBtn.click();
    }

    public void openAccount() {
        waitForElementToBeClickable(openAccountBtn);
        openAccountBtn.click();
    }

    public void selectCustomerInAccountForm(String customerName) {
        waitForElementToBeVisible(customerSelect);
        Select select = new Select(customerSelect);
        select.selectByVisibleText(customerName);
    }

    public void selectCurrency(String currency) {
        waitForElementToBeVisible(currencySelect);
        Select select = new Select(currencySelect);
        select.selectByVisibleText(currency);
    }

    public void submitAccountForm() {
        waitForElementToBeClickable(processBtn);
        processBtn.click();
    }

    public void viewCustomers() {
        waitForElementToBeClickable(customersBtn);
        customersBtn.click();
    }

    public void searchCustomer(String customerName) {
        waitForElementToBeVisible(searchCustomerInput);
        searchCustomerInput.clear();
        searchCustomerInput.sendKeys(customerName);
    }

    public void deleteCustomer(String postcode) {
        try {

            String rowXpath = "//tr[td[contains(text(), '" + postcode + "')]]";

            String deleteButtonXpath = rowXpath + "//button[contains(text(),'Delete')]";


            WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(deleteButtonXpath)));


            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(deleteButton));

            // Click the Delete button
            Thread.sleep(3000);
            deleteButton.click();

            System.out.println("✅ Successfully deleted customer with Postcode: " + postcode);
        }
        catch (TimeoutException | InterruptedException e) {
            System.out.println("❌ No matching customer found with Postcode: " + postcode);
        }
    }



    public boolean isCustomerPresent(String firstName, String lastName) {
        String customerXPath = String.format(
                "//td[contains(text(), '%s')]/../td[contains(text(), '%s')]",
                firstName, lastName
        );
        try {
            return driver.findElement(By.xpath(customerXPath)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getValidationErrors() {
        try {
            waitForElementToBeVisible(errorMessages);
            return errorMessages.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
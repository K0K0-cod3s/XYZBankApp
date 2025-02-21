//package com.xyz.bank.pages;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//
//public class ManagerPage extends BasePage {
//    @FindBy(css = "button[ng-click='addCust()']")
//    private WebElement addCustomerBtn;
//
//    @FindBy(css = "button[ng-click='openAccount()']")
//    private WebElement openAccountBtn;
//
//    @FindBy(css = "button[ng-click='showCust()']")
//    private WebElement customersBtn;
//
//    // Add Customer Form
//    @FindBy(css = "input[ng-model='fName']")
//    private WebElement firstNameInput;
//
//    @FindBy(css = "input[ng-model='lName']")
//    private WebElement lastNameInput;
//
//    @FindBy(css = "input[ng-model='postCd']")
//    private WebElement postCodeInput;
//
//    @FindBy(css = "button[type='submit']")
//    private WebElement submitCustomerBtn;
//
//    public ManagerPage(WebDriver driver) {
//        super(driver);
//    }
//
//    public void addCustomer(String firstName, String lastName, String postCode) {
//        waitForElementToBeClickable(addCustomerBtn);
//        addCustomerBtn.click();
//
//        waitForElementToBeVisible(firstNameInput);
//        firstNameInput.sendKeys(firstName);
//        lastNameInput.sendKeys(lastName);
//        postCodeInput.sendKeys(postCode);
//        submitCustomerBtn.click();
//    }
//
//    public void openAccount() {
//        waitForElementToBeClickable(openAccountBtn);
//        openAccountBtn.click();
//    }
//
//    public void viewCustomers() {
//        waitForElementToBeClickable(customersBtn);
//        customersBtn.click();
//    }
//}

package com.xyz.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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

    public void deleteCustomer(String customerName) {
        String deleteButtonXPath = String.format(
                "//td[contains(text(), '%s')]/..//button[contains(text(), 'Delete')]",
                customerName
        );
        WebElement deleteButton = driver.findElement(By.xpath(deleteButtonXPath));
        waitForElementToBeClickable(deleteButton);
        deleteButton.click();
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
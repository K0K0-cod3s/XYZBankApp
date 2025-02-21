package com.xyz.bank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(css = "button[ng-click='customer()']")
    private WebElement customerLoginBtn;

    @FindBy(css = "button[ng-click='manager()']")
    private WebElement bankManagerLoginBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CustomerLoginPage navigateToCustomerLogin() {
        waitForElementToBeClickable(customerLoginBtn);
        customerLoginBtn.click();
        return new CustomerLoginPage(driver);
    }

    public ManagerPage navigateToManagerLogin() {
        waitForElementToBeClickable(bankManagerLoginBtn);
        bankManagerLoginBtn.click();
        return new ManagerPage(driver);
    }
}
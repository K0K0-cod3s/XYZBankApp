package com.xyz.bank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage extends BasePage {
    @FindBy(id = "userSelect")
    private WebElement customerSelect;

    @FindBy(css = "button[type='submit']")
    private WebElement loginBtn;

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    public CustomerAccountPage loginAsCustomer(String customerName) {
        waitForElementToBeVisible(customerSelect);
        Select select = new Select(customerSelect);
        select.selectByVisibleText(customerName);

        waitForElementToBeClickable(loginBtn);
        loginBtn.click();
        return new CustomerAccountPage(driver);
    }
}
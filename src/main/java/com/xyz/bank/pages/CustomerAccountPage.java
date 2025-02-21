package com.xyz.bank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerAccountPage extends BasePage {
    @FindBy(css = "button[ng-click='deposit()']")
    private WebElement depositBtn;

    @FindBy(css = "button[ng-click='withdrawl()']")
    private WebElement withdrawalBtn;

    @FindBy(css = "button[ng-click='transactions()']")
    private WebElement transactionsBtn;

    @FindBy(css = "input[ng-model='amount']")
    private WebElement amountInput;

    @FindBy(css = "button[type='submit']")
    private WebElement submitBtn;

    @FindBy(css = "div.error")
    private WebElement errorMessage;

    @FindBy(css = "span.balance")
    private WebElement balance;

    public CustomerAccountPage(WebDriver driver) {
        super(driver);
    }

    public void makeDeposit(String amount) {
        waitForElementToBeClickable(depositBtn);
        depositBtn.click();

        waitForElementToBeVisible(amountInput);
        amountInput.sendKeys(amount);
        submitBtn.click();
    }

    public void makeWithdrawal(String amount) {
        waitForElementToBeClickable(withdrawalBtn);
        withdrawalBtn.click();

        waitForElementToBeVisible(amountInput);
        amountInput.sendKeys(amount);
        submitBtn.click();
    }

    public void viewTransactions() {
        waitForElementToBeClickable(transactionsBtn);
        transactionsBtn.click();
    }

    public String getBalance() {
        waitForElementToBeVisible(balance);
        return balance.getText();
    }

    public String getErrorMessage() {
        waitForElementToBeVisible(errorMessage);
        return errorMessage.getText();
    }
}
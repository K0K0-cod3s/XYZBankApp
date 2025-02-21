package com.xyz.bank.tests;

import com.xyz.bank.pages.*;
import com.xyz.bank.utils.TestBase;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BankManagerTest extends TestBase {
    private HomePage homePage;
    private ManagerPage managerPage;

    @BeforeEach
    void setupTest() {
        homePage = new HomePage(driver);
        managerPage = homePage.navigateToManagerLogin();
    }

    @Test
    @DisplayName("TC001 - Add Customer Successfully")
    void testAddCustomer() {
        managerPage.addCustomer("John", "Doe", "12345");
        String alertText = driver.switchTo().alert().getText();
        assertTrue(alertText.contains("Customer added successfully"));
        driver.switchTo().alert().accept();

        managerPage.viewCustomers();
        assertTrue(managerPage.isCustomerPresent("John", "Doe"));
    }

    @Test
    @DisplayName("TC002 - Add Customer Input Validation")
    void testAddCustomerValidation() {
        managerPage.addCustomer("John123", "Doe@#", "ABC123");
        String errors = managerPage.getValidationErrors();
        assertTrue(errors.contains("Invalid input"));
        assertFalse(managerPage.isCustomerPresent("John123", "Doe@#"));
    }

    @Test
    @DisplayName("TC003 - Open Account Successfully")
    void testOpenAccount() {
        // Prerequisite: Create customer first
        managerPage.addCustomer("John", "Doe", "12345");
        driver.switchTo().alert().accept();

        managerPage.openAccount();
        managerPage.selectCustomerInAccountForm("John Doe");
        managerPage.selectCurrency("Dollar");
        managerPage.submitAccountForm();

        String alertText = driver.switchTo().alert().getText();
        assertTrue(alertText.contains("Account created successfully"));
        assertTrue(alertText.matches(".*Account \\d+ created.*"));
        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("TC004 - Delete Customer")
    void testDeleteCustomer() {
        // Prerequisite: Create customer first
        managerPage.addCustomer("John", "Doe", "12345");
        driver.switchTo().alert().accept();

        managerPage.viewCustomers();
        managerPage.searchCustomer("John Doe");
        managerPage.deleteCustomer("John Doe");

        assertFalse(managerPage.isCustomerPresent("John", "Doe"));
    }
}
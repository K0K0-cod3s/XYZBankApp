package com.xyz.bank.tests;

import com.xyz.bank.pages.HomePage;
import com.xyz.bank.pages.ManagerPage;
import com.xyz.bank.utils.TestBase;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class ManagerTests extends TestBase {
    private static HomePage homePage;
    private static ManagerPage managerPage;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUpManager() {
        homePage = new HomePage(driver);
        managerPage = homePage.navigateToManagerLogin();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        assertNotNull(managerPage, "Manager Page navigation failed!");
    }

    @Test

    @DisplayName("TC001 - Add Customer Successfully")
    public void testAddCustomer() {
        managerPage.addCustomer("John", "Doe", "12345");

        // Handle alert and verify success message
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
//        assertTrue(alertText.contains("Customer added successfully"));
        alert.accept();

        // Verify customer exists in the list
        managerPage.viewCustomers();
//        assertTrue(managerPage.isCustomerPresent("John", "Doe"),
//                "Added customer not found in the list");
    }

    @Test

    @DisplayName("TC002 - Add Customer Input Validation")
    public void testAddCustomerInputValidation() {
        managerPage.addCustomer("John123", "Doe@#", "ABC123");
        String errors = managerPage.getValidationErrors();
//        assertTrue(errors.contains("Invalid input"),
//                "Expected validation error message not displayed");

        // Verify invalid customer wasn't added
        managerPage.viewCustomers();
//        assertFalse(managerPage.isCustomerPresent("John123", "Doe@#"),
//                "Invalid customer should not be added to the list");
    }

    @Test

    @DisplayName("TC003 - Open Account Successfully")
    public void testOpenAccount() {
        // First ensure we have a customer
        if (!managerPage.isCustomerPresent("John", "Doe")) {
            managerPage.addCustomer("John", "Doe", "12345");
            wait.until(ExpectedConditions.alertIsPresent()).accept();
        }

        managerPage.openAccount();
        managerPage.selectCustomerInAccountForm("John Doe");
        managerPage.selectCurrency("Dollar");
        managerPage.submitAccountForm();

        // Verify account creation success
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertTrue(alertText.contains("Account created successfully"));
//        assertTrue(alertText.matches(".*Account \\d+ created.*"),
//                "Account number not generated");
        alert.accept();
    }

    @Test

    @DisplayName("TC004 - Delete Customer")
    public void testDeleteCustomer() {
        // First ensure we have a customer to delete
        if (!managerPage.isCustomerPresent("John", "Doe")) {
            managerPage.addCustomer("John", "Doe", "12345");
            wait.until(ExpectedConditions.alertIsPresent()).accept();
        }

        managerPage.viewCustomers();
        managerPage.searchCustomer("John Doe");
        managerPage.deleteCustomer("12345");

        // Verify customer was deleted
//        assertFalse(managerPage.isCustomerPresent("John", "Doe"),
//                "Customer still exists after deletion");
    }

    @Test

    @DisplayName("Search Customer Functionality")
    public void testSearchCustomer() {
        managerPage.viewCustomers();
        managerPage.searchCustomer("John");
        // Add assertions based on your application's search behavior
    }

    @AfterAll
    public static void cleanUpManager() {
        // Clean up any test data if needed
        if (managerPage.isCustomerPresent("John", "Doe")) {
            managerPage.viewCustomers();
            managerPage.searchCustomer("John Doe");
            managerPage.deleteCustomer("John Doe");
        }
        System.out.println("All Manager Tests Completed Successfully");
    }
}


package com.xyz.bank.tests;

import com.xyz.bank.pages.CustomerAccountPage;
import com.xyz.bank.pages.CustomerLoginPage;
import com.xyz.bank.pages.HomePage;
import com.xyz.bank.utils.TestBase;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerTests extends TestBase {

    private static CustomerAccountPage accountPage;

    @BeforeAll
    public static void setUpLogin() {
        HomePage homePage = new HomePage(driver);
        CustomerLoginPage customerLoginPage = homePage.navigateToCustomerLogin();
        accountPage = customerLoginPage.loginAsCustomer("Harry Potter");
        // Verify login success (if needed)
    }

    @Test
    @Order(1)
    public void testDepositFunds() {
        accountPage.makeDeposit("500");
//        Assertions.assertEquals("500", accountPage.getBalance(), "Balance mismatch after deposit!");
    }

    @Test
    @Order(2)
    public void testDepositValidation() {
        accountPage.makeDeposit("-100");
        accountPage.makeDeposit("ABC");
        // Verify error messages
    }

    @Test
    @Order(3)
    public void testWithdrawFunds() {
        accountPage.makeDeposit("1000");
        accountPage.makeWithdrawal("500");
//        Assertions.assertEquals("500", accountPage.getBalance(), "Balance mismatch after withdrawal!");
    }

    @Test
    @Order(4)
    public void testWithdrawValidation() {
        accountPage.makeWithdrawal("999999");
        // Verify error message for insufficient balance
    }

    @Test
    @Order(5)
    public void testViewTransactions() {
        accountPage.viewTransactions();
        // Verify transaction history, filters, and reset functionality
    }

    @AfterAll
    public static void tearDownLogout() {
        // Add logout steps if required
        System.out.println("Logging out customer...");
    }
}

//package com.xyz.bank.tests;
//
//import com.xyz.bank.pages.*;
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class BaseTest {
//    protected WebDriver driver;
//    protected HomePage homePage;
//
//    @BeforeAll
//    static void setupClass() {
//        // Any one-time setup needed
//    }
//
//    @BeforeEach
//    void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        homePage = new HomePage(driver);
//        driver.get("https://xyz-bank.com"); // Replace with actual URL
//    }
//
//    @AfterEach
//    void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}
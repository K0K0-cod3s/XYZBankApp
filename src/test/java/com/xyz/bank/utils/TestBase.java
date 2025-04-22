//package com.xyz.bank.utils;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class TestBase {
//    protected static WebDriver driver;
//
//    @BeforeAll
//    public static void setUp() {
//        // WebDriverManager handles downloading and path setup
//        WebDriverManager.chromedriver().setup();
//
//
//        // Initialize WebDriver without manually setting the driver path
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
//
//        System.out.println("hi");
//    }
//
//    @AfterAll
//    public static void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}
package com.xyz.bank.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
    protected static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis()); // Unique profile
        options.addArguments("--headless");  // Run in headless mode (remove if you need UI)
        options.addArguments("--disable-gpu"); // Recommended for headless mode
        options.addArguments("--no-sandbox"); // Helps in CI/CD environments
        options.addArguments("--disable-dev-shm-usage"); // Prevents crashes in Docker/Linux

        // Initialize WebDriver with options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

        System.out.println("Test setup complete.");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Test teardown complete.");
        }
    }
}

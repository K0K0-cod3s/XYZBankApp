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
//        WebDriverManager.chromedriver().setup();
//        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
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
import java.nio.file.Paths;

public class TestBase {
    protected static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Automatically manage ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Get project directory dynamically
        String projectPath = System.getProperty("user.dir");

        // Set ChromeDriver path dynamically for different OS
        String driverPath;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            driverPath = Paths.get(projectPath, "drivers", "chromedriver.exe").toString();
        } else {
            driverPath = Paths.get(projectPath, "drivers", "chromedriver").toString();
        }

        System.setProperty("webdriver.chrome.driver", driverPath);

        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

        System.out.println("hi");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

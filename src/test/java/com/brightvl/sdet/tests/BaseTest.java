package com.brightvl.sdet.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {

    protected static final String URL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";
    protected WebDriver driver;
    protected static ChromeOptions options;

    @BeforeAll
    public static void setUpBeforeClass() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--headless");
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

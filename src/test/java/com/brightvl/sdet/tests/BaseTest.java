package com.brightvl.sdet.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {

    protected static final String URL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";
    protected static ChromeOptions options;
    protected WebDriver driver;

    @BeforeAll
    public static void setUpBeforeClass() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--start-maximized",
                "--headless",
                "--window-size=2560,1440",
                "--ignore-certificate-errors",
                "--disable-extensions",
                "--disable-dev-shm-usage");
    }

    @BeforeEach
    @Step("Открытие браузера и загрузка страницы")
    public void setUp() {

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL);
    }

    @AfterEach
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


package com.brightvl.sdet.tests;

import com.brightvl.sdet.page.AddCustomerPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static com.brightvl.sdet.DataGenerator.generateNameFromPostCode;
import static com.brightvl.sdet.DataGenerator.generatePostCode;

public class AddCustomerPageTests {
    private WebDriver driver;
    private static ChromeOptions options;
    private AddCustomerPage page;

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
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        page = new AddCustomerPage(driver);
    }
    @Test
    @Description("Добавление клиента в систему")
    public void testAddCustomer() {
        String postCode = generatePostCode();
        String firstName = generateNameFromPostCode(postCode);
        String lastName = generateNameFromPostCode(postCode);

        page.addCustomer(firstName, lastName, postCode);

        String alertText = driver.switchTo().alert().getText();
        Assertions.assertTrue(alertText.contains("Customer added successfully"));
        driver.switchTo().alert().accept();
    }
}

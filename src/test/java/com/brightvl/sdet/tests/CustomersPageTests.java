package com.brightvl.sdet.tests;


import com.brightvl.sdet.page.CustomersPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomersPageTests {
    private WebDriver driver;
    private static ChromeOptions options;
    private CustomersPage customerPage;

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
        customerPage = new CustomersPage(driver);
    }


    @Test
    @Description("Проверка выполнения сортировки по именам")
    public void testSortCustomersByName() {
        List<String> customerNames = customerPage.getCustomerNames();
        List<String> sortedNames = new ArrayList<>(customerNames);
        sortedNames.sort(Collections.reverseOrder());

        Assertions.assertEquals(sortedNames, customerNames, "Имена клиентов должны быть отсортированы по алфавиту.");
    }


    @Test
    @Description("Проверка удаления пользователя по среднему количеству букв в имени")
    public void testDeleteCustomerWithClosestNameLengthToAverage() {
        List<String> customerNames = customerPage.getCustomerNames();
        List<Integer> nameLengths = customerNames.stream()
                .map(String::length)
                .toList();

        double averageLength = nameLengths.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        String closestName = customerNames.stream()
                .min(Comparator.comparingInt(name -> Math.abs(name.length() - (int) averageLength)))
                .orElse(null);

        Assertions.assertNotNull(closestName, "Клиент для удаления не найден");
        customerPage.deleteCustomerByName(closestName);
        List<String> updatedCustomerNames = customerPage.getCustomerNames();
        Assertions.assertFalse(updatedCustomerNames.contains(closestName),
                "Клиент с именем " + closestName + " должен быть удален.");
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

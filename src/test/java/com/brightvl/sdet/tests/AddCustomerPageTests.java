package com.brightvl.sdet.tests;

import com.brightvl.sdet.pom.AddCustomerPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.brightvl.sdet.DataGenerator.generateNameFromPostCode;
import static com.brightvl.sdet.DataGenerator.generatePostCode;

public class AddCustomerPageTests extends BaseTest {
    private AddCustomerPage page;


    @BeforeEach
    public void setUp() {
        super.setUp();
        page = new AddCustomerPage(driver);
    }

    @Test
    @Description("Добавление клиента в систему")
    public void testAddCustomer() {
        Allure.step("Начало теста по добавлению клиента");

        String postCode = generatePostCode();
        String firstName = generateNameFromPostCode(postCode);
        String lastName = generateNameFromPostCode(postCode);

        page.addCustomer(firstName, lastName, postCode);

        Allure.step("Проверка успешного добавления клиента");
        String alertText = driver.switchTo().alert().getText();
        Assertions.assertTrue(alertText.contains("Customer added successfully"),
                "Ожидалось сообщение 'Customer added successfully', но получено: " + alertText);
        driver.switchTo().alert().accept();
    }
}

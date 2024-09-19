package com.brightvl.sdet.tests;

import com.brightvl.sdet.pom.CustomersPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomersPageTests extends BaseTest {
    private CustomersPage customerPage;

    @BeforeEach
    public void setUp() {
        super.setUp();
        customerPage = new CustomersPage(driver);
    }


    @Test
    @Description("Проверка выполнения сортировки по именам")
    public void testSortCustomersByName() {
        List<String> sortedCustomerNames = customerPage.getCustomerNames(true);

        List<String> customerNames = new ArrayList<>(customerPage.getCustomerNames());
        customerNames.sort(Collections.reverseOrder());

        Allure.step("Сравнение списков");
        Assertions.assertEquals(sortedCustomerNames, customerNames, "Имена клиентов должны быть отсортированы по алфавиту.");
    }

    @Test
    @Description("Проверка удаления пользователя по среднему количеству букв в имени")
    public void testDeleteCustomerWithClosestNameLengthToAverage() {
        System.out.println("Executing testDeleteCustomerWithClosestNameLengthToAverage in thread: " + Thread.currentThread().getName());
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
        Allure.step("Проверка удаления");
        Assertions.assertFalse(updatedCustomerNames.contains(closestName),
                "Клиент с именем " + closestName + " должен быть удален.");
    }
}

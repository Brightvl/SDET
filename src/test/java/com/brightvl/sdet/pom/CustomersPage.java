package com.brightvl.sdet.pom;

import com.brightvl.sdet.elements.CustomersTableRow;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersPage {


    @FindBy(xpath = "//button[contains(@class, 'btn') and contains(text(), 'Customers')]")
    private WebElement customersTabButton;

    @FindBy(xpath = "//a[contains(@ng-click, 'sortType') and contains(@ng-click, 'fName')]")
    private WebElement sortByFirstNameButton;

    @FindBy(css = "table.table tbody tr")
    private List<WebElement> rowsInGroupTable;


    public CustomersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Возвращает имена клиентов, с возможностью сортировки по алфавиту.
     *
     * @param sorted если true, то имена сортируются по алфавиту
     * @return список имен
     */
    @Step("Получение списка имен клиентов, отсортированных по имени")
    public List<String> getCustomerNames(boolean sorted) {
        Allure.step("Открытие вкладки Customers");
        customersTabButton.click();
        if (sorted) {
            Allure.step("Фильтрация клиентов по именам");
            sortByFirstNameButton.click();
        }
        Allure.step("Формирование списка с именами клиентов");
        return rowsInGroupTable.stream()
                .map(CustomersTableRow::new)
                .map(CustomersTableRow::getFirstName)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает имена клиентов. По умолчанию возвращает неотсортированные имена.
     *
     * @return список имен
     */
    @Step("Получение списка имен клиентов")
    public List<String> getCustomerNames() {
        return getCustomerNames(false);
    }

    /**
     * Удаление пользователя из таблицы по имени
     *
     * @param name имя
     */
    @Step("Удаление клиента из таблицы")
    public void deleteCustomerByName(String name) {
        Allure.step("Открытие вкладки Customers");
        customersTabButton.click();
        Allure.step("Формирование списка с именами клиентов");
        rowsInGroupTable.stream()
                .map(CustomersTableRow::new)
                .filter(row -> row.getFirstName()
                        .equals(name)).findFirst()
                .ifPresent(CustomersTableRow::clickDeleteButton);
    }
}

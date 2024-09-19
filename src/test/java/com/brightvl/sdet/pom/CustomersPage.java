package com.brightvl.sdet.pom;

import com.brightvl.sdet.elements.CustomersTableRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersPage {


    @FindBy(xpath = "//button[contains(text(),'Customers')]")
    private WebElement customersTabButton;

    @FindBy(xpath = "//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
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
    public List<String> getCustomerNames(boolean sorted) {
        customersTabButton.click();
        if (sorted) {
            sortByFirstNameButton.click();
        }
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
    public List<String> getCustomerNames() {
        return getCustomerNames(false);
    }

    /**
     * Удаление пользователя из таблицы по имени
     *
     * @param name имя
     */
    public void deleteCustomerByName(String name) {
        customersTabButton.click();

        rowsInGroupTable.stream()
                .map(CustomersTableRow::new)
                .filter(row -> row.getFirstName().equals(name))
                .findFirst()
                .ifPresent(CustomersTableRow::clickDeleteButton);
    }
}

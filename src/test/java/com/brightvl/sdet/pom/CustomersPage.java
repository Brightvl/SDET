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
     * Позволяет вернуть имена из таблицы пользователей
     *
     * @return список имен
     */
    public List<String> getCustomerNames() {
        customersTabButton.click();
        sortByFirstNameButton.click();

        return rowsInGroupTable.stream()
                .map(CustomersTableRow::new)
                .map(CustomersTableRow::getFirstName)
                .collect(Collectors.toList());
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

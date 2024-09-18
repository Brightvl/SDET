package com.brightvl.sdet.page;

import com.brightvl.sdet.elements.GroupTableRow;
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
                .map(GroupTableRow::new)
                .map(GroupTableRow::getFirstName)
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
                .map(GroupTableRow::new)
                .filter(row -> row.getFirstName().equals(name))
                .findFirst()
                .ifPresent(GroupTableRow::clickDeleteButton);
    }
}

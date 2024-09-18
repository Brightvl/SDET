package com.brightvl.sdet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerPage {
    private final WebDriver driver;

    @FindBy(xpath = "//button[contains(text(),'Add Customer')]")
    private WebElement addCustomerTabButton;

    @FindBy(css = "input[placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(css = "input[placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(css = "input[placeholder='Post Code']")
    private WebElement postCodeField;

    @FindBy(css = "button[type='submit']")
    private WebElement addCustomerButton;

    @FindBy(xpath = "//button[contains(text(),'Customers')]")
    private WebElement customersTabButton;

    @FindBy(xpath = "//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
    private WebElement sortByFirstNameButton;

    @FindBy(css = "table.table tbody tr")
    private List<WebElement> rowsInGroupTable;


    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Создает клиента
     *
     * @param firstName имя
     * @param lastName  фамилия
     * @param postCode  почтовый индекс
     */
    public void addCustomer(String firstName, String lastName, String postCode) {
        addCustomerTabButton.click();
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postCodeField.sendKeys(postCode);
        addCustomerButton.click();
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

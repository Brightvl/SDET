package com.brightvl.sdet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerPage {
    private final WebDriver driver;

    private final By addCustomerTabButton = By.xpath("//button[contains(text(),'Add Customer')]");
    private final By firstNameField = By.cssSelector("input[placeholder='First Name']");
    private final By lastNameField = By.cssSelector("input[placeholder='Last Name']");
    private final By postCodeField = By.cssSelector("input[placeholder='Post Code']");
    private final By addCustomerButton = By.cssSelector("button[type='submit']");
    private final By customersTabButton = By.xpath("//button[contains(text(),'Customers')]");
    private final By sortByFirstNameButton = By.xpath("//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]");
    private final By customerNameColumn = By.cssSelector("table.table tbody tr td:nth-child(1)");

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Создает клиента
     *
     * @param firstName имя
     * @param lastName  фамилия
     * @param postCode  почтовый индекс
     */
    public void addCustomer(String firstName, String lastName, String postCode) {
        click(addCustomerTabButton);
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postCodeField, postCode);
        click(addCustomerButton);
    }

    /**
     * Позволяет вернуть имена из таблицы пользователей
     *
     * @return список имен
     */
    public List<String> getCustomerNames() {
        click(customersTabButton);
        click(sortByFirstNameButton);
        return driver.findElements(customerNameColumn).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Удаление пользователя из таблицы по имени
     *
     * @param name имя
     */
    public void deleteCustomerByName(String name) {
        List<WebElement> rows = driver.findElements(By.cssSelector("table.table tbody tr"));
        for (WebElement row : rows) {
            String rowName = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
            if (rowName.equals(name)) {
                row.findElement(By.cssSelector("button")).click();
                break;
            }
        }
    }

    private void click(By locator) {
        driver.findElement(locator).click();
    }

    private void type(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }
}

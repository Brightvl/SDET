package com.brightvl.sdet.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CustomersTableRow {
    private final WebElement root;

    private final By firstNameLocator = By.xpath("./td[1]");
    private final By lastNameLocator = By.xpath("./td[2]");
    private final By postCodeLocator = By.xpath("./td[3]");
    private final By accountNumbersLocator = By.xpath("./td[4]");
    private final By deleteButtonLocator = By.xpath("./td[5]/button");

    public CustomersTableRow(WebElement root) {
        this.root = root;
    }

    public String getFirstName() {
        return root.findElement(firstNameLocator).getText();
    }

    public String getLastName() {
        return root.findElement(lastNameLocator).getText();
    }

    public String getPostCode() {
        return root.findElement(postCodeLocator).getText();
    }

    public String getAccountNumbers() {
        return root.findElement(accountNumbersLocator).getText();
    }

    public void clickDeleteButton() {
        root.findElement(deleteButtonLocator).click();
    }
}

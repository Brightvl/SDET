package com.brightvl.sdet.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CustomersTableRow {
    private final WebElement root;

    public CustomersTableRow(WebElement root) {
        this.root = root;
    }

    public String getFirstName() {
        return root.findElement(By.xpath("./td[1]")).getText();
    }

    public String getLastName() {
        return root.findElement(By.xpath("./td[2]")).getText();
    }

    public String getPostCode() {
        return root.findElement(By.xpath("./td[3]")).getText();
    }

    public String getAccountNumbers() {
        return root.findElement(By.xpath("./td[4]")).getText();
    }

    public void clickDeleteButton() {
        root.findElement(By.xpath("./td[5]/button")).click();
    }
}

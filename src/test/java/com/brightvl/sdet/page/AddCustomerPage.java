package com.brightvl.sdet.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

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

    public AddCustomerPage(WebDriver driver) {
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
}

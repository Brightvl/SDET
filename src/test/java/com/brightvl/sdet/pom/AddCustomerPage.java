package com.brightvl.sdet.pom;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

    @FindBy(xpath = "//button[contains(@class, 'btn') and contains(text(),'Add Customer')]")
    private WebElement addCustomerTabButton;

    @FindBy(css = "input[placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(css = "input[placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(css = "input[placeholder='Post Code']")
    private WebElement postCodeField;

    @FindBy(css = "button[type='submit']")
    private WebElement addCustomerSubmitButton;

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
    @Step("Добавление клиента с именем {firstName}, фамилией {lastName} и почтовым индексом {postCode}")
    public void addCustomer(String firstName, String lastName, String postCode) {
        Allure.step("Открытие вкладки добавления клиента");
        addCustomerTabButton.click();
        Allure.step("Ввод имени клиента");
        firstNameField.sendKeys(firstName);
        Allure.step("Ввод фамилии клиента");
        lastNameField.sendKeys(lastName);
        Allure.step("Ввод почтового индекса клиента");
        postCodeField.sendKeys(postCode);
        Allure.step("Нажатие на кнопку добавления клиента");
        addCustomerSubmitButton.click();
    }
}

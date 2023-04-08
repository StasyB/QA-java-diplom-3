package ru.yandex.practicum.model.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.yandex.practicum.model.user.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    final public static String URL = MainPage.URL + "register";
    private final SelenideElement nameInputField = $(By.xpath("//label[text()='Имя']//following-sibling::input"));
    private final SelenideElement emailInputField = $(By.xpath("//label[text()='Email']//following-sibling::input"));
    private final SelenideElement passwordInputField = $(By.xpath("//input[@type='password']"));
    private final SelenideElement registerButton = $(By.xpath("//button[text()='Зарегистрироваться']"));
    private final SelenideElement incorrectPasswordWarning = $(By.xpath("//p[text()='Некорректный пароль']"));
    private final SelenideElement loginLink = $(By.xpath("//a[text()='Войти']"));

    @Step
    public void inputName(String name) {
        nameInputField.sendKeys(name);
    }

    @Step
    public void inputEmail(String email) {
        emailInputField.sendKeys(email);
    }

    @Step
    public void inputPassword(String password) {
        passwordInputField.sendKeys(password);
    }

    @Step
    public void clickRegister() {
        registerButton.click();
    }

    @Step
    public LoginPage registerUser(User user) {
        inputName(user.getName());
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickRegister();
        return Selenide.page(LoginPage.class);
    }

    @Step
    public RegisterPage registerUserWithIncorrectPassword(User user) {
        inputName(user.getName());
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickRegister();
        return this;
    }

    @Step
    public LoginPage clickLogin() {
        loginLink.click();
        return Selenide.page(LoginPage.class);
    }

    public void incorrectPasswordIsShowed() {
        incorrectPasswordWarning.shouldBe(visible);
    }

}

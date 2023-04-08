package ru.yandex.practicum.model.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.yandex.practicum.model.user.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginButton = $(By.xpath("//button[text()='Войти']"));
    private final SelenideElement loginText = $(By.xpath("//h2[text()='Вход']"));
    private final SelenideElement emailInputField = $(By.xpath("//label[@class='input__placeholder text noselect text_type_main-default'][text()='Email']/parent::div/input"));
    private final SelenideElement passwordInputField = $(By.xpath("//label[@class='input__placeholder text noselect text_type_main-default'][text()='Пароль']/parent::div/input"));
    private final SelenideElement registerLink = $(By.xpath("//a[text()='Зарегистрироваться']"));

    @Step
    public RegisterPage clickRegister() {
        registerLink.shouldBe(visible).click();
        return Selenide.page(RegisterPage.class);
    }

    @Step
    public void clickLogin() {
        loginButton.click();
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
    public MainPage login(User user) {
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickLogin();
        return Selenide.page(MainPage.class);
    }

    public void loginTextIsShowed() {
        loginText.shouldBe(visible);
    }
}

package ru.yandex.practicum.model.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PasswordRecoveryPage {
    public static final String URL = MainPage.URL + "forgot-password";
    private final SelenideElement loginLink = $(By.xpath("//a[@class='Auth_link__1fOlj']"));

    @Step
    public LoginPage clickLogin() {
        loginLink.click();
        return Selenide.page(LoginPage.class);
    }
}

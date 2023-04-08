package ru.yandex.practicum.model.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private final SelenideElement profileText = $(By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']"));
    private final SelenideElement constructorLink = $(By.xpath("//p[text()='Конструктор']"));
    private final SelenideElement burgerLogo = $(By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a"));
    private final SelenideElement logoutButton = $(By.xpath("//button[text()='Выход']"));

    @Step
    public MainPage clickConstructorLink() {
        constructorLink.click();
        return Selenide.page(MainPage.class);
    }

    @Step
    public MainPage clickBurgerLogo() {
        burgerLogo.click();
        return Selenide.page(MainPage.class);
    }

    @Step
    public LoginPage clickLogoutButton() {
        logoutButton.click();
        return Selenide.page(LoginPage.class);
    }

    public void profileTextIsShowed() {
        profileText.shouldBe(visible);
    }
}

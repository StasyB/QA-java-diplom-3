package ru.yandex.practicum.model.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    private final SelenideElement orderButton = $(By.xpath("//button[text()='Оформить заказ']"));
    private final SelenideElement loginButton = $(By.xpath("//button[text()='Войти в аккаунт']"));
    private final SelenideElement profileLink = $(By.xpath("//p[text()='Личный Кабинет']"));
    private final SelenideElement createBurgerText = $(By.xpath("//h1[text()='Соберите бургер']"));
    private final SelenideElement bunsTab = $(By.xpath(".//span[text()='Булки']/.."));
    private final SelenideElement saucesTab = $(By.xpath("//span[text()='Соусы']/.."));
    private final SelenideElement fillingsTab = $(By.xpath("//span[text()='Начинки']/.."));
    private final By activeSection = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");

    @Step
    public LoginPage clickLogin() {
        loginButton.click();
        return Selenide.page(LoginPage.class);
    }

    @Step
    public LoginPage clickProfile() {
        profileLink.click();
        return Selenide.page(LoginPage.class);
    }

    @Step
    public ProfilePage clickProfileAfterLogin() {
        profileLink.click();
        return Selenide.page(ProfilePage.class);
    }

    public String getBunsTabText() {
        return bunsTab.getText();
    }

    public String getSaucesTabText() {
        return saucesTab.getText();
    }

    public String getFillingsTabText() {
        return fillingsTab.getText();
    }

    public void orderButtonIsShowed() {
        orderButton.shouldBe(visible);
    }

    public void createBurgerTextIsShowed() {
        createBurgerText.shouldBe(visible);
    }

    public void showAvailableBuns() {
        bunsTab.click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated(activeSection, "Булки"));
    }

    public void showAvailableSauces() {
        saucesTab.click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated(activeSection, "Соусы"));
    }

    public void showAvailableFillings() {
        fillingsTab.click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated(activeSection, "Начинки"));
    }

}

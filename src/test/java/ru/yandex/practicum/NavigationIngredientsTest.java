package ru.yandex.practicum;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.practicum.client.base.ConfigurationWebDriver;
import ru.yandex.practicum.model.pages.MainPage;

import static org.junit.Assert.assertEquals;
import static ru.yandex.practicum.client.base.ConfigurationWebDriver.setDriver;

public class NavigationIngredientsTest {
    private MainPage mainPage;

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }

    @Before
    public void setUp() {
        setDriver(ConfigurationWebDriver.BROWSER_NAME);
        Selenide.open("/");
        mainPage = new MainPage();
    }

    @After
    public void cleanUp() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    @DisplayName("Test: the crossing to the Buns section is correct.")
    public void crossingSectionBunsIsCorrect() {
        mainPage.showAvailableFillings();
        mainPage.showAvailableSauces();
        mainPage.showAvailableBuns();
        assertEquals("Something went wrong. Didn't find the Buns section.", "Булки", mainPage.getBunsTabText());
    }

    @Test
    @DisplayName("Test: the crossing to the Sauces section is correct.")
    public void crossingSectionSaucesIsCorrect() {
        mainPage.showAvailableFillings();
        mainPage.showAvailableBuns();
        mainPage.showAvailableSauces();
        assertEquals("Something went wrong. Didn't find the Sauces section.", "Соусы", mainPage.getSaucesTabText());
    }

    @Test
    @DisplayName("Test: the crossing to the Fillings section is correct.")
    public void crossingSectionFillingsIsCorrect() {
        mainPage.showAvailableSauces();
        mainPage.showAvailableFillings();
        assertEquals("Something went wrong. Didn't find the Fillings section.", "Начинки", mainPage.getFillingsTabText());
    }
}

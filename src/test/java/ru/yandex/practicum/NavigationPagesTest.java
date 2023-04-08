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
import ru.yandex.practicum.client.UserSteps;
import ru.yandex.practicum.client.base.ConfigurationWebDriver;
import ru.yandex.practicum.model.pages.LoginPage;
import ru.yandex.practicum.model.user.User;
import ru.yandex.practicum.model.user.UserGenerator;

import static ru.yandex.practicum.client.base.ConfigurationWebDriver.setDriver;

public class NavigationPagesTest {
    private User user;
    private UserSteps userSteps;
    private String accessToken;


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
        Selenide.open("\\login");
        user = UserGenerator.getRandomUser();
        userSteps = new UserSteps();
        accessToken = userSteps.register(user).extract().body().jsonPath().get("accessToken");
    }

    @After
    public void cleanUp() {
        WebDriverRunner.closeWebDriver();

        if (accessToken != null) {
            userSteps.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Test: crossing to Profile page is success from main page")
    public void userCanBeCrossingToProfilePage() {
        new LoginPage()
                .login(user)
                .clickProfileAfterLogin()
                .profileTextIsShowed();
    }

    @Test
    @DisplayName("Test: crossing to Main page is success from Profile. Logo button")
    public void userCanBeCrossingToMainPageLogoButton() {
        new LoginPage()
                .login(user)
                .clickProfileAfterLogin()
                .clickBurgerLogo()
                .createBurgerTextIsShowed();
    }

    @Test
    @DisplayName("Test: crossing to Main page is success from Profile. Constructor Link")
    public void userCanBeCrossingToMainPageConstructorLink() {
        new LoginPage()
                .login(user)
                .clickProfileAfterLogin()
                .clickConstructorLink()
                .createBurgerTextIsShowed();
    }
}

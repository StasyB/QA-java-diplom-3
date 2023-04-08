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
import ru.yandex.practicum.model.pages.MainPage;
import ru.yandex.practicum.model.pages.PasswordRecoveryPage;
import ru.yandex.practicum.model.pages.RegisterPage;
import ru.yandex.practicum.model.user.User;
import ru.yandex.practicum.model.user.UserGenerator;

import static ru.yandex.practicum.client.base.ConfigurationWebDriver.setDriver;

public class LoginTest {
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
    @DisplayName("Test: login user is success from main page")
    public void userCanBeLoginFromMainPage() {
        Selenide.open(MainPage.URL, MainPage.class)
                .clickLogin()
                .login(user)
                .orderButtonIsShowed();
    }

    @Test
    @DisplayName("Test: login user is success from profile button")
    public void userCanBeLoginFromProfileButton() {
        Selenide.open(MainPage.URL, MainPage.class)
                .clickProfile()
                .login(user)
                .orderButtonIsShowed();
    }

    @Test
    @DisplayName("Test: login user is success from register page")
    public void userCanBeLoginFromRegisterPage() {
        Selenide.open(RegisterPage.URL, RegisterPage.class)
                .clickLogin()
                .login(user)
                .orderButtonIsShowed();
    }

    @Test
    @DisplayName("Test: login user is success from password recovery page")
    public void userCanBeLoginFromPasswordRecoveryPage() {
        Selenide.open(PasswordRecoveryPage.URL, PasswordRecoveryPage.class)
                .clickLogin()
                .login(user)
                .orderButtonIsShowed();
    }

}

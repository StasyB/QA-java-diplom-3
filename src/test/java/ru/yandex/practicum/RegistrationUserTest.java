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
import ru.yandex.practicum.model.user.User;
import ru.yandex.practicum.model.user.UserCredentials;
import ru.yandex.practicum.model.user.UserGenerator;

import static ru.yandex.practicum.client.base.ConfigurationWebDriver.setDriver;

public class RegistrationUserTest {
    private User user;
    private UserSteps userSteps;

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
        user = UserGenerator.getRandomUser();
        userSteps = new UserSteps();
    }

    @After
    public void cleanUp() {
        WebDriverRunner.closeWebDriver();

        String accessToken = userSteps.login(UserCredentials.from(user)).extract().body().jsonPath().get("accessToken");
        if (accessToken != null) {
            userSteps.delete(accessToken);
        }

    }

    @Test
    @DisplayName("Test: register user is success.")
    public void userCanBeRegisterWithValidData() {
        new MainPage()
                .clickLogin()
                .clickRegister()
                .registerUser(user)
                .loginTextIsShowed();
    }

    @Test
    @DisplayName("Test: register user with incorrect (shortest then 6 char) password is not possible.")
    public void userCanNotBeRegisterWithIncorrectPassword() {
        user.setPassword("1!3wS");
        new MainPage()
                .clickLogin()
                .clickRegister()
                .registerUserWithIncorrectPassword(user)
                .incorrectPasswordIsShowed();
    }

}

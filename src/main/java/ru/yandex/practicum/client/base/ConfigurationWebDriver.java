package ru.yandex.practicum.client.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConfigurationWebDriver {
    public static final String BROWSER_NAME = "chrome"; //use "chrome" or "yandex"
    static String yandexDriverFile = "C:\\Users\\Anastasiia\\Desktop\\JAVA\\WebDrivers\\yandexdriver.exe";

    public static void setDriver(String browserName) {
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site";
        Configuration.holdBrowserOpen = false;

        if (browserName.equals("chrome")) {
            Configuration.browser = "chrome";
        } else if (browserName.equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", yandexDriverFile);
            WebDriverRunner.setWebDriver(new ChromeDriver());
        } else {
            throw new IllegalStateException("The name of browser is incorrect. Please, use chrome or yandex");
        }
    }

}

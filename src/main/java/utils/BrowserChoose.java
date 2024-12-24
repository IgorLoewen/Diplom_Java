package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserChoose {

    public static WebDriver createDriver(String browser) {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        addCommonArguments(options); // Общие аргументы для всех браузеров

        if (browser.equalsIgnoreCase("chrome")) {
            // Специфичные настройки для Chrome
            options.addArguments("--disable-features=FederatedCredentialManagement");

        } else if (browser.equalsIgnoreCase("yandex")) {
            // Специфичные настройки для Yandex
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return new ChromeDriver(options);
    }

    // Общие аргументы для всех браузеров
    private static void addCommonArguments(ChromeOptions options) {
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.addArguments("--enable-automation");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--incognito");
        options.addArguments("--disable-background-networking");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-autofill-keyboard-accessory-view[8]");
    }
}

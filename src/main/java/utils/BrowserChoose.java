package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserChoose {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        ChromeOptions options = new ChromeOptions();
        addCommonArguments(options);

        switch (browser) {
            case "chrome":
                configureChromeDriver();
                break;
            case "yandex":
                configureYandexDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return new ChromeDriver(options);
    }

    private static void configureChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    }

    private static void configureYandexDriver(ChromeOptions options) {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver-yandex");
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
    }

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
    }
}

package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserChoose {

    public static WebDriver createDriver(String browser) {


        setDriverPath(browser.toLowerCase());

        ChromeOptions options = new ChromeOptions();
        addCommonArguments(options);


        switch (browser.toLowerCase()) {
            case "chrome":
                configureChrome(options);
                break;
            case "yandex":
                configureYandex(options);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return new ChromeDriver(options);
    }

    private static void setDriverPath(String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver-yandex");
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
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
        options.addArguments("--disable-background-networking");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-autofill-keyboard-accessory-view[8]");
    }

    private static void configureChrome(ChromeOptions options) {
        options.addArguments("--disable-features=FederatedCredentialManagement");
    }

    private static void configureYandex(ChromeOptions options) {
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
    }
}

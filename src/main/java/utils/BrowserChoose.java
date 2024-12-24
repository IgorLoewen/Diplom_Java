package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserChoose {

    public static WebDriver createDriver(String browser) {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); // Укажите корректный путь к драйверу

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-features=FederatedCredentialManagement");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-gpu");
            return new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("yandex")) {
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex"); // Укажите путь к Yandex Browser
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-extensions");
            options.addArguments("--start-maximized");
            options.addArguments("--enable-automation");
            return new ChromeDriver(options);

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}

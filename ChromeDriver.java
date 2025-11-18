package org.tss.cucumberdemo.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ChromeDriver {
    public static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new org.openqa.selenium.chrome.ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }
}

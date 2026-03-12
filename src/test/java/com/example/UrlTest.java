package com.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UrlTest {
    private WebDriver driver;
    private String testUrl;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        testUrl = System.getProperty("TEST_URL");
        System.out.println("Running tests on URL: " + testUrl);
    }

    @Test
    public void testPageTitleContainsExpectedText() {
        driver.get(testUrl);
        String title = driver.getTitle();
        System.out.println("✅ Page title is: " + title);
        assertTrue("Page title should contain 'Dinesh Reddy'", title.contains("Dinesh Reddy"));
    }
 
    @Test
    public void testHttpStatusIs200() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(testUrl).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("✅ HTTP Response Code: " + responseCode);
        assertEquals("Expected HTTP 200 OK", 200, responseCode);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SumWebTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Headless mode for Jenkins
        options.addArguments("--allow-file-access-from-files"); // Access local HTML
        driver = new ChromeDriver(options);
    }

    @Test
    public void testSum() throws InterruptedException {
        String url = "file:///C:/ProgramData/Jenkins/.jenkins/workspace/SeleniumWebSumTest/src/test/resources/sum.html";
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("num1")));

        WebElement num1 = driver.findElement(By.id("num1"));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement calcBtn = driver.findElement(By.id("calcBtn"));
        WebElement result = driver.findElement(By.id("result"));

        num1.sendKeys("10");
        num2.sendKeys("20");
        calcBtn.click();

        Thread.sleep(1000); // wait for JS to update

        String output = result.getText().trim();
        System.out.println("Output: " + output);
        assertEquals("Sum = 30", output);
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}

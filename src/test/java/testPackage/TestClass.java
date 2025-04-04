package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestClass {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ahmed Hany\\.cache\\selenium\\chromedriver\\win64\\135.0.7049.42\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set explicit wait
    }

    @Test
    public void testDuckDuckGoTitle() {
        driver.get("https://duckduckgo.com/");
        String title = driver.getTitle();
        Assert.assertEquals(title, "DuckDuckGo - Protection. Privacy. Peace of mind.", "Page title does not match!");
    }

    @Test
    public void testDuckDuckGoLogo() {
        driver.get("https://duckduckgo.com/");

        // Wait until the logo is visible
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.logo_homepage")));

        // Assert that logo is displayed
        Assert.assertTrue(logo.isDisplayed(), "DuckDuckGo logo is not displayed!");
    }
    @Test
    public void testDuckDuckGoSearch() {
        // Step 1: Navigate to DuckDuckGo
        driver.get("https://duckduckgo.com/");

        // Step 2: Locate the search bar and enter "Selenium WebDriver"
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchbox_input")));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();

        // Step 3: Wait for search results and get the first result link (Increase wait time)
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".result__url")));

        // Step 4: Assert the first result link is correct
        String firstResultLink = firstResult.getAttribute("href");
        Assert.assertEquals(firstResultLink, "https://www.selenium.dev/documentation/webdriver/", "First result link does not match!");
    }



    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

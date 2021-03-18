import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class YandexGoogleTest {

    private WebDriver webDriver;

    @Test
    public void hello() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.google.ru/");
        WebElement searchInput = webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input"));
        searchInput.sendKeys("Hello World");
        searchInput.submit();
        String title = webDriver.getTitle();
        Assert.assertTrue(title.contains("Hello World"));
        webDriver.get("https://yandex.ru/");
        searchInput = webDriver.findElement(By.cssSelector("#text"));
        searchInput.sendKeys("Hello World");
        searchInput.submit();
        title = webDriver.getTitle();
        Assert.assertTrue(title.contains("Hello World"));
    }

    @After
    public void close() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class LoginTest {
    private WebDriver webDriver;
    WebElement element;


    public void userLogin(String user, String pass) {
        element = webDriver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        element.sendKeys(user);
        element = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));
        element.sendKeys(pass);

    }

    public void click() {
        element = webDriver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        element.click();
    }

    public void userLoginClear() {
        element = webDriver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        element.sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        element = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));
        element.sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
    }

    @Test
    public void loginTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);
        //negative
        userLogin("khk", "jkjkjk");
        click();
        String error = webDriver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/h3")).getText();
        Assert.assertTrue(error.contains("Username and password do not match any user in this service"));
        //positive
        userLoginClear();
        Thread.sleep(2000);
        userLogin("standard_user", "secret_sauce");
        click();
        Thread.sleep(2000);
        String page = webDriver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/div")).getText();
        Assert.assertTrue(page.contains("Products"));
    }

    @After
    public void close() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

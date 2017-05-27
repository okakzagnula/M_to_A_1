import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Serge on 23.05.2017.
 */
public class TestMy {
    private WebDriver driver;


    @BeforeTest
    public void setup() {

        final File file = new File(PropertyLoader.loadProperty("path.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test /* go to the URL */
    public void test1() {
        driver.navigate().to("http://juliemr.github.io/protractor-demo/");
        Assert.assertEquals(driver.getCurrentUrl(), "http://juliemr.github.io/protractor-demo/");
    }

    @Test /* input digits into fields*/
    public void test2() {
        WebElement FirstField = driver.findElement(By.xpath("//input[@ng-model='first']"));
        FirstField.sendKeys("1");
        WebElement SecondField = driver.findElement(By.xpath("//input[@ng-model='second']"));
        SecondField.sendKeys("1");
        Assert.assertEquals(FirstField.getAttribute("value"), "1");
        Assert.assertEquals(SecondField.getAttribute("value"), "1");
    }

    @Test /* select operation with digits */
    public void test3() {
        Select action = new Select(driver.findElement(By.xpath("//select[@ng-model='operator']")));
        action.selectByVisibleText("+");
        String proverka = driver.findElement(By.xpath("//option[1]")).getText();
        Assert.assertEquals(proverka, "+");

    }

    @Test /* click calculation --> check the result */
    public void test4() {

        WebElement goButton = driver.findElement(By.xpath(".//*[@id='gobutton']"));
        goButton.click();
        WebElement result = driver.findElement(By.xpath("//td[3]"));
        Assert.assertEquals(result.getText(), "2");
    }


    @AfterTest

    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
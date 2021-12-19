// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class Test4VVSTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void test4VVS() {
    driver.get("http://localhost:3355/");
    driver.manage().window().setSize(new Dimension(1089, 1040));
    driver.findElement(By.cssSelector("input:nth-child(2)")).click();
    driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys("Ovidiu");
    driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys(Keys.ENTER);
    driver.findElement(By.cssSelector("li:nth-child(1) button:nth-child(1)")).click();
    driver.findElement(By.cssSelector("li:nth-child(2) button:nth-child(1)")).click();
    driver.findElement(By.cssSelector("input:nth-child(4)")).click();
    driver.findElement(By.cssSelector("input:nth-child(4)")).sendKeys("Arad");
    driver.findElement(By.cssSelector("input:nth-child(4)")).sendKeys(Keys.ENTER);
    driver.findElement(By.id("Feedback")).click();
    {
      WebElement dropdown = driver.findElement(By.id("Feedback"));
      dropdown.findElement(By.xpath("//option[. = 'Acceptable']")).click();
    }
    driver.findElement(By.cssSelector("fieldset")).click();
    driver.findElement(By.cssSelector("li:nth-child(2) > em")).click();
    driver.findElement(By.cssSelector("p:nth-child(5)")).click();
    driver.findElement(By.cssSelector("h2")).click();
    driver.findElement(By.cssSelector("p:nth-child(2)")).click();
    driver.findElement(By.cssSelector("center")).click();
    driver.findElement(By.cssSelector("center")).click();
    driver.findElement(By.cssSelector("footer")).click();
  }
}

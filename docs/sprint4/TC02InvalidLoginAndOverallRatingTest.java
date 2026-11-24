import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;

public class TC02InvalidLoginAndOverallRatingTest {
  private WebDriver driver;

  @Before
  public void setUp() {
    driver = new ChromeDriver();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void tC02InvalidLoginAndOverallRating() {
    driver.get("https://buggy.justtestit.org/");
    driver.manage().window().setSize(new Dimension(1280, 900));
    driver.findElement(By.name("login")).sendKeys("non_existent_user_qa");
    driver.findElement(By.name("password")).sendKeys("WrongPass123!");
    driver.findElement(By.cssSelector("button[type='submit']")).click();
    assertThat(driver.findElement(By.cssSelector(".label-warning")).getText(), is("Invalid username/password"));
    driver.findElement(By.cssSelector("a[href='/overall']")).click();
    assertTrue(driver.findElements(By.cssSelector("table.cars tbody tr")).size() > 0);
  }
}
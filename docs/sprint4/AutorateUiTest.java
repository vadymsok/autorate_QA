package ua.kpi.tzyapz.autorate.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Tag("ui")
@DisplayName("АвтоРейт (Buggy Cars Rating) — UI-тести Selenium WebDriver 4")
class AutorateUiTest {

    private static final String BASE_URL = "https://buggy.justtestit.org/";
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void openBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--window-size=1280,900");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(BASE_URL);
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("TC-01: головна сторінка відкривається і містить ключові блоки каталогу")
    void homePageDisplaysMainSections() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.navbar-brand")));
        String title = driver.getTitle();
        List<WebElement> cards = wait.until(d -> {
            List<WebElement> found = d.findElements(By.cssSelector(".card"));
            return found.size() >= 3 ? found : null;
        });

        assertAll("Головна сторінка АвтоРейт",
                () -> assertTrue(title.contains("Buggy"), "Заголовок сторінки має містити Buggy"),
                () -> assertTrue(cards.size() >= 3, "На головній мають відображатися 3 основні картки")
        );
    }

    @Test
    @DisplayName("TC-02: вхід із невірним паролем відображає повідомлення про помилку")
    void invalidLoginShowsErrorMessage() {
        WebElement loginInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        loginInput.sendKeys("non_existent_user_qa");
        passwordInput.sendKeys("WrongPass123!");
        loginBtn.click();

        WebElement errorLabel = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".label-warning"))
        );
        assertAll("Помилка входу",
                () -> assertTrue(errorLabel.isDisplayed()),
                () -> assertFalse(errorLabel.getText().isBlank(), "Текст помилки не має бути порожнім")
        );
    }

    @Test
    @DisplayName("TC-03: перехід до загального рейтингу (Overall Rating) завантажує таблицю моделей")
    void overallRatingOpensModelsTable() {
        WebElement overallCardLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/overall']"))
        );

        overallCardLink.click();
        wait.until(ExpectedConditions.urlContains("/overall"));

        List<WebElement> rows = wait.until(d -> {
            List<WebElement> trs = d.findElements(By.cssSelector("table.cars tbody tr"));
            return trs.isEmpty() ? null : trs;
        });

        assertAll("Таблиця загального рейтингу",
                () -> assertTrue(driver.getCurrentUrl().contains("/overall")),
                () -> assertFalse(rows.isEmpty(), "Таблиця моделей не повинна бути порожньою")
        );
    }

    @Test
    @DisplayName("TC-04: перехід на сторінку реєстрації та валідація обов'язкових полів")
    void registrationFormValidationAndPageLoad() {
        WebElement registerLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/register']"))
        );

        registerLink.click();
        wait.until(ExpectedConditions.urlContains("/register"));

        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 6);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                .sendKeys("qa_artem_" + uniqueSuffix);
        driver.findElement(By.id("firstName")).sendKeys("Artem");
        driver.findElement(By.id("lastName")).sendKeys("Lytvynenko");
        driver.findElement(By.id("password")).sendKeys("Pass123!");
        driver.findElement(By.id("confirmPassword")).sendKeys("DifferentPass123!");

        WebElement mismatchAlert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Passwords do not match')]"))
        );
        WebElement registerBtn = driver.findElement(By.xpath("//button[text()='Register']"));

        assertAll("Валідація форми реєстрації",
                () -> assertTrue(mismatchAlert.isDisplayed()),
                () -> assertFalse(registerBtn.isEnabled(), "Кнопка Register має бути неактивною при невідповідності паролів")
        );
    }
}
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class FillUpTheForm {

    WebDriver driver;

    @BeforeAll
    public void setup() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.get("https://www.digitalunite.com/practice-webform-learners");

        // Handling cookie
        WebElement cookie = driver.findElement(By.id("onetrust-accept-btn-handler"));
            cookie.click();
            Thread.sleep(1000);  // giving time to disappear it properly

    }


    @DisplayName("Form should be filled up successfully")
    @Test
    public void formFillup() throws InterruptedException {
        // Name
        driver.findElement(By.id("edit-name")).sendKeys("Misir Ali");

        //Phone
        driver.findElement(By.id("edit-number")).sendKeys("01078023420");

        //Age
        driver.findElement(By.cssSelector("[for = \"edit-agnew-20-30\"]")).click();

        // Date
        String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        driver.findElement(By.id("edit-date")).sendKeys(today);

        // Email
        driver.findElement(By.id("edit-email")).sendKeys("abc123@gmail.com");

        // Myself
        String myself = "I am beginner in Automation. It is really interesting!";
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys(myself);

        // File upload
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys("E:\\thanku.jpg");
        Thread.sleep(3000);  // file upload comple howar aage next task e gele alert dibe, test fail ashbe. So sleep

        // CheckBox
        driver.findElement(By.id("edit-age")).click();

        // Submit
        driver.findElement(By.id("edit-submit")).click();

        //Assertion
        String expected_text = "Thank you for your submission!";
        String actual_text = driver.findElement(By.className("page-title")).getText();
        Assertions.assertTrue(actual_text.contains(expected_text));

    }


    @AfterAll
    public void close() throws InterruptedException {
        driver.close();
    }

}

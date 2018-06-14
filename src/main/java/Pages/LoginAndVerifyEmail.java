package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;

import java.util.concurrent.TimeUnit;

public class LoginAndVerifyEmail {

    public WebDriver webdriver;
    public WebDriverWait wait;

    @Test
    public void login() throws InterruptedException {
        System.out.println("Under the test");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "//Users//vijetankit//Downloads//chromedriver");
        webdriver = new ChromeDriver(options); // Create a new instance for the Chrome Driver.
        webdriver.get("https://pages.circles.life/");

        webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webdriver.findElement(By.xpath("//*[@id=\"site-navigation\"]/div[2]/div[2]/div/a[1]")).click();
        System.out.println("Clicked sign up");
        Thread.sleep(10000);

        //Handle the bug where Facebook sign in pop up opens Automatically
        String winHandleBefore = webdriver.getWindowHandle();
        for (String winHandle : webdriver.getWindowHandles()) {
            webdriver.switchTo().window(winHandle);
            System.out.println("Window switch");
            System.out.println(webdriver.getTitle());
            if (webdriver.getTitle().contains("Facebook")) {
                webdriver.close();
            }
        }
        webdriver.switchTo().window(winHandleBefore);
        webdriver.findElement(By.xpath("//input[@name='email']")).sendKeys("bhatia.vijeta@gmail.com");
        webdriver.findElement(By.xpath("//input[@name='password']")).sendKeys("testing123");
        webdriver.findElement(By.xpath("//*[@id=\"st-container\"]/div/div/div[2]/div[1]/div[2]/div/div/div[2]/div/div[3]/form/div[4]/div/div/button")).click();
        System.out.println("Submitted successfully");

        Thread.sleep(10000);
        webdriver.findElement(By.xpath("//div[@class='hidden-md-down']/div/a[3]")).click();
        System.out.println("Accounts tab clicked successfully");
        String text= webdriver.findElement(By.xpath("//*[@id=\"st-container\"]/div/div/div[2]/div[1]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div/form/div[3]/div/input")).getAttribute("value");

        //Verify whether the email address in account is correct or not
        Assert.assertEquals(text,"bhatia.vijeta@gmail.com");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        webdriver.close();
    }

    public static void main(String[] args) {

        System.out.println("Inside the main");


    }
}







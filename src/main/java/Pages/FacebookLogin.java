package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FacebookLogin {

    @Test
    public void loginAndPostFacebook() throws InterruptedException {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            System.setProperty("webdriver.chrome.driver", "//Users//vijetankit//Downloads//chromedriver");

            WebDriver webdriver = new ChromeDriver(options);
            WebDriverWait wait = new WebDriverWait(webdriver, 60);


            System.out.println("Under the test");
            webdriver.get("https://pages.circles.life/");
            String parentHandle = webdriver.getWindowHandle(); // get the current window handle
            webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            webdriver.findElement(By.xpath("//*[@id=\"site-navigation\"]/div[2]/div[2]/div/a[1]")).click();
            System.out.println("Clicked sign up");

            Thread.sleep(10000);
            String winHandleBefore = webdriver.getWindowHandle();
            for (String winHandle : webdriver.getWindowHandles()) {
                webdriver.switchTo().window(winHandle);
                System.out.println("Window switch");
                System.out.println(webdriver.getTitle());
                if (webdriver.getTitle().contains("Facebook")) {
                    System.out.println("Got the Facebook Window");
                    webdriver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("vijeta.bhatia88@gmail.com");
                    webdriver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("Circles@123");
                    webdriver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();

                }
            }
            webdriver.switchTo().window(winHandleBefore);
            webdriver.findElement(By.xpath("//*[@id=\"st-container\"]/div/div/div[2]/div/div[3]/div[2]/a[1]/img")).click();
            for (String winHandle : webdriver.getWindowHandles()) {
                webdriver.switchTo().window(winHandle);
                System.out.println("Window switch");
                System.out.println(webdriver.getTitle());
                if (webdriver.getTitle().contains("Circles.Life")) {
                    System.out.println("Got the Circles.Life Facebook Window");
                    Thread.sleep(10000);
                    Actions actions = new Actions(webdriver);
                    actions.moveToElement(webdriver.findElement(By.xpath("//div[starts-with(@id,'js')]//form/div[1]/div[2]/div")));
                    actions.click();

                    //Post a Sample Comment on Circles Page
                    actions.sendKeys("TEST DATA");
                    actions.build().perform();
                    webdriver.findElement(By.xpath("//div[starts-with(@id,'js')]/div[2]/div[2]/div/div[2]/div/span[2]/div/button")).click();
                    Thread.sleep(10000);
                    webdriver.findElement(By.xpath("//*[@id=\"u_0_w\"]/div[8]/a/span[1]")).click();
                    webdriver.findElement(By.xpath("//*[@id=\"PagePostsByOthersPagelet_1520156088270664\"]/div/div/div[1]/a")).click();

                    //Verify that the comment got posted
                    Assert.assertTrue(webdriver.getPageSource().contains("TEST DATA"));

                    webdriver.close();
                }
            }
            webdriver.switchTo().window(parentHandle);
            webdriver.close();

        }

    public static void main (String args[]) {
    }
}


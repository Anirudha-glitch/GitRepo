import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageobjects.LandingPage;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
       // LandingPage landingPage = new LandingPage(driver);
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("abcxyz@gmail.com");
       driver.findElement(By.cssSelector("*[id=\'userPassword\']")).sendKeys("Anirudha12@");
       Thread.sleep(3000);
       driver.findElement(By.cssSelector(".login-btn")).click();
      // From line number 21 to 27 present in Landing Page



        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        //From 32 and 33 is in AbstractComponent class

        List<WebElement> productname = driver.findElements(By.cssSelector(".col-lg-4"));
        // 36 is in product catalog

       WebElement prod =  productname.stream().filter(product->product.findElement(By.tagName("h5")).getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);
       prod.findElement(By.xpath("//button[contains(text(),' Add To Cart')]")).click();


// 1. Wait until the toast message is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

// 2. Wait until the toast message is invisible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

// Now click on the cart button (or next button)
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
List<WebElement> cartprod = driver.findElements(By.cssSelector(".cartSection h3"));
Boolean match = cartprod.stream().anyMatch(cart->cart.getText().equalsIgnoreCase("ZARA COAT 3"));
Assert.assertTrue(match);


        driver.findElement(By.cssSelector(".totalRow button")).click();
        Actions a = new Actions(driver);
        //a.moveToElement(driver.findElement(By.cssSelector("*[placeholder='Select Country']"))).sendKeys("India").build().perform();
       a.sendKeys(driver.findElement(By.cssSelector("*[placeholder='Select Country']")), "India").build().perform();
       // driver.close();
        driver.findElement(By.cssSelector(".ta-item:last-of-type")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(),"THANKYOU FOR THE ORDER.");
       Thread.sleep(3000);
       driver.findElement(By.xpath("//button[text()=' Sign Out ']")).click();

    }


}

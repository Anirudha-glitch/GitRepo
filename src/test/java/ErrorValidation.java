import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.Retry;
import pageobjects.CartPage;
import pageobjects.Confirmationpage;
import pageobjects.ProductCatalog;
import pageobjects.checkoutPage;

public class ErrorValidation extends BaseTest {
    @Test(groups={"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void errorValidation() throws InterruptedException, IOException {
        String productName = "ZARA COAT 3";

        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // LandingPage landingPage= launchApplication();
        landingPage.LoginApplication("abcxyz@gmail.com", "Anirudha12@23");
        //Thread.sleep(3000);
        Assert.assertEquals("Incorrect email  password.", landingPage.getError());

        //driver.findElement(By.cssSelector(".totalRow button")).click();

    }

@Test
public void productvalidation() throws InterruptedException, IOException {
        String productName = "ZARA COAT 3";

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // LandingPage landingPage= launchApplication();
        ProductCatalog productCatlog = landingPage.LoginApplication("abcxyz@gmail.com", "Anirudha12@");
       // Thread.sleep(3000);

        List<WebElement> productname = productCatlog.getProductList();
        productCatlog.addProductToCart(productName);
        Thread.sleep(3000);
        CartPage cp = productCatlog.gotoCartPage();
        Boolean match = cp.Verifyroduct("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}

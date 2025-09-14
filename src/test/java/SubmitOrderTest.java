import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageobjects.CartPage;
import pageobjects.Confirmationpage;
import pageobjects.OrderPage;
import pageobjects.ProductCatalog;
import pageobjects.checkoutPage;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
@Test(dataProvider="getOrders",groups={"Purchase"})
 public void submitorder(HashMap<String, String> input) throws InterruptedException, IOException {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       // LandingPage landingPage= launchApplication();
        ProductCatalog productCatlog=landingPage.LoginApplication(input.get("email"), input.get("password"));
       Thread.sleep(3000);

        List<WebElement> productname = productCatlog.getProductList();
        productCatlog.addProductToCart(input.get("productName"));
        Thread.sleep(3000);
        CartPage cp = productCatlog.gotoCartPage();

         Boolean match = cp.Verifyroduct(input.get("productName"));

       System.out.println("Welcome");

Assert.assertTrue(match);

        checkoutPage ckp = cp.goToCheckout();
        ckp.selectcountry();
        Confirmationpage cnf = ckp.submit();
        String confirmationMessage = cnf.message();

        Assert.assertEquals(confirmationMessage,"THANKYOU FOR THE ORDER.");

        //driver.findElement(By.cssSelector(".totalRow button")).click();




    }
    @Test(dependsOnMethods = {"submitorder"})
    public void OrderHistory() {
        ProductCatalog productCatlog=landingPage.LoginApplication("abcxyz@gmail.com", "Anirudha12@");
        OrderPage ordersPage=productCatlog.gotoOrderPage();
        Assert.assertTrue(ordersPage.VerifyOrder(productName));

    }



    @DataProvider
    public Object[][] getOrders() throws IOException {
      //return new Object[][]{{"abcxyz@gmail.com", "Anirudha12@","ZARA COAT 3"}, {"abcxyz@gmail.com", "Anirudha12@","ADIDAS ORIGINAL"}};

//        HashMap<String,String> map=new HashMap<>();
//        map.put("email","abcxyz@gmail.com");
//        map.put("password","Anirudha12@");
//        map.put("productName","ZARA COAT 3");
//
//        HashMap<String,String> map1=new HashMap<>();
//        map1.put("email","abcxyz@gmail.com");
//        map1.put("password","Anirudha12@");
//        map1.put("productName","ADIDAS ORIGINAL");
        List<HashMap<String,String>> data = getJsonDataToMap();
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }
}

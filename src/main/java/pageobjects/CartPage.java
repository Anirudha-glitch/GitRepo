package pageobjects;
//1.in this class only locators belonging to landing page means login page like username, password, login button
//2.When you initialize driver it has no life so to giving life we have to use constructor so for that you have to create object of your landing page class and pass this driver as a parameter and pagefactory method.
//3. From child to parent you have to send variable so through super keyword we can pas from child to parent.

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
      WebDriver driver;

      public CartPage(WebDriver driver){

          super(driver);
          this.driver = driver;
          PageFactory.initElements(driver, this);


      }

      @FindBy(css=".cartSection h3")
      List<WebElement> cartSection;



    @FindBy(css=".totalRow button")
    WebElement checkout;

    public Boolean Verifyroduct(String productName){
        Boolean match = cartSection.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
        return match;
    }

     public checkoutPage goToCheckout(){
        checkout.click();
        checkoutPage ckp = new checkoutPage(driver);
        return ckp;
     }







}

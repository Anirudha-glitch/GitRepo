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

public class OrderPage extends AbstractComponent {
      WebDriver driver;

      public OrderPage(WebDriver driver){

          super(driver);
          this.driver = driver;
          PageFactory.initElements(driver, this);


      }

      @FindBy(css="tr td:nth-child(3)")
      List<WebElement> productnames;



//    @FindBy(css=".totalRow button")
//    WebElement checkout;

    public Boolean VerifyOrder(String productName){
        Boolean match = productnames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
    }









}

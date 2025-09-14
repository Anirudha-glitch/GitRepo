package pageobjects;
//1.in this class only locators belonging to landing page means login page like username, password, login button
//2.When you initialize driver it has no life so to giving life we have to use constructor so for that you have to create object of your landing page class and pass this driver as a parameter and pagefactory method.
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
      WebDriver driver;

      public ProductCatalog(WebDriver driver){
          super(driver);
          this.driver = driver;
          PageFactory.initElements(driver, this);


      }
    //List<WebElement> productname = driver.findElements(By.cssSelector(".col-lg-4"));
      //WebElement userEmail =  driver.findElement(By.xpath("//input[@id='userEmail']"));
      //Pagefactory reduce the symbol


    @FindBy(css=".col-lg-4")
    List<WebElement> productsName;

    By productBy = By.cssSelector(".mb-3")  ;
    By addTocart1 = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

      public List<WebElement> getProductList(){
          waitForElementAppear(productBy);
          return productsName;
      }

      public WebElement getProductByName(String nameofProduct){
          WebElement prod =  productsName.stream().filter(product->
                  product.findElement(By.tagName("h5")).getText().equalsIgnoreCase(nameofProduct)).findFirst().orElse(null);
       return prod;

      }

      public void addProductToCart(String name){
          WebElement prod=getProductByName(name);
          prod.findElement(addTocart1).click();
          waitForElementAppear(toastMessage);
          waitForElementDisppear(toastMessage);
      }


   






}

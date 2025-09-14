package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.CartPage;
import pageobjects.OrderPage;

public class AbstractComponent {
   WebDriver driver;

   @FindBy(css="[routerlink*='cart']")
   WebElement cartbutton;

    @FindBy(css="[routerlink*='myorders']")
    WebElement Order;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementAppear(By findBy){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
}

    public void  waitForWebElementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

  public CartPage gotoCartPage(){
      cartbutton.click();
      CartPage cp = new CartPage(driver);
      return cp;
  }
    public OrderPage gotoOrderPage(){
        Order.click();
        OrderPage op = new OrderPage(driver);
        return op;
    }

    public void waitForElementDisppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

}

package pageobjects;
//1.in this class only locators belonging to landing page means login page like username, password, login button
//2.When you initialize driver it has no life so to giving life we have to use constructor so for that you have to create object of your landing page class and pass this driver as a parameter and pagefactory method.
//3. From child to parent you have to send variable so through super keyword we can pas from child to parent.

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
      WebDriver driver;

      public LandingPage(WebDriver driver){

          super(driver);
          this.driver = driver;
          PageFactory.initElements(driver, this);


      }

      //WebElement userEmail =  driver.findElement(By.xpath("//input[@id='userEmail']"));
      //Pagefactory reduce the symbol
    @FindBy(xpath="//input[@id='userEmail']")
    WebElement userEmail;

    @FindBy(css="*[id=\'userPassword\']")
    WebElement userPassword;

    @FindBy(css=".login-btn")
    WebElement loginButton;

    @FindBy(css="div[aria-label='Incorrect email or password.']")
    WebElement errorMessage;

    public ProductCatalog LoginApplication(String username, String password){
        userEmail.sendKeys(username);
        userPassword.sendKeys(password);
        loginButton.click();
        ProductCatalog productCatlog = new ProductCatalog(driver);
        return productCatlog;
    }

    public String getError(){
        waitForWebElementToAppear(errorMessage);
       return errorMessage.getText();
    }

    public void goTo(){

        driver.get("https://rahulshettyacademy.com/client");
    }







}

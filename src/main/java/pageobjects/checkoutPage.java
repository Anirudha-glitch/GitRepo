package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class checkoutPage extends AbstractComponent {
    WebDriver driver;
    public checkoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="*[placeholder='Select Country']")
    WebElement selctCountry;

    @FindBy(css=".ta-item:last-of-type")
    WebElement LIST;

    @FindBy(css=".action__submit")
    WebElement BUTTON;

    By results = By.cssSelector(".ta-results");

    public void selectcountry(){
        Actions a = new Actions(driver);
        //a.moveToElement(driver.findElement(By.cssSelector("*[placeholder='Select Country']"))).sendKeys("India").build().perform();
        a.sendKeys(selctCountry, "India").build().perform();
        // driver.close();

        waitForElementAppear(results);

        LIST.click();


    }
     public Confirmationpage submit(){

         BUTTON.click();
         return new Confirmationpage(driver);

     }



}

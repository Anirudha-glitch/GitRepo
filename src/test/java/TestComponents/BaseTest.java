package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageobjects.LandingPage;

public class BaseTest {
    public  WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializer() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C://Users//z0053vpk//IdeaProjects//Selenium_Framework//src//main//java//Resources//global.properties");

        prop.load(fis);
        String browsername = prop.getProperty("browser");
        if(browsername.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;

    }

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        String jsonContent = FileUtils.readFileToString(new File("C://Users//z0053vpk//IdeaProjects//Selenium_Framework//src//test//java//data//Purchaseorder.json"));
        //String to HashMap
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

    public String getScreenshot(String testcase, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+testcase+".png");
        FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), file);
        return System.getProperty("user.dir")+"//reports//"+testcase+".png";

    }

   @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        WebDriver driver = initializer();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void logout(){
       // driver.findElement(By.xpath("//button[text()=' Sign Out ']")).click();
        driver.close();

    }


}

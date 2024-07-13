package driveractions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageobjects.loginpage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class baseclass {
   public WebDriver driver;
  public loginpage login;
    public WebDriver initiateBrowser() throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\globalResources.properties");
        properties.load(fis);
        String browser = System.getProperty("browser")!=null?System.getProperty("browser"):properties.getProperty("Browser");
        if (browser.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browser.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        } else if (browser.equals("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        return driver;
    }
    @BeforeMethod(alwaysRun = true)//it will run even if it's not contain group name specified in TestNg
    public loginpage launchapplication() throws IOException {
       driver =  initiateBrowser();
         login = new loginpage(driver);
        login.gotoUrl();
        return login;
    }
    @AfterMethod(alwaysRun = true)
    public void close(){
        driver.quit();
    }
    public List<HashMap<String, String>> getjsonToMap(String Filename) throws IOException {
       //json to string
        String jsoncontent = FileUtils.readFileToString(new File(Filename));
        //string to hashmap jackson databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }
    public String getscreenShot(String TestcaseName,WebDriver driver) throws IOException {
        TakesScreenshot screenshot =  (TakesScreenshot)driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir")+"//screenshots//"+"//"+TestcaseName+".png");
        FileUtils.copyFile(source,dest);
        return System.getProperty("user.dir")+"//screenshots//"+"//"+TestcaseName+".png";
    }
}


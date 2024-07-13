package org.rahulshetty;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageobjects.loginpage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class standaloneTest {
    public static void main(String[] args) throws IOException {
        String productname = "ADIDAS ORIGINAL";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        loginpage login = new loginpage(driver);
       // login.username.sendKeys("sedhuhaema@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Sedhu@14");
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
      WebElement image =  driver.findElement(By.cssSelector("[src='https://rahulshettyacademy.com/api/ecom/uploads/productImage_1650649434146.jpeg']"));
        File snap = image.getScreenshotAs(OutputType.FILE);
        File na = new File("photo.png");
        FileUtils.copyFile(snap,na);

       List<WebElement> products =  driver.findElements(By.cssSelector(".mb-3"));
//        for (WebElement p:products) {
//
//           String productname =  p.getText();
//           if(productname.contains("ADIDAS ORIGINAL")){
//               p.findElement(By.cssSelector(".btn.w-10.rounded")).click();
//           }
//
//        }
        //Alternate
      WebElement desired_product =  products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
desired_product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='cartSection']")));
List<WebElement> cart_products = driver.findElements(By.xpath("//div/p/following-sibling::h3"));
Boolean added = cart_products.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
        Assert.assertTrue(added);
        driver.findElement(By.cssSelector(".totalRow button")).click();
        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
        List<WebElement> results = driver.findElements(By.cssSelector(".list-group-item"));
        WebElement location = results.stream().filter(result->result.getText().equalsIgnoreCase("Indonesia")).findFirst().orElse(null);
        System.out.println(location.getText());
        Actions action = new Actions(driver);
        action.moveToElement(location).click().build().perform();
        driver.quit();


    }
}

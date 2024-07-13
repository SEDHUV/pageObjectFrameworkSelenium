package abstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.orderpage;

import java.time.Duration;

public class Abstractcomponent {
WebDriver driver;

public Abstractcomponent(WebDriver driver){

    this.driver=driver;
}

@FindBy(css ="[routerlink*='myorders']")
WebElement orderpage;
    public void waitToAppear(By locators){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locators));
    }
    public void waitTdisAppear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void waitToelementToAppear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public orderpage goToOrder(){
        orderpage.click();
    orderpage orderpage1 = new orderpage(driver);
    return orderpage1;
    }
}

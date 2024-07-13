package pageobjects;

import abstractcomponents.Abstractcomponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class confirmationPage extends Abstractcomponent {

    WebDriver driver;

    @FindBy(css = ".hero-primary")
    WebElement successmsg;

    By successMsg = By.cssSelector(".hero-primary");
    public confirmationPage(WebDriver d){
        super(d);
        this.driver=d;
        PageFactory.initElements(driver,this);
    }

public String successfulMsg(){
        waitToAppear(successMsg);
     String Message = successmsg.getText();
     return Message;
}
}

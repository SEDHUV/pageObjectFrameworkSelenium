package pageobjects;

import abstractcomponents.Abstractcomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage extends Abstractcomponent {

    WebDriver driver;
    public loginpage(WebDriver d){
        super(d);
        this.driver=d;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "userEmail")
    WebElement username;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement loginbtn;

    @FindBy(css = ".ng-star-inserted")
    WebElement errormsg;

    public product_Catalog loginapplication(String email, String passwrd){
        username.sendKeys(email);
        password.sendKeys(passwrd);
        loginbtn.click();
        product_Catalog product_catalog = new product_Catalog(driver);
        return product_catalog;
    }

    public void gotoUrl(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String geterrormsg(){
        waitToelementToAppear(errormsg);
       return errormsg.getText();
    }
}





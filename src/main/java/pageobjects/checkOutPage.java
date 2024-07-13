package pageobjects;

import abstractcomponents.Abstractcomponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.text.html.CSS;
import java.util.List;

public class checkOutPage extends Abstractcomponent {
    WebDriver driver;
    public checkOutPage(WebDriver d){
        super(d);
        this.driver=d;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css= "input[placeholder='Select Country']")
    WebElement selectCountry;
    @FindBy(css = ".list-group-item")
    List<WebElement> CountriesDropdown;

    @FindBy(css = ".action__submit")
            WebElement confirmationpage;

    By countries_suggestion = By.cssSelector(".list-group");

    public void addCountryName(String countryname){
        selectCountry.sendKeys(countryname);
        waitToAppear(countries_suggestion);
        WebElement countryToSelect = CountriesDropdown.stream().filter(result->result.getText().equalsIgnoreCase(countryname)).findFirst().orElse(null);
        Actions action = new Actions(driver);
        action.moveToElement(countryToSelect).click().build().perform();
    }

    public confirmationPage submitOrder(){
        waitToelementToAppear(confirmationpage);
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        js.executeScript("window.scrollBy(0,9000)");
        Actions action = new Actions(driver);
        action.moveToElement(confirmationpage).click().build().perform();
       confirmationPage confirmationPage = new confirmationPage(driver);
       return confirmationPage;
    }






}

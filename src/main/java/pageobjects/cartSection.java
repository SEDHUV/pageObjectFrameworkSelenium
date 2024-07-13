package pageobjects;

import abstractcomponents.Abstractcomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class cartSection extends Abstractcomponent {

    WebDriver driver;
    public cartSection(WebDriver d){
        super(d);
        this.driver=d;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath= "//div/p/following-sibling::h3")
    List<WebElement> cartItems;

    @FindBy(css = ".totalRow button")
    WebElement chekoutBtn;


    public Boolean productMatch(String productname){
      Boolean value =   cartItems.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
        return value;
    }
    public checkOutPage checkOutBtnclk(){
        waitToelementToAppear(chekoutBtn);
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        js.executeScript("window.scrollBy(0,9000)");
        Actions action = new Actions(driver);
        action.moveToElement(chekoutBtn).click().build().perform();
        checkOutPage checkOutPage = new checkOutPage(driver);
        return checkOutPage;
    }


}

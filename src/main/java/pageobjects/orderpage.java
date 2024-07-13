package pageobjects;

import abstractcomponents.Abstractcomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class orderpage extends Abstractcomponent {
    WebDriver driver;
    public orderpage(WebDriver d){
        super(d);
        this.driver=d;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css= "tr td:nth-child(3)")
    List<WebElement> orderItems;



    public Boolean orderdisplay(String productname){
        Boolean value =   orderItems.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
        return value;
    }


}

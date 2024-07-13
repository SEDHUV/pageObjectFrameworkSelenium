package pageobjects;

import abstractcomponents.Abstractcomponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class product_Catalog extends Abstractcomponent {

    WebDriver driver;
    public product_Catalog(WebDriver d){
        super(d);
        this.driver=d;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".mb-3")
    List< WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement animation;

    @FindBy(xpath = "//button[contains(@routerlink,'cart')]")
            WebElement goToCartBtn;

    @FindBy (xpath = "//div/p/following-sibling::h3")
            WebElement cartProducts;

    By productsBy = By.cssSelector(".mb-3");
    By AddToCart_Button = By.cssSelector(".card-body button:last-of-type");
    By toastContainer = By.cssSelector("#toast-container");

    By cart_section = By.xpath("//*[@class='cartSection']");
    public List<WebElement> getproducts(){
        waitToAppear(productsBy);
        return products;
    }
    public WebElement getProdyctByName(String productName){
     WebElement desiredProduct =   getproducts().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return desiredProduct;
    }
    public void addToCart(String productname){
        WebElement desiredProduct = getProdyctByName(productname);
        desiredProduct.findElement(AddToCart_Button).click();
        waitToAppear(toastContainer);
        waitTdisAppear(animation);
    }
    public cartSection goToCartSection(){
        goToCartBtn.click();
        waitToAppear(cart_section);
        cartSection cartSection = new cartSection(driver);
        return cartSection;

    }


}



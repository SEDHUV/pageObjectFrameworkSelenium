package StepDefinitions;

import driveractions.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.*;

import java.io.IOException;

public class stepDefinitions extends baseclass {
//   public loginpage loginpage;
    product_Catalog product_catalog;
    confirmationPage confirmationPage;
    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException {
         launchapplication();
    }
    @Given("^Logged with username(.+) and Password(.+)$")
    public void Logged_with_username_and_Password(String username,String password){
         product_catalog =login.loginapplication(username,password);
    }
    @When("^I add the (.+) to cart$")
    public void When_I_add_the_productName_to_cart(String productname){
        product_catalog.addToCart(productname);
    }
    @When("^checkout the (.+) and submit$")
    public void And_checkout_the_productname_and_submit(String productname){
        cartSection cartSection = product_catalog.goToCartSection();
        Boolean added = cartSection.productMatch(productname);
        Assert.assertTrue(added);
        checkOutPage checkOutPage= cartSection.checkOutBtnclk();
        checkOutPage.addCountryName("india");
         confirmationPage =  checkOutPage.submitOrder();
    }
    @Then("{string} displayed in the confirmation page")
            public void Then_Thankyou_for_the_order_displayed_in_the_confirmation_page(String string) {
        System.out.println(confirmationPage.successfulMsg());
        String lastMsg = confirmationPage.successfulMsg();
        Assert.assertTrue(lastMsg.equalsIgnoreCase(string));
        driver.quit();
    }
    @Then("{string} displayed in the login page")
    public void displayed_in_the_login_page(String string) {
        Assert.assertEquals(string,login.geterrormsg());
driver.quit();
    }
}

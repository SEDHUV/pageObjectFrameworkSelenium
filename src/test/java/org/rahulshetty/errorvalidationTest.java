package org.rahulshetty;

import driveractions.Retry;
import driveractions.baseclass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.cartSection;
import pageobjects.product_Catalog;

public class errorvalidationTest extends baseclass {

@Test(groups = {"errorhandling"},retryAnalyzer = Retry.class)
    public void errorvalidation(){
    login.loginapplication("sedhuhaema@gmail.com","568787t786t78");
    Assert.assertEquals("Incorrect email or password.",login.geterrormsg());

}
@Test
    public void productvalidation(){
    String productname = "ADIDAS ORIGINAL";
    String countryName = "India";
    product_Catalog product_catalog= login.loginapplication("anshika@gmail.com","Iamking@000");
    product_catalog.addToCart(productname);
    cartSection cartSection = product_catalog.goToCartSection();
    Boolean added = cartSection.productMatch(productname);
    Assert.assertTrue(added);
}
}

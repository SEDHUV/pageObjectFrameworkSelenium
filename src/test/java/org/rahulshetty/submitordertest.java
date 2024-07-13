package org.rahulshetty;

import driveractions.baseclass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.pqc.crypto.util.PQCOtherInfoGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class submitordertest extends baseclass {
    String productname = "ADIDAS ORIGINAL";
    @Test(dataProvider = "getData")
    public void submitorder(String name,String email,String password) throws IOException {
        String countryName = "India";
       product_Catalog product_catalog= login.loginapplication("sedhuhaema@gmail.com","Sedhu@14");
       product_catalog.addToCart(productname);
        cartSection cartSection = product_catalog.goToCartSection();
        Boolean added = cartSection.productMatch(productname);
        Assert.assertTrue(added);
       checkOutPage checkOutPage= cartSection.checkOutBtnclk();
        checkOutPage.addCountryName(countryName);
       confirmationPage confirmationPage =  checkOutPage.submitOrder();
       System.out.println(confirmationPage.successfulMsg());
       String lastMsg = confirmationPage.successfulMsg();
       Assert.assertTrue(lastMsg.equalsIgnoreCase("Thankyou for the order."));


    }

    @Test(dependsOnMethods = {"submitorder"})
    public void ordercheck() throws InterruptedException {
      product_Catalog product_catalog=  login.loginapplication("sedhuhaema@gmail.com","Sedhu@14");
      orderpage orderpage = product_catalog.goToOrder();
      Thread.sleep(2000);
      Assert.assertTrue(orderpage.orderdisplay(productname));
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][] {{"sedhuhaema@gmail.com","Sedhu@14","ADIDAS ORIGINAL"},{""}};
    }
}

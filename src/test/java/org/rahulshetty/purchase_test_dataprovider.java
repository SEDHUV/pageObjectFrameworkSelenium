package org.rahulshetty;

import driveractions.baseclass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class purchase_test_dataprovider extends baseclass {
    String productname = "ADIDAS ORIGINAL";
    @Test(dataProvider = "getData",groups = {"purchase"})
    public void submitorder(HashMap<String,String> input) throws IOException {

        String countryName = "India";
       product_Catalog product_catalog= login.loginapplication(input.get("email"),input.get("password"));
       product_catalog.addToCart(input.get("productname"));
        cartSection cartSection = product_catalog.goToCartSection();
        Boolean added = cartSection.productMatch(input.get("productname"));
        Assert.assertTrue(added);
       checkOutPage checkOutPage= cartSection.checkOutBtnclk();
        checkOutPage.addCountryName(countryName);
       confirmationPage confirmationPage =  checkOutPage.submitOrder();
       System.out.println(confirmationPage.successfulMsg());
       String lastMsg = confirmationPage.successfulMsg();
       Assert.assertTrue(lastMsg.equalsIgnoreCase("Thankyou for the order."));

    }

    @Test(dependsOnMethods = {"submitorder"},groups = {"purchase"})
    public void ordercheck() throws InterruptedException {
      product_Catalog product_catalog=  login.loginapplication("sedhuhaema@gmail.com","Sedhu@14");
      orderpage orderpage = product_catalog.goToOrder();
      Thread.sleep(2000);
      Assert.assertTrue(orderpage.orderdisplay(productname));



    }
//    @DataProvider
//    public Object[][] getData(){
//        return new Object[][] {{"sedhuhaema@gmail.com","Sedhu@14","ADIDAS ORIGINAL"},
//                {"sedhu89@gmail.com","Sedhu@89","ZARA COAT 3"}};
//    }

    @DataProvider
    public Object[][]getData() throws IOException {
//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","sedhuhaema@gmail.com");
//        map.put("password","Sedhu@14");
//        map.put("productname","ADIDAS ORIGINAL");
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","sedhu89@gmail.com");
//        map1.put("password","Sedhu@89");
//        map1.put("productname","ZARA COAT 3");
        List<HashMap<String,String>> map =getjsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\data.json");
        return new Object[][] {{map.get(0)},{map.get(1)}};
    }
}

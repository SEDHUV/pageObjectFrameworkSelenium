package summa;

import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class class2 extends class1 {
    @Test
    public void test2(){
driver=new EdgeDriver();
        driver.get("https://www.google.co.uk/");
    }

}

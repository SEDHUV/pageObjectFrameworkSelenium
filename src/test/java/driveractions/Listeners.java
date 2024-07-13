package driveractions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.extentReport;

import java.io.IOException;

public class Listeners extends baseclass implements ITestListener {

    ExtentTest test;
    ExtentReports extent = extentReport.extentRt();
    ThreadLocal<ExtentTest> extenttest = new ThreadLocal<>();

    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub
extent.flush();
    }
    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        // TODO Auto-generated method stub
       extenttest.get().fail(arg0.getThrowable());
       try{
          driver =  (WebDriver) arg0.getTestClass().getRealClass().getField("driver").get(arg0.getInstance());}
       catch (Exception e){
           e.printStackTrace();
       }
       String Filepath = null;
        try {
          Filepath= getscreenShot(arg0.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extenttest.get().addScreenCaptureFromPath(Filepath,arg0.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        test = extent.createTest(result.getMethod().getMethodName());
        extenttest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        // TODO Auto-generated method stub

extenttest.get().log(Status.PASS,"test passed");

    }



}

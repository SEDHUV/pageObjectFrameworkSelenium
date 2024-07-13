package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReport {


    public static ExtentReports extentRt(){
        String path = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("webAutomation");
        reporter.config().setDocumentTitle("TestResults");
       ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Tester","Sedhu");
        return reports;


    }
}

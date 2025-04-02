 package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter; // UI of the report
    public ExtentReports extent; // Populate common info on the report
    public ExtentTest test; // Create test case entries in the report and update the status of the test methods

    String repName;
    @Override
    public void onStart(ITestContext context) {
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    	Date dt = new Date(0);
    	String currentdatetimestamp = df.format(dt);
    	
    	repName ="Test-Report-"+currentdatetimestamp+".html";
    	sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
    	
        sparkReporter.config().setDocumentTitle("Automation Report"); // Title of report
        sparkReporter.config().setReportName("Functional Testing"); // Name of report
        sparkReporter.config().setTheme(Theme.STANDARD);

        
        extent = new ExtentReports();
        extent.attachReporter (sparkReporter);
        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environemnt", "QA");

        String os = context.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);
        String browser = context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);
        List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()) {
        extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }        
    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new entry in the report
        test.assignCategory(result.getMethod().getGroups()); // to display the group report
        test.log(Status.PASS, "Test Case Passed: " + result.getName()); // Update status
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new entry in the report
        test.assignCategory(result.getMethod().getGroups()); 
        
        test.log(Status.FAIL, "Test Case Failed: " + result.getName()); // Update status
        test.log(Status.INFO, result.getThrowable().getMessage());
        
        try {
        	String imgPath = new BaseClass().captureScreen(result.getName());
        	test.addScreenCaptureFromPath(imgPath);
        }catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new entry in the report
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Case Skipped: " + result.getName()); // Update status
        test.log(Status.INFO,result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Finalize the report
        
        String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
        File extentReport = new File(pathOfExtentReport);
        try {
        	Desktop.getDesktop().browse(extentReport.toURI());
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
     /*   
        try {

        	URL url = new
        	URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
        	// Create the email message
        	ImageHtmlEmail email = new ImageHtmlEmail();
        	email.setDataSourceResolver(new DataSourceUrlResolver(url));
        	email.setHostName("smtp.googlemail.com");
        	email.setSmtpPort(465);
        	email.setAuthenticator (new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
        	email.setSSLOnConnect(true);
        	email.setFrom("pavanoltraining@gmail.com"); //Sender
        	email.setSubject("Test Results");
        	email.setMsg("Please find Attached Report....");
        	email.addTo("pavankumar.busyqa@gmail.com"); //Receiver
        	email.attach(url, "extent report", "please check report...");
        	email.send(); // send the email
        	}
        	catch(Exception e)
        	{
        	e.printStackTrace();
        	}
        */
    }
}


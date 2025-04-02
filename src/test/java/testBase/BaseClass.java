/*
package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups = {"Sanity","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities dc = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("window")) {
				dc.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac")) {
				dc.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("no matching os");
				return;
			}
			
			//browser
			switch (br.toLowerCase()) {
			case "chrome": dc.setBrowserName("browser");break;
			case "edge": dc.setBrowserName("MicrosoftEdge");break;
			default : System.out.println("no matching browser");return;
			}
			
			driver = new RemoteWebDriver(new URL(""),dc);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch (br.toLowerCase()) {
			case "chrome": driver = new ChromeDriver();break;
			case "firefox": driver = new FirefoxDriver();break;
			case "edge": driver = new EdgeDriver();break;
			default: System.out.println("Invalide Browser name..."); return;
			}
			
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			FileReader file = new FileReader("./src//test//resources//config.properties");
			p = new Properties();
			p.load(file);
			driver.get(p.getProperty("AppUrl"));		// reading url from properties files..
			driver.manage().window().maximize();
		}
	}

	@AfterClass(groups = {"Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	public String randomNumber() {
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	public String randomAlphaNumberic() {
		String generatednumber = RandomStringUtils.randomNumeric(3);
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) {
		
    	String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));
    	
    	TakesScreenshot takescrScreenshot = (TakesScreenshot)driver;
    	File srcfile = takescrScreenshot.getScreenshotAs(OutputType.FILE);
    	
    	String targetfilePath = System.getProperty("user.dir")+"\\screenshots"+tname+"_"+timestamp+".png";
    	File targetFile = new File(targetfilePath);
    	srcfile.renameTo(targetFile);
    	
    	return targetfilePath;
    	
	}
	
}
*/

package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException {
        logger = LogManager.getLogger(this.getClass());

        // Load properties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        String executionEnv = p.getProperty("execution_env");
        if (executionEnv == null) {
            throw new IllegalStateException("execution_env property is missing from config.properties");
        }

        if (executionEnv.equalsIgnoreCase("remote")) {
            DesiredCapabilities dc = new DesiredCapabilities();

            // Set OS
            if (os.equalsIgnoreCase("windows")) {
                dc.setPlatform(Platform.WIN10);
            } else if (os.equalsIgnoreCase("mac")) {
                dc.setPlatform(Platform.MAC);
            } else {
                System.out.println("No matching OS found.");
                return;
            }

            // Set Browser
            switch (browser.toLowerCase()) {
                case "chrome":
                    dc.setBrowserName("chrome");
                    break;
                case "edge":
                    dc.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    System.out.println("No matching browser found.");
                    return;
            }

            driver = new RemoteWebDriver(new URL(p.getProperty("remote_url")), dc);
        }

        if (executionEnv.equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    System.out.println("Invalid Browser name.");
                    return;
            }

            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get(p.getProperty("AppUrl"));
            driver.manage().window().maximize();
        }
    }


    @AfterClass(groups = {"Sanity", "Regression"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(3);
    }

    public String captureScreen(String tname) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(0));

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        // Ensure directory exists
        File dir = new File(System.getProperty("user.dir") + "\\screenshots");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
        File targetFile = new File(targetFilePath);

        try {
            boolean isRenamed = srcFile.renameTo(targetFile);
            if (!isRenamed) {
                throw new IOException("Failed to rename screenshot file.");
            }
        } catch (IOException e) {
            logger.error("Error while capturing screenshot: " + e.getMessage());
        }

        return targetFilePath;
    }
}


package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_AccountLoginTest extends BaseClass {

	@Test(groups = {"Sanity","Regression"})
	public void verify_account_Login() {

		logger.info("************* Starting TC002_AccountLoginTest***********");
		
		try {
		HomePage hp = new HomePage(driver);
		logger.info("Clicked on MyAccount");
		hp.clickMyAccoount();
		logger.info("Clicked on Login");
		hp.clickLogin();
		 
		LoginPage lp = new LoginPage(driver);
		logger.info("Entered Email");
		lp.setEmail(p.getProperty("email"));
		logger.info("Entered Password");
		lp.setPassword(p.getProperty("pwd"));
		logger.info("Clicked on Login..");
		lp.clickLogin();
		logger.info("Loggin Passed");
		
		MyAccountPage Accpge = new MyAccountPage(driver);
		boolean targetpage = Accpge.MyAccounPageExists();
		logger.info("MyAccount Page Displayed");
		Assert.assertTrue(targetpage);
		
		}catch (Exception e) {
			Assert.fail();
		}
		logger.info("***************** Finished TC001_AccountRegistrationTest***********");
	}
}

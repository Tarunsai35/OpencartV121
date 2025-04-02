package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Sanity","Regression"})
	public void verify_account_registraton() {

		logger.info("***************** Starting TC001_AccountRegistrationTest***********");
		
		try {
		HomePage hp = new HomePage(driver);
		logger.info("Clicked on MyAccount");
		hp.clickMyAccoount();
		logger.info("Clicked on Register");
		hp.clickRegister();
		logger.info("open Register page");
		AccountRegisterPage regpage = new AccountRegisterPage(driver);
		logger.info("Enter FirstName");
		regpage.setFirstName(randomString().toUpperCase());
		logger.info("Enter LastName");
		regpage.setLastName(randomString().toUpperCase());
		logger.info("Enter Email");
		regpage.setEmail(randomString()+"@gmail.com"); //randomly generated the email
		regpage.setTelePhone(randomNumber());
		
		String password = randomAlphaNumeric();
		logger.info("Enter Password");
		regpage.setPassword(password);
		logger.info("Enter ConfirmPassword");
		regpage.setConfirmPassword(password);
		logger.info("Click on PrivacyPolicy");
		regpage.setPrivacyPolicy();
		logger.info("Click on Continue");
		regpage.clickContinue();
		String confmsg = regpage.getCongirmationMsg();
		logger.info("Confmsg is displayed");
		
		
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Test Failed..");
			logger.debug("Debug log...");
			Assert.assertTrue(false);
		}
		}catch (Exception e) {
			Assert.fail();
		}
		logger.info("***************** Finished TC001_AccountRegistrationTest***********");
	}
}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

//	@Test(dataProvider = "LoginData", dataProviderClass = DataProvider.class)
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)

	public void verify_LoginDDT(String email, String pwd, String exp) {
		logger.info("************* Starting TC003_LoginDDTt***********");

		try {
			HomePage hp = new HomePage(driver);
			logger.info("Clicked on MyAccount");
			hp.clickMyAccoount();
			logger.info("Clicked on Login");
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			logger.info("Entered Email");
			lp.setEmail(email);
			logger.info("Entered Password");
			lp.setPassword(pwd);
			logger.info("Clicked on Login..");
			lp.clickLogin();
			logger.info("Loggin Passed");

			MyAccountPage Accpge = new MyAccountPage(driver);
			boolean targetpage = Accpge.MyAccounPageExists();
			logger.info("MyAccount Page Displayed");
			Assert.assertTrue(targetpage);

			/*
			 * Data is valid = login success - test pass - logout 
			 * 									login failed - test fail
			 * Data is Invalid = login succes -test fail- logout 
			 * 									login fail - test pass
			 */

			if (exp.equalsIgnoreCase("valid")) {
				if (targetpage == true) {
					Accpge.ClickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetpage == true) {
					Accpge.ClickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("***************** Finished TC003_LoginDDT***********");
	}
}


/*
package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProvider.class)
    public void verify_LoginDDT(String email, String pwd, String exp) {
        logger.info("************* Starting TC003_LoginDDT ***********");

        try {
            HomePage hp = new HomePage(driver);
            logger.info("Navigating to MyAccount...");
            hp.clickMyAccoount(); // Corrected method name
            logger.info("Navigating to Login...");
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            logger.info("Entering Email: " + email);
            lp.setEmail(email);
            logger.info("Entering Password...");
            lp.setPassword(pwd);
            logger.info("Clicking Login...");
            lp.clickLogin();

            MyAccountPage Accpge = new MyAccountPage(driver);
            boolean targetpage = Accpge.MyAccounPageExists();
            logger.info("Login verification: " + (targetpage ? "Success" : "Failed"));

            // Validation based on expected result
            if (exp.equalsIgnoreCase("valid")) {
                Assert.assertTrue(targetpage, "Login failed for valid credentials.");
                logger.info("Valid Login successful.");
                Accpge.ClickLogout();
            } else {
                Assert.assertFalse(targetpage, "Login should have failed but succeeded.");
                logger.info("Invalid Login failed as expected.");
            }

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            Assert.fail(e.getMessage());
        }

        logger.info("***************** Finished TC003_LoginDDT ***********");
    }
}
*/
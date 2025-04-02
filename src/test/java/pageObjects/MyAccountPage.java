package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement msgHeader;
	
	public boolean MyAccounPageExists() {
		try {
		boolean status = msgHeader.isDisplayed();
		return status;
		}catch (Exception e) {
			return false;
		}
	}
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout;
	
	public void ClickLogout() {
		logout.click();
	}
}

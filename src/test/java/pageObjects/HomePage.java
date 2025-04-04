package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//ul[@class='list-inline']//li[@class='dropdown']")
	WebElement lnkMyAccount;
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	public void clickMyAccoount() {
		lnkMyAccount.click();
	}
	public void clickRegister() {
		lnkRegister.click();
	}
	public void clickLogin() {
		lnkLogin.click();
	}
	
}





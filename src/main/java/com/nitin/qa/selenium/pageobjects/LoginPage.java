package main.java.com.nitin.qa.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import main.java.com.nitin.qa.selenium.framework.Config;
import main.java.com.nitin.qa.selenium.framework.WebDriverUtilities;

public class LoginPage extends WebDriverUtilities {

	WebDriver driver;

	// Home Page
	
	@FindBy(xpath="//input[@id='identifierId']")
	public static WebElement homepage_loginID;
	
	@FindBy(xpath="//div[@id='identifierNext']")
	public static WebElement homepage_loginpage_nextBtn;

	@FindBy(xpath="//input[@type='password']")
	public static WebElement homepage_password;
	
	@FindBy(xpath="//div[@id='passwordNext']")
	public static WebElement homepage_passwordPage_nextBtn;
	

	// --------------------------- CONSTRUCTORS -------------------------

	public LoginPage(WebDriver driver){
		this.driver = driver;
	}

	/**
	 * Login to Gmail
	 */
	public void loginToGmail(){
		
		log("Test case to login to gmail");
		homepage_loginID.sendKeys(Config.GMAIL_ID);
		homepage_loginpage_nextBtn.click();
		waitForElement(driver, homepage_password);
		homepage_password.sendKeys(Config.PASSWORD);
		homepage_passwordPage_nextBtn.click();
		log("Logging in to gmail");
		
	}
}

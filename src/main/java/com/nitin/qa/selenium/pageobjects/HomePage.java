package main.java.com.nitin.qa.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import main.java.com.nitin.qa.selenium.framework.Config;
import main.java.com.nitin.qa.selenium.framework.WebDriverUtilities;

public class HomePage extends WebDriverUtilities {

	WebDriver driver;

	@FindBy(xpath="//a[@class='gb_x gb_Da gb_f']")
	public static WebElement homepage_UserNameIcon;



	// --------------------------- CONSTRUCTORS -------------------------

	public HomePage(WebDriver driver){
		this.driver = driver;
	}

	public void verifyHomePage() {
		waitForElement(driver, homepage_UserNameIcon);
		Assert.assertTrue(homepage_UserNameIcon.getAttribute("aria-label").contains(Config.GMAIL_ID));
		log("Logged in successfully");
	}

}

package main.java.com.nitin.qa.selenium.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebDriverUtilities {

	/**
	 * Wait for a element to be displayed.
	 * @param by : Element
	 * @param maxWaitTime : Maximum waiting time in seconds.
	 * @throws Exception 
	 */
	public static void waitForElement(WebDriver driver, WebElement element)  {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Drag and drop utility.
	 * @param driver
	 * @param sourceLocator
	 * @param targetLocator
	 * @throws Exception
	 */
	public static void dragAndDrop(WebDriver driver , WebElement sourceLocator , WebElement targetLocator) throws Exception{

		Actions action = new Actions(driver);
		action.dragAndDrop(sourceLocator, targetLocator).build().perform();
		Thread.sleep(4000);
	}

	/**
	 * 
	 * @param driver
	 * @param sourceLocator
	 * @param xoffset
	 * @param yoffset
	 * @throws Exception
	 */
	public static void dragAndDropBy(WebDriver driver , WebElement sourceLocator , int xoffset , int yoffset) throws Exception{

		Actions action = new Actions(driver);
		action.dragAndDropBy(sourceLocator, xoffset, yoffset).build().perform();
		Thread.sleep(4000);
	}

	/**
	 * Perform mouseover on the specified WebElement.
	 * @param webElement
	 */
	public void mouseoverAction(WebElement webElement){
		Actions builder = new Actions(AutomationEngine.getDriver());
		builder.moveToElement(webElement).perform();
	}



	protected void log(String message) {
		Reporter.log(message, true);
	}

	protected void log(Object message) {
		if(message != null){
			Reporter.log(message.toString(), true);
		}else{
			Reporter.log("log method called with null object", true);
		}
	}
}

package main.java.com.nitin.qa.selenium.framework;

import java.io.File;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class AutomationEngine {

	private static String OS = System.getProperty("os.name").toLowerCase();

	public static String BROWSER_FIREFOX = "Firefox";
	public static String BROWSER_IE      = "IE";
	public static String BROWSER_CHROME  = "Chrome";
	public static String BROWSER_HTML    = "Html";

	protected static WebDriver driver;
	static String path;

	/**
	 * Invoking Webdriver object.
	 * @throws Exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {

		if(driver == null){
			createNewDriverInstance();
		}else {
			AutomationEngine.driver=getDriver();
		}
		driver.get(Config.URL);
	}

	public static WebDriver getDriver(){
		return AutomationEngine.driver;
	}

	/**
	 * Create a new driver instance.
	 */
	public void createNewDriverInstance()
	{
		if(Config.BROWSER.equals(BROWSER_FIREFOX)){

			if (OS.contains("linux")) {
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + File.separator+"src"+ File.separator+"test"+ File.separator+"resources"+ File.separator+"geckodriver");
			}
			else if (OS.contains("windows")){
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + File.separator+"src"+ File.separator+"test"+ File.separator+"resources"+ File.separator+"geckodriver.exe");
			}
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("pdfjs.disabled", true);
			profile.setPreference("browser.download.dir", System.getProperty("user.dir") + File.separator
					+ "src"+File.separator+"test"+File.separator+"resources"+File.separator+"test-data"+File.separator);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/csv, application/pdf, application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel, application/vnd.ms- excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel,text/x-c,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/zip,application/octet-stream");
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			profile.setPreference("browser.download.manager.focusWhenStarting", false);
			profile.setPreference("browser.download.manager.useWindow", false);
			profile.setPreference("browser.download.manager.showAlertOnComplete", false);
			profile.setPreference("browser.download.manager.closeWhenDone", false);
			profile.setPreference("browser.cache.disk.enable", false);
			profile.setPreference("browser.cache.memory.enable", false);
			profile.setPreference("browser.cache.offline.enable", false);
			profile.setPreference("network.http.use-cache", false);
			profile.setPreference("pdfjs.disabled", true);
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("marionette", true);
			firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new FirefoxDriver(firefoxOptions);
		}
		else if(Config.BROWSER.equals(BROWSER_CHROME)){
			if (OS.contains("linux")) {
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + File.separator+"src"+ File.separator+"test"+ File.separator+"resources"+ File.separator+"chromedriver");
			}
			else if (OS.contains("windows")){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + File.separator+"src"+ File.separator+"test"+ File.separator+"resources"+ File.separator+"chromedriver.exe");
			}
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir") + File.separator
					+ "src"+File.separator+"test"+File.separator+"resources"+File.separator+"test-data"+File.separator);
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			options.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options);
		}
		driver.manage().window().maximize();
	}
	

	/**
	 * Close and quit driver.
	 * @param result
	 * @throws Exception
	 */
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			try{
				tearDown();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@AfterSuite
	public void tearDown(){
		Reporter.log("closing webdriver");
		driver.close();
		if(driver != null){
			driver = null;
		}
		Reporter.log("webdriver closed.");
		Reporter.log("Reports can be");
	}
}

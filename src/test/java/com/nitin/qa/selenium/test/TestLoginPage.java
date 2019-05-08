package com.nitin.qa.selenium.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import main.java.com.nitin.qa.selenium.framework.AutomationEngine;
import main.java.com.nitin.qa.selenium.pageobjects.HomePage;
import main.java.com.nitin.qa.selenium.pageobjects.LoginPage;


/**
 *  Book Flight ticket with Cleartrip.
 */
public class TestLoginPage extends AutomationEngine
{
	
	@Test
	public void testLogin(){
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.loginToGmail();
		
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.verifyHomePage();
		
		
	}
}

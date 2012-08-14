package com.test;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddPage {
	CommonFunctions cf;
	@Test
	public void Test_CreateNewArticle() throws Exception {
		 cf = new CommonFunctions();
		 cf.LoginEcms("root", "gtn");
		 Thread.sleep(1000);
		 cf.EnterSiteForm();
		 Thread.sleep(1000);
		 cf.CreateNewArticle("Test", "Test", "Test", "Test");
	}
	@Test
	public void Test_CreateNewAnnoucement() throws Exception {
		 cf = new CommonFunctions();
		 cf.LoginEcms("root", "gtn");
		 Thread.sleep(1000);
		 cf.EnterSiteForm();
		 Thread.sleep(1000);
		 cf.CreateNewAnnoucement("Testabc", "Testannoucement");
	}
	@Test
	public void Test_CreateNewFreeLayoutWebContent() throws Exception{
		 cf = new CommonFunctions();
		 cf.LoginEcms("root", "gtn");
		 Thread.sleep(1000);
		 cf.EnterSiteForm();
		 Thread.sleep(1000);
		 cf.CreateNewFreeLayoutWebContent("Test", "Test", "Test", "/home/lientm/test.jpg", "Test", "Test", "Test");		 
	}
	@Test 
	void Test_CreateNewFile() throws Exception {
		 cf = new CommonFunctions();
		 cf.LoginEcms("root", "gtn");
		 Thread.sleep(1000);
		 cf.EnterSiteForm();
		 Thread.sleep(1000);
		 cf.CreateNewFile("Test", "Test", "Test");
	}
	
	@Test
	void Test_CreateNewSampleNode() throws Exception {
		 cf = new CommonFunctions();
		 cf.LoginEcms("root", "gtn");
		 Thread.sleep(1000);
		 cf.EnterSiteForm();
		 Thread.sleep(1000);
		 cf.CreateNewSampleNode("Test", "Test");
	}
}

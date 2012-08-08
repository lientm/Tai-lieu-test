package com.test;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Test_portal {
	// public Variable
	WebDriver driver;

	@Test
	public void PageManagement() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/portal/classic");
		WebElement register = driver.findElement(By.linkText("Sign in"));
		register.click();
		WebElement user = null;
		try {
			while (user == null) {
				Thread.sleep(500);
				user = driver.findElement(By.name("username"));
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		user.sendKeys("root");
		WebElement pass = driver.findElement(By.name("password"));
		pass.sendKeys("gtn");
		driver.findElement(By.name("signIn")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Enter Page management form
		Actions action = new Actions(driver);
		WebElement group = driver.findElement(By.linkText("Group"));
		action.moveToElement(group).build().perform();
		WebElement adminis = driver.findElement(By.linkText("Administration"));
		action.moveToElement(adminis).build().perform();
		WebElement page = null;
		try {
			while (page == null) {
				Thread.sleep(1000);
				page = driver.findElement(By.linkText("Page Management"));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.click();

		// check value in textbox
		WebElement title = driver.findElement(By.id("pageTitle"));
		assertTrue(title.getText().equalsIgnoreCase(""));
		WebElement pagename = driver.findElement(By.id("siteName"));
		assertTrue(pagename.getText().equalsIgnoreCase(""));

		// add new page
		driver.findElement(By.linkText("Add New Page")).click();
		WebElement name = null;
		try {
			while (name == null) {
				Thread.sleep(1000);
				name = driver.findElement(By.id("name"));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		name.sendKeys("Page1");
		driver.findElement(By.id("title")).sendKeys("Page1");
		driver.findElement(By.linkText("Save")).click();

		// search created page
		// find according to title
		WebElement title1 = null;
		try {
			while (title1 == null) {
				Thread.sleep(1000);
				title1 = driver.findElement(By.id("pageTitle"));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		title1.sendKeys("Page1");
		driver.findElement(By.cssSelector(".SearchIcon")).click();
		
		//Delete created page
		WebElement delete = null;
		try {
			while (delete == null){
			Thread.sleep(1000);
			delete = driver.findElement(By.cssSelector(".DeleteIcon"));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		delete.click();
		Alert alert = driver.switchTo().alert();
		alert.getText();  
		alert.accept();

		// Logout
		Actions action_logout = new Actions(driver);
		WebElement UI = driver.findElement(By
				.xpath(".//*[@id='PortalNavigationTopContainer']/li"));
		action_logout.moveToElement(UI).build().perform();
		driver.findElement(By.linkText("Sign out")).click();

	}
}

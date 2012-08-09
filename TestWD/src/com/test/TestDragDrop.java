package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TestDragDrop {
    WebDriver driver;
  @Test
  public void login() {
	  //Login
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
  }
  @Test
  public void enterEditpage(){
		Actions action = new Actions(driver);
		WebElement site = driver.findElement(By.linkText("Site Editor"));
		action.moveToElement(site).build().perform();
		WebElement page = null;
		try {
			while (page == null) {
				Thread.sleep(1000);
				page = driver.findElement(By.linkText("Edit Page"));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.click();
  }
  @Test
  public void DragDrop(){
	  try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  Actions builder = new Actions(driver);
	  WebElement sr = driver.findElement(By.xpath("//*[@id='Administration/ApplicationRegistryPortlet']"));
//	  WebElement des = driver.findElement(By.xpath("//*[@class='UIPage']"));
//	  Action dragAndDrop = builder.clickAndHold(portal).moveToElement(destiny).release(destiny).build();	  
//	   dragAndDrop.perform();
	  builder.dragAndDropBy(sr,2,2).perform();
	  try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  driver.findElement(By.xpath("//*[@id='UIPageEditor']/div[1]/a[2]")).click();
	  WebElement checkclick = null;
	  try {
		while (checkclick == null){
		Thread.sleep(3000);
		checkclick = driver.findElement(By.linkText("Administrator"));
		}
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  builder.contextClick(checkclick).perform();
	  try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 checkclick.sendKeys(Keys.ARROW_DOWN);
	 checkclick.sendKeys(Keys.RETURN);
  }
  

}

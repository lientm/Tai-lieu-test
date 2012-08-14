package com.test;

import static org.testng.AssertJUnit.assertEquals;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CommonFunctions {
	private WebDriver driver;
	public CommonFunctions(){
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/portal/acme");
	}
	//login ECMS
	public void LoginEcms(String username, String password) {

		WebElement register = driver.findElement(By
				.xpath("//*[@id='AcmeWebSiteLogInLogOut']"));
		register.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		WebElement user = null;
		try {
			while (user == null) {
				Thread.sleep(1000);
				user = driver.findElement(By.name("username"));
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		user.sendKeys(username);
		WebElement pass = driver.findElement(By.name("password"));
		pass.sendKeys(password);
		driver.findElement(By.xpath("//*[@id='UIPortalLoginFormAction']"))
				.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Enter sites management Form -> add new content
	public void EnterSiteForm() throws Exception{
		Actions action = new Actions(driver);
		WebElement mysite = driver.findElement(By.xpath("//img[@alt='Setup']"));
		action.moveToElement(mysite).build().perform();
		WebElement content = driver.findElement(By.linkText("Content"));
		action.moveToElement(content).build().perform();
		WebElement site = driver.findElement(By.linkText("Sites Explorer"));
		site.click();
		Thread.sleep(1000);
		// click add new content
		GotoNode("documents ");
		WebElement addContent = null;
		try {
			Thread.sleep(3000);
			while (addContent == null) {
				Thread.sleep(3000);
				addContent = driver.findElement(By.linkText("New Content"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		addContent.click();
	}
	
	//function go to Node
	public void GotoNode(String node) throws Exception{
		WebElement acme = null;
		try {
			while (acme==null){
			Thread.sleep(1000);
			acme = driver.findElement(By.xpath("//a[@title='acme ']"));
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		acme.click();
		Thread.sleep(1000);
		WebElement Node = null;
		try {
			Thread.sleep(1000);
			while (Node ==null){
			Thread.sleep(1000);
			Node = driver.findElement(By.xpath("//a[@title='"+node+"']"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Node.click();
	}
	//function add data to frame
	public void InputDataToFrame (String frameId, String bodyId, String data){
		WebElement summary = driver.findElement(By.xpath(frameId));
		driver.switchTo().frame(summary);
		driver.findElement(By.xpath(bodyId));
		WebElement inputsummary = driver.switchTo().activeElement();
		inputsummary.sendKeys(data);
	}
	
	//function swith to parent windows
	public void SwitchtoParentWindow () throws Exception{
		try
	    {
	    Set<String> availableWindows = driver.getWindowHandles();
	    String WindowIDparent= null, WindowIDModal = null;
	    int counter = 1;
	    for (String windowId : availableWindows) {
	    if (counter == 1){
	    WindowIDparent = windowId;
	    }
	    counter++;
	    }
	    driver.switchTo().window(WindowIDparent);
	    Thread.sleep(1000);
	    }
	    catch (WebDriverException e)
	    {
	    e.printStackTrace();
	    }
	}
	
	// add new article
	public void CreateNewArticle(String title, String name, String sum, String cont) throws Exception {
		WebElement addArticle = null;
		try {
			while (addArticle == null) {
				Thread.sleep(1000);
				addArticle = driver.findElement(By
						.xpath("//div[@title='Article']"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		addArticle.click();
		Thread.sleep(1000);

		// Input information
		WebElement Title = null;
		try {
			while (Title == null) {
				Thread.sleep(1000);
				Title = driver.findElement(By.id("title"));
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Title.sendKeys(title);
		driver.findElement(By.id("name")).sendKeys(name);
		//input data to summary
		Thread.sleep(1000);
		InputDataToFrame("//td[@id='cke_contents_summary']/iframe","//body[@class='cke_show_borders']",sum);
	    Thread.sleep(1000);
	    
	    //switch back windownparent
	    SwitchtoParentWindow();
	    
	    //input data to content
	    Thread.sleep(1000);
	    InputDataToFrame("//td[@id='cke_contents_content']/iframe","//body[@class='cke_show_borders']",cont);
	    Thread.sleep(1000);
	    
	    //switch back windownparent
	    SwitchtoParentWindow();

		driver.findElement(By.linkText("Save & Close")).click();
	}
	
	//add new annoucement
	public void CreateNewAnnoucement (String name, String sum) throws Exception{
		WebElement addAnn = null;
		try {
			while (addAnn == null) {
				Thread.sleep(3000);
				addAnn = driver.findElement(By.linkText("Announcement"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		addAnn.click();
		Thread.sleep(1000);
		WebElement namepage = null;
		try{
			while (namepage==null){
			Thread.sleep(1000);
			namepage = driver.findElement(By.id("name"));
			} 
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		namepage.sendKeys(name);
		Thread.sleep(100);
		InputDataToFrame("//td[@id='cke_contents_exo:summary']/iframe","//body[@class='cke_show_borders']",sum);
		Thread.sleep(3000);
	    SwitchtoParentWindow();
		driver.findElement(By.linkText("Save & Close")).click();
	}
	
	//add new Free layout webcontent
	public void CreateNewFreeLayoutWebContent(String title, String name, String cont, String img, String sum, String css, String js) throws Exception {
		WebElement addAnn = null;
		try {
			Thread.sleep(1000);
			while (addAnn == null) {
				Thread.sleep(1000);
				addAnn = driver.findElement(By.linkText("Free layout webcontent"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		addAnn.click();
		Thread.sleep(1000);	
		//input data in tab Main content
		WebElement tit =null;
		try{
			Thread.sleep(1000);
			while (tit ==null){
			Thread.sleep(1000);
			tit = driver.findElement(By.id("title"));
			}
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		tit.sendKeys(title);
		driver.findElement(By.id("name")).sendKeys(name);
		InputDataToFrame("//td[contains(@id,'cke_contents_htmlData')]/iframe","//body[@class='cke_show_borders']",cont);
		Thread.sleep(1000);
	    SwitchtoParentWindow();
		Thread.sleep(2000);
		//input data in tab Illustration
		driver.findElement(By.xpath("//div[contains(text(),'Illustration')]")).click();
		Thread.sleep(1000);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//*[contains(@id,'uploadFrame')]")));
		WebElement file = null;
		try{
			Thread.sleep(1000);
			while (file ==null){
				Thread.sleep(1000);
				file = driver.findElement(By.id("file"));
			}
			
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		file.sendKeys(img);
	    SwitchtoParentWindow();
	    Thread.sleep(1000);
		InputDataToFrame("//td[@id='cke_contents_exo:summary']/iframe","//body[@class='cke_show_borders']",sum);
		Thread.sleep(1000);
	    SwitchtoParentWindow();
		Thread.sleep(1000);
		
		//input data in tab Advanced
		driver.findElement(By.xpath("//div[contains(text(),'Advanced')]")).click();
		WebElement cssdata = null;
		try{
			Thread.sleep(1000);
			while (cssdata ==null){
				Thread.sleep(1000);
				cssdata = driver.findElement(By.xpath("//textarea[contains(@id,'ContentCSS')]"));
			}
			
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		cssdata.sendKeys(css);
		driver.findElement(By.xpath("//textarea[contains(@id,'ContentJS')]")).sendKeys(js);
		Thread.sleep(1000);
		driver.findElement(By.linkText("Save & Close")).click();			
	}
	
	//add new file
	public void CreateNewFile(String name, String cont, String title)throws Exception{
		WebElement addAnn = null;
		try {
			while (addAnn == null) {
				Thread.sleep(1000);
				addAnn = driver.findElement(By.linkText("File"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		addAnn.click();
		Thread.sleep(1000);	
		WebElement namefile =null;
		try{
			Thread.sleep(1000);
			while (namefile ==null){
			Thread.sleep(1000);
			namefile = driver.findElement(By.id("name"));
			}
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		namefile.sendKeys(name);
		InputDataToFrame("//td[@id='cke_contents_contentHtml']/iframe","//body[@class='cke_show_borders']",cont);
		Thread.sleep(1000);
	    SwitchtoParentWindow();
		Thread.sleep(1000);
		driver.findElement(By.id("title0")).sendKeys(title);
		driver.findElement(By.linkText("Save & Close")).click();
	}
	
	//add new Sample Node
	public void CreateNewSampleNode(String title, String name) throws Exception{
		WebElement sample = null;
		try {
			Thread.sleep(1000);
			while (sample == null) {
				Thread.sleep(1000);
				sample = driver.findElement(By.linkText("Sample node"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sample.click();
		Thread.sleep(1000);
		driver.findElement(By.id("title")).sendKeys(title);
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.xpath("//img[@title = 'Add Item']")).click();
		Select select = null;
		Thread.sleep(1000);
		try {
			Thread.sleep(1000);
			while (select == null) {
			Thread.sleep(1000);
			select =  new Select(driver.findElement(By.id("taxonomyTree")));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		select.selectByVisibleText("acme");
		Thread.sleep(1000);
		
		WebElement choose = null;
		try {
			Thread.sleep(1000);
			while (select == null) {
			Thread.sleep(1000);
			choose = driver.findElement(By.xpath("//div[@class='Select16x16Icon']"));
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		choose.click();
		Thread.sleep(2000);
		driver.findElement(By.id("description")).sendKeys("Test");
		driver.switchTo().frame(driver.findElement(By.xpath("//ifframe[contains(@id,'uploadFrame')]")));
		driver.findElement(By.id("file")).sendKeys("/home/lientm/test.jpg");
		Thread.sleep(5000);
		SwitchtoParentWindow();
		Thread.sleep(1000);
		driver.findElement(By.id("summary")).sendKeys("Test");
		driver.findElement(By.id("content")).sendKeys("Test");
		driver.findElement(By.linkText("Save & Close")).click();
	}
	
}

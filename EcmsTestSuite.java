package org.exoplatform.selenium.platform.ecms;

import java.util.Set;
import org.exoplatform.selenium.platform.PlatformTestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class EcmsTestSuite extends PlatformTestSuite {
	//login ECMS
	public void LoginEcms(String username, String password) {
		SelectElementByXpath("//*[@id='AcmeWebSiteLogInLogOut']").click();
		pause(100);
		SelectElementByName("username").sendKeys(username);
		SelectElementByName("password").sendKeys(password);
		SelectElementByXpath("//*[@id='UIPortalLoginFormAction']").click();
	}
	
	//select a Element by xpath
	public WebElement SelectElementByXpath(String xpath){
		WebElement element=null;
		pause(100);
		try {
			Thread.sleep(100);
			while(element ==null){
			Thread.sleep(500);
			element = webDriver.findElement(By.xpath(xpath));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return element;
	}
	
	//select a Element by linkText
	public WebElement SelectElementByLinktext(String link){
		WebElement element=null;
		pause(100);
		try {
			Thread.sleep(100);
			while(element ==null){
			Thread.sleep(500);
			element = webDriver.findElement(By.linkText(link));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return element;
	}
	//select a Element by name
	public WebElement SelectElementByName(String name){
		WebElement element=null;
		pause(100);
		try {
			Thread.sleep(100);
			while(element ==null){
			Thread.sleep(500);
			element = webDriver.findElement(By.name(name));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return element;
	}
	
	//select a Element by id
	public WebElement SelectElementById(String id){
		WebElement element=null;
		pause(100);
		try {
			Thread.sleep(100);
			while(element ==null){
			Thread.sleep(500);
			element = webDriver.findElement(By.id(id));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return element;
	}
	
	//Select option from combobox
	public void SelectOption(String selectId,String value){
		Select typefolder = null;
		pause(100);
		try {
			Thread.sleep(100);
			while (typefolder ==null){
				Thread.sleep(500);
				typefolder = new Select(SelectElementById(selectId));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		typefolder.selectByValue(value);
	}
	
	//Enter sites management Form 
	public void EnterSiteForm() throws Exception{
		Actions action = new Actions(webDriver);
		action.moveToElement(SelectElementByXpath("//img[@alt='Setup']")).build().perform();
		action.moveToElement(SelectElementByLinktext("Content")).build().perform();
		SelectElementByLinktext("Sites Explorer").click();
	}
	//Enter create new page form
	public void EnterNewPageForm(){
		Actions action = new Actions(webDriver);
		action.moveToElement(SelectElementByLinktext("Edit")).build().perform();
		action.moveToElement(SelectElementByLinktext("Page")).build().perform();
		SelectElementByLinktext("Add Page").click();	
	}
	
	//go to new content
	public void GotoAddnewConntent(){
		SelectElementByLinktext("New Content").click();	
	}
	
	//function go to Node
	public void GotoNode(String node) throws Exception{
		SelectElementByXpath("//a[@title='acme ']").click();
		SelectElementByXpath("//a[@title='"+node+"']").click();
	}
	
	//function add data to frame
	public void InputDataToFrame (String frameId, String data){
		webDriver.switchTo().frame(SelectElementByXpath(frameId));
		WebElement inputsummary = webDriver.switchTo().activeElement();
		inputsummary.sendKeys(data);
	}
	//function swith to parent windows
	public void SwitchtoParentWindow () throws Exception{
		try
	    {
	    Set<String> availableWindows = webDriver.getWindowHandles();
	    String WindowIDparent= null;
	    int counter = 1;
	    for (String windowId : availableWindows) {
	    if (counter == 1){
	    WindowIDparent = windowId;
	    }
	    counter++;
	    }
	    webDriver.switchTo().window(WindowIDparent);
	    Thread.sleep(1000);
	    }
	    catch (WebDriverException e)
	    {
	    e.printStackTrace();
	    }
	}
	
	// addnew article
	public void CreateNewArticle(String title, String name, String sum, String cont) throws Exception {
		SelectElementByXpath("//div[@title='Article']").click();
		// Input information
		SelectElementById("title").sendKeys(title);
		SelectElementById("name").sendKeys(name);
		InputDataToFrame("//td[@id='cke_contents_summary']/iframe",sum);
	    SwitchtoParentWindow();
	    InputDataToFrame("//td[@id='cke_contents_content']/iframe",cont);
	    SwitchtoParentWindow();
	    //save
		SelectElementById("Save & Close").click();
	}
	
	//add new annoucement
	public void CreateNewAnnoucement (String name, String sum) throws Exception{
		SelectElementByLinktext("Announcement").click();
		SelectElementById("name").sendKeys(name);
		InputDataToFrame("//td[@id='cke_contents_exo:summary']/iframe",sum);
	    SwitchtoParentWindow();
		SelectElementById("Save & Close").click();
	}
	
	//add new Free layout webcontent
	public void CreateNewFreeLayoutWebContent(String title, String name, String cont, String img, String sum, String css, String js) throws Exception {
		SelectElementByLinktext("Free layout webcontent").click();
		
		//input data in tab Main content
		SelectElementById("title").sendKeys(title);
		SelectElementById("name").sendKeys(name);
		InputDataToFrame("//td[contains(@id,'cke_contents_htmlData')]/iframe",cont);
	    SwitchtoParentWindow();
	    
		//input data in tab Illustration
	    SelectElementByXpath("//div[contains(text(),'Illustration')]").click();
		webDriver.switchTo().frame(SelectElementByXpath("//*[contains(@id,'uploadFrame')]"));
		SelectElementById("file").sendKeys(img);
	    SwitchtoParentWindow();
		InputDataToFrame("//td[@id='cke_contents_exo:summary']/iframe",sum);
	    SwitchtoParentWindow();
		
		//input data in tab Advanced
		SelectElementByXpath("//div[contains(text(),'Advanced')]").click();
		SelectElementByXpath("//textarea[contains(@id,'ContentCSS')]").sendKeys(css);
		SelectElementByXpath("//textarea[contains(@id,'ContentJS')]").sendKeys(js);
		SelectElementByLinktext("Save & Close").click();			
	}
	
	//add new file
	public void CreateNewFile(String name, String cont, String title)throws Exception{
		SelectElementByLinktext("File").click();	
		SelectElementById("name").sendKeys(name);
		InputDataToFrame("//td[@id='cke_contents_contentHtml']/iframe",cont);
	    SwitchtoParentWindow();
		SelectElementById("title0").sendKeys(title);
		SelectElementByLinktext("Save & Close").click();
	}
	
	//add new Sample Node
	public void CreateNewSampleNode(String title, String name, String img) throws Exception{
		SelectElementByLinktext("Sample node").click();
		SelectElementById("title").sendKeys(title);
		SelectElementById("name").sendKeys(name);
		SelectElementByXpath("//img[@title = 'Add Item']").click();
		Thread.sleep(1000);
		SelectOption("taxonomyTree","acme");
		SelectElementByXpath("//div[@class='Select16x16Icon']").click();
		SelectElementById("description").sendKeys("Test");
		webDriver.switchTo().frame(SelectElementByXpath("//iframe[contains(@id,'uploadFrame')]"));
		SelectElementById("file").sendKeys(img);
		Thread.sleep(5000);
		SwitchtoParentWindow();
		SelectElementById("summary").sendKeys("Test");
		SelectElementById("content").sendKeys("Test");
		SelectElementByLinktext("Save & Close").click();
	}

	// add new Kofax webDriver
	public void CreateNewKofax(String name) throws Exception{
		SelectElementByLinktext("Kofax document").click();
		SelectElementById("name").sendKeys(name);
		SelectElementByXpath("//img[@class='MultiFieldAction AddNewNodeIcon']").click();
		SelectOption("taxonomyTree","acme");	
		SelectElementByXpath("//div[@class='Select16x16Icon']").click();
		SelectElementByLinktext("Save & Close").click();
	}
	
	// add new File Plan
	public void CreateNewFilePlan(String name, String cat_inden, String dis_aut, String ori_org, String event){
		SelectElementByLinktext("File Plan").click();	
		SelectElementById("name").sendKeys(name);
		SelectElementByXpath("//div[contains(text(),'Record Properties')]").click();
		SelectElementById("recordCategoryIdentifier").sendKeys(cat_inden);
		SelectElementById("dispositionAuthority").sendKeys(dis_aut);
		SelectElementById("defaultOriginatingOrganization").sendKeys(ori_org);
		
		SelectElementByXpath("//div[contains(text(),'Process Properties')]").click();
		SelectElementById("eventTrigger").sendKeys(event);
		SelectElementByLinktext("Save & Close").click();
	}

	// add new postcard
	public void CreateNewPodcast(String name, String title, String link){
		SelectElementByLinktext("Podcast").click();
		SelectElementById("name").sendKeys(name);
		SelectElementById("title").sendKeys(title);
		SelectElementById("link").sendKeys(link);
		SelectElementByLinktext("Save & Close").click();
	}
	
	//add new Picture on head layout webcontent
	public void CreateNewPictureOnHeadLayout (String name, String title, String file) throws Exception {
		SelectElementByLinktext("Picture on head layout webcontent").click();
		SelectElementById("name").sendKeys(name);
		SelectElementById("title").sendKeys(title);
		webDriver.switchTo().frame(SelectElementByXpath("//iframe[contains(@id,'uploadFrame')]"));
		webDriver.findElement(By.id("file")).sendKeys(file);
		Thread.sleep(1000);
		SwitchtoParentWindow();
		SelectElementByLinktext("Save & Close").click();
	}
	
	//add new product
	public void CreateNewProduct (String name, String title){
		SelectElementByLinktext("Product").click();
		SelectElementById("name").sendKeys(name);
		SelectElementById("title").sendKeys(title);
		SelectElementByLinktext("Save & Close").click();
	}
	
	//add new Content Folder
	public void CreateNewContentFolder(String title, String name){
		SelectElementByLinktext("New Folder").click();
		SelectOption("type","nt:unstructured");
		SelectElementById("title").sendKeys(title);
		SelectElementById("name").sendKeys(name);
		SelectElementByLinktext("Save & Close").click();
	}
	//add new Document Folder
	public void CreateNewDocumentFolder(String title, String name){
		SelectElementByLinktext("New Folder").click();
		Select typefolder = null;
		try {
			while (typefolder ==null){
				Thread.sleep(1000);
				typefolder = new Select(SelectElementById("type"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		typefolder.selectByValue("nt:folder");
		SelectElementById("title").sendKeys(title);
		SelectElementById("name").sendKeys(name);
		SelectElementByLinktext("Save & Close").click();
	}
	
	//delete level 1 node
	public void DeleteNode(String node) throws Exception{
		Actions action = new Actions(webDriver);
		action.contextClick(SelectElementByXpath("//a[@title='"+node+"']")).perform();
		webDriver.findElement(By.partialLinkText("Delete")).click();
		SelectElementByLinktext("OK").click();
	}

	//delete level 2 node
	public void DeleteNode(String node1, String node2) throws Exception{
		SelectElementByXpath("//a[@title='"+node1+"']").click();
		Actions action = new Actions(webDriver);
		action.contextClick(SelectElementByXpath("//a[@title='"+node2+"']")).perform();
		webDriver.findElement(By.partialLinkText("Delete")).click();
		SelectElementByLinktext("OK").click();
	}
	
	//simple search
	public void SimpleSearch(String keyword){
		SelectElementById("simpleSearch").sendKeys(keyword);
		SelectElementById("SimpleSearch").click();
		isElementPresent("//div[contains(text(),'"+keyword+"')]");
	}
	
	//create new page without layout
	public void CreateNewPageEmptyLayout(String namepage){
		EnterNewPageForm();
		SelectElementById("pageName").sendKeys(namepage);
		SelectElementByLinktext("Next").click();
		SelectElementByLinktext("Next").click();
		SelectElementByXpath("//a[@class='EdittedSaveButton']").click();
	}
	
	//create new page has layout
	
}

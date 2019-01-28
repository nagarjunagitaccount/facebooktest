package qaf.example.steps;

import static com.qmetry.qaf.automation.step.CommonStep.*;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebComponent;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.LocatorUtil;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.sun.istack.internal.logging.Logger;

import sun.util.logging.resources.logging;

public class StepsLibrary  {
	
	@FindBy(locator="stories.tray.birthday")
	private QAFWebElement trayBirthDay;
	@FindBy(locator="stories.tray")
	private QAFWebElement storiestray;
	
    public QAFWebElement getStoriestray() {
		return storiestray;
	}

	public void setStoriestray(QAFWebElement storiestray) {
		this.storiestray = storiestray;
	}

	public QAFWebElement getTrayBirthDay() {
		return trayBirthDay;
	}

	public void setTrayBirthDay(QAFWebElement trayBirthDay) {
		this.trayBirthDay = trayBirthDay;
	}












	
	@QAFTestStep(description = "login to fb with credential {0} and {1}")
	public static void login(String username,String Password) {
		StepsLibrary step=new StepsLibrary();
		new WebDriverTestBase().getDriver().manage().window().maximize();
		verifyPresent("input.fb.login.username");
		sendKeys(username,"input.fb.login.username");
		verifyPresent("input.fb.login.password");
		sendKeys(Password,"input.fb.login.password");
		verifyPresent("input.fb.login.btn");
	    click("input.fb.login.btn");
		
	}
	
	@QAFTestStep(description = "get latest story and birthday")
	public static void Getlateststoryandbirthday() {
		StepsLibrary step=new StepsLibrary();
		String timeStampOfFirst=null;
		
		new Actions(new WebDriverTestBase().getDriver()).sendKeys(Keys.ESCAPE).build().perform();
        if(verifyPresent("stories.tray"))
        {
		timeStampOfFirst = getText("stories.tray.first");
	
		Reporter.log("Stories section exist,"+""+timeStampOfFirst);
        }
        else
        {
	    Reporter.log("Stories section doesn't exist");
        }

		if(verifyPresent("stories.tray.birthday"))
		{
						
			QAFExtendedWebElement dummyElement =new QAFExtendedWebElement("stories.tray.birthday");
			
			List<QAFWebElement> details=dummyElement.findElements("stories.tray.birthday");
            int n=details.size();
            

            if(n>1)
            {
                String numberofbirtdays=details.get(1).getText();

                String number= numberofbirtdays.replaceAll("[^0-9]", "");

                int ffinalnumber = Integer.parseInt(number);
	
                Reporter.log("Birthday's section exists,"+""+(ffinalnumber+1));

        }
            else
            {
            	Reporter.log("Birthday's section exists,1");
            	
            }
		}
		else
		{ 
        	Reporter.log("Birthday's sectiondoesn't exists");
		}

		}
	
		
	@QAFTestStep(description = "get latest messages")
	public static void getmessages() {
		StepsLibrary step=new StepsLibrary();
		verifyPresent("messages.icon");
		click("messages.icon");
		verifyPresent("messages.list");
		
		QAFExtendedWebElement dummyElement =new QAFExtendedWebElement("messages.list");
		
		verifyPresent("messages.list.all");
		waitForEnabled("messages.list.all", 30);
		List<QAFWebElement> details=dummyElement.findElements("messages.list.all");
		
		for(QAFWebElement ele:details)
		{
			String str=ele.getText().toString();
        	Reporter.log("messages,"+" "+str);
		}
		
		
		
	}
	
	@QAFTestStep(description = "search for {0}")
	public static void searchFor(String searchTerm) {
		
		new WebDriverTestBase().getDriver().manage().window().maximize();
		
		sendKeys(searchTerm, "input.search");
		verifyPresent("button.search");
		
		click("button.search");
	}
}

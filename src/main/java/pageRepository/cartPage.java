package pageRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.iOSBy;

public class cartPage {

	AndroidDriver driver;
	
	@FindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement visitToWebsiteBtn;
	
	
	public cartPage(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}


	public void visitToWebsite() {
		visitToWebsiteBtn.click();
	}
}

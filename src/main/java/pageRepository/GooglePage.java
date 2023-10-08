package pageRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class GooglePage {

	AndroidDriver driver;
	
	@FindBy(name="q")
	private WebElement searchTextfield;
	
	public GooglePage(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}


	public void search(String data) {
		searchTextfield.sendKeys(data);
	}
}

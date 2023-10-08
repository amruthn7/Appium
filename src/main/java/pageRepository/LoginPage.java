package pageRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.GesturesUtility;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage {

AndroidDriver driver;
	
	@FindBy(id= "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;
	
//	@FindBy(uiAutomator= "new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));")
//	private WebElement country;
	
	@FindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameTextField;
	
	@FindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleRadioBtn;
	
	@FindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleRadioBtn;
	
	@FindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopBtn;
	
	
	public LoginPage(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void letsShop(GesturesUtility gUtil,String name,String value) {
		countryDropdown.click();
		gUtil.scroll(value);
		nameTextField.sendKeys(name);
		maleRadioBtn.click();
		letsShopBtn.click();
	}

}

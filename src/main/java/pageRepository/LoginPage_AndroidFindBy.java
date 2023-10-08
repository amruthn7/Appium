package pageRepository;

import java.sql.Driver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage_AndroidFindBy {

	//declaration
	AndroidDriver driver;
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;
	
	@AndroidFindBy(uiAutomator= "new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));")
	private WebElement country;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameTextField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleRadioBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleRadioBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopBtn;
	
	//initialization
	public LoginPage_AndroidFindBy(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	//business logic methods
	public void letsShop(String name) {
		countryDropdown.click();
		country.click();
		nameTextField.sendKeys(name);
		maleRadioBtn.click();
		letsShopBtn.click();
	}
}

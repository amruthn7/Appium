package pageRepository.BluDoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.DriverUtility;
import genericUtilities.GesturesUtility;
import io.appium.java_client.android.AndroidDriver;

public class ClinicDetailsPage {

	AndroidDriver driver;

	@FindBy(id="com.likesby.bludoc:id/clinicName")
	private WebElement clinicNameTxtField;

	@FindBy(id="com.likesby.bludoc:id/clinicAddress")
	private WebElement clinicAddTxtField;

	@FindBy(id="com.likesby.bludoc:id/fromOne")
	private WebElement fromTxtField;

	@FindBy(id="com.likesby.bludoc:id/twoOne")
	private WebElement toTxtField;

	@FindBy(id="com.likesby.bludoc:id/toTwo")
	private WebElement fromTxtField2;

	@FindBy(id="com.likesby.bludoc:id/mailId")
	private WebElement toTxtField2;

	@FindBy(xpath="	//android.widget.TextView[@text='Next']")
	private WebElement NextBtn;

	public ClinicDetailsPage(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void enterClinicDetials(GesturesUtility gUtil,DriverUtility dUtil) {
		clinicNameTxtField.sendKeys("HMS");
		clinicAddTxtField.sendKeys("JP nagar");
	}
}

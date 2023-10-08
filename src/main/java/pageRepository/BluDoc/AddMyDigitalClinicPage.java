package pageRepository.BluDoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.DriverUtility;
import genericUtilities.GesturesUtility;
import io.appium.java_client.android.AndroidDriver;

public class AddMyDigitalClinicPage {
	AndroidDriver driver;
	//Doctor details
	@FindBy(id="com.likesby.bludoc:id/btn_register")
	private WebElement letsStartBtn;
	
	@FindBy(id="com.likesby.bludoc:id/doctorName")
	private WebElement docNameTxtField;
	
	@FindBy(id="com.likesby.bludoc:id/registrationNumber")
	private WebElement regNumberTxtField;
	
	@FindBy(id="com.likesby.bludoc:id/qualifications")
	private WebElement qualificationTxtField;
	
	@FindBy(id="com.likesby.bludoc:id/contactNumber")
	private WebElement contactNumTxtField;
	
	@FindBy(id="com.likesby.bludoc:id/mailId")
	private WebElement mailIDTxtField;
	
	@FindBy(xpath="	//android.widget.TextView[@text='Next']")
	private WebElement NextBtn;
	
	public AddMyDigitalClinicPage(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterDoctorDetials(GesturesUtility gUtil,DriverUtility dUtil) {
		docNameTxtField.clear();
		docNameTxtField.sendKeys("doctor");
		dUtil.pause(2000);
		regNumberTxtField.sendKeys("12345");
		dUtil.pause(2000);
		qualificationTxtField.sendKeys("MBBS");
		dUtil.pause(2000);
		contactNumTxtField.sendKeys("8147140085");
		dUtil.pause(2000);
		mailIDTxtField.sendKeys("amruthgowda36@gmail.com");
		dUtil.pause(2000);
		gUtil.scroll("Next");
		NextBtn.click();
	}
}

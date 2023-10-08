package com.BluDoc;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import genericUtilities.BaseAppiumClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import pageRepository.BluDoc.AddMyDigitalClinicPage;

public class CreateDoctor extends BaseAppiumClass {

	@Test
	public void createDocTest() throws MalformedURLException {
		
//		File f= new File("C:\\\\Users\\\\amrut\\\\AppData\\\\Roaming\\\\npm\\\\node_modules\\\\appium\\\\build\\\\lib\\\\main.js");
//		
//		AppiumDriverLocalService sb = new AppiumServiceBuilder().withAppiumJS(f).withIPAddress("127.0.0.1").usingPort(4723)
//		.withTimeout(Duration.ofSeconds(300)).build();
//		
//		sb.start();
//		
//		DesiredCapabilities cap= new DesiredCapabilities();
//		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "POCO X2");
//		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
//		cap.setCapability(MobileCapabilityType.UDID, "302ed886");
//		
//		cap.setCapability("appPackage", "com.likesby.bludoc");
//		cap.setCapability("appActivity", ".SplashActivity");
		
//		URL u = new URL("http://localhost:4723");
//		AndroidDriver driver = new AndroidDriver(u,cap);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(AppiumBy.id("com.likesby.bludoc:id/sign_in_button2")).click();
		
		dUtil.pause(4000);
		
		//driver.findElement(AppiumBy.id("(//android.widget.LinearLayout[@index='1'])[1]")).click();
		
//		driver.findElement(AppiumBy.id("com.likesby.bludoc:id/floatingActionButton")).click();
//		driver.findElement(AppiumBy.id("(//android.widget.LinearLayout[@index='0'])[1]")).click();
		
		
		driver.findElement(AppiumBy.id("com.likesby.bludoc:id/img3")).click();

		AddMyDigitalClinicPage ad=new AddMyDigitalClinicPage(driver);
		ad.enterDoctorDetials(gUtil,dUtil);


	}
}

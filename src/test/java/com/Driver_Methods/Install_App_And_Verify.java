package com.Driver_Methods;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Install_App_And_Verify {

	@Test
	public void installAppTest() throws MalformedURLException {
		
		File f= new File("C:\\Users\\amrut\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");

		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(f).withIPAddress("127.0.0.1").usingPort(4723)
				.withTimeout(Duration.ofSeconds(300)).build();

		service.start();

		DesiredCapabilities cap= new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "POCO X2");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability(MobileCapabilityType.UDID, "302ed886");
		
//		cap.setCapability("appPackage", "com.androidsample.generalstore"); 
//		cap.setCapability("appActivity", ".SplashActivity"); 
		
		
		URL u = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(u, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//To Install app
		driver.installApp("D:\\Eclipse Workspace\\Selenium Workspaces\\SDET Workspace\\Appium\\src\\test\\resources\\General-Store.apk");
		
		//To check app is installed or not
		boolean appInstalled = driver.isAppInstalled("com.androidsample.generalstore");
		System.out.println(appInstalled);
		
	}
}

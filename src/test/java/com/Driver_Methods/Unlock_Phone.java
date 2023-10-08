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

public class Unlock_Phone {

	@Test
	public void unlockPhoneTest() throws MalformedURLException {
		
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

		//To unlock with Password
		cap.setCapability("unlockType", "password");
		cap.setCapability("unlockKey", "amr123");
		
		//To unlock with Pin
		cap.setCapability("unlockType", "pin");
		cap.setCapability("unlockKey", "12345");
		
		//To unlock with Pattern
		cap.setCapability("unlockType", "pattern");
		cap.setCapability("unlockKey", "14789");
		
		URL u = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(u, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
}

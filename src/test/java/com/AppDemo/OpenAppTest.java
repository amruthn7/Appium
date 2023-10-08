package com.AppDemo;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class OpenAppTest {

	@Test
	public void openAppTest() throws MalformedURLException{
		
		//To start the server from the script
		File file = new File("C:\\Users\\amrut\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");	
		
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(file)
				.withIPAddress("127.0.0.1").usingPort(4723)
				.withTimeout(Duration.ofSeconds(300)).build();
		
		//service.start();
		
		DesiredCapabilities cap= new DesiredCapabilities();
		//identify specfic devices
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "POCO X2");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability(MobileCapabilityType.UDID, "302ed886");
		
		//To open particular app
		cap.setCapability("appPackage", "io.appium.android.apis"); //
		cap.setCapability("appActivity", ".ApiDemos"); //

		URL u = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(u, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
		driver.pressKey(new KeyEvent(AndroidKey.VOLUME_UP));
		driver.pressKey(new KeyEvent(AndroidKey.VOLUME_DOWN));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		//driver.quit();
		
		//To stop the server
		service.stop();
	}

	
}

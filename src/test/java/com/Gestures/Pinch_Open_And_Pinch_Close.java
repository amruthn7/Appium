package com.Gestures;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Pinch_Open_And_Pinch_Close{

	@Test
	public void pinchInOutTest() throws MalformedURLException {
		
		File file = new File("C:\\Users\\amrut\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");	
		
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(file).withIPAddress("127.0.0.1")
		.usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
		
		service.start();
		
		DesiredCapabilities cap= new DesiredCapabilities();

		//identify specfic devices
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "POCO X2");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability(MobileCapabilityType.UDID, "302ed886");

		//To open particular app
		cap.setCapability("appPackage", "com.davemorrissey.labs.subscaleview.sample"); //
		cap.setCapability("appActivity", ".MainActivity"); //

		URL u = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(u, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


		driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/continue_button")).click();
		driver.findElement(AppiumBy.id("android:id/button1")).click();

		driver.findElement(AppiumBy.id("com.davemorrissey.labs.subscaleview.sample:id/basicFeatures")).click();
		WebElement imageEle = driver.findElement(AppiumBy.id("com.davemorrissey.labs.subscaleview.sample:id/imageView"));
		
		//pinchOpenGesture(zoom in)
		((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) imageEle).getId(),
			    "percent", 0.75 //percentage we want to zoom in 
			));
		
		//pinchCloseGesture(zoom out)
		((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) imageEle).getId(),
			    "percent", 0.75 //percentage we want to zoom out
			));
	}
}

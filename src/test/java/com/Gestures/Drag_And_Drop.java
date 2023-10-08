package com.Gestures;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Drag_And_Drop {

	@Test
	public void dragAndDropTest() throws MalformedURLException {

		//To start the server from the script
		File file = new File("C:\\Users\\amrut\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");	

		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(file)
				.withIPAddress("127.0.0.1").usingPort(4723)
				.withTimeout(Duration.ofSeconds(300)).build();

		service.start();


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
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

//		WebElement firstDot = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
//		
//		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
//			    "elementId", ((RemoteWebElement) firstDot).getId(),
//			    "endX", 640,
//			    "endY", 1000
//			));
		
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "startX", 200,
			    "startY", 600,
			    "endX", 640,
			    "endY", 1000
			));
		
		String expText = "Dropped!";
		String actText = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText();
		Assert.assertEquals(expText, actText);
		
		//To stop the server
		service.stop();

	}
}

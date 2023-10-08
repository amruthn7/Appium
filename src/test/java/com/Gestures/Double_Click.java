package com.Gestures;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Double_Click {

	@Test
	public void doubleClickTest() throws MalformedURLException {
		
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
		
		cap.setCapability("appPackage", "io.appium.android.apis");
		cap.setCapability("appActivity", ".ApiDemos");
		
		URL u = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(u,cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(AppiumBy.accessibilityId("Text")).click();
		driver.findElement(AppiumBy.accessibilityId("LogTextBox")).click();
		WebElement addBtn = driver.findElement(AppiumBy.accessibilityId("Add"));
		
		((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) addBtn).getId()
			));
		
		String textBox = driver.findElement(AppiumBy.id("io.appium.android.apis:id/text")).getText();
		String expText = "This is a test This is a test";
		Assert.assertTrue(textBox.equalsIgnoreCase(expText));
	}
}

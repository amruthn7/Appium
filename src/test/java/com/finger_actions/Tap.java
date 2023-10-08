package com.finger_actions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class Tap {
	
	public static void main(String[] args) throws MalformedURLException {
//		File file = new File("C:\\Users\\amrut\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");	
//
//		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(file)
//				.withIPAddress("127.0.0.1").usingPort(4723).build();
//
//		service.start();

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "POCO X2");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		dc.setCapability(MobileCapabilityType.UDID, "302ed886");

		//To open particular app
		dc.setCapability("appPackage", "com.androidsample.generalstore"); 
		dc.setCapability("appActivity", ".SplashActivity"); 

		URL u = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(u,dc);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement element = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry"));
		//tap(element,driver);
		
		driver.executeScript("mobile: clickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId()
			));
	}
	public static void tap(WebElement element,AndroidDriver driver) {
		Point location = element.getLocation();
		//System.out.println("location"+location);
		Dimension size = element.getSize();
		//System.out.println("size"+size);
		
		Point centerOfElement = getCenterOfElement(location, size);
		//System.out.println("centerOfElement"+centerOfElement);
		
		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence seq = new Sequence(finger1, 1)
		.addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport() ,centerOfElement))
		.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		.addAction(new Pause(finger1, Duration.ofMillis(200)))
		.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Collections.singletonList(seq));
		
	}
	
	public static Point getCenterOfElement(Point location,Dimension size) {
		return new Point(location.getX()+ size.getWidth()/2,
						location.getY()+ size.getHeight()/2);
	}
}

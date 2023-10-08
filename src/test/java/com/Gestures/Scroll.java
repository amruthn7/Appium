package com.Gestures;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Scroll {

//	@Test
//	public void scrollTest() throws MalformedURLException {
		public static void main(String[] args) throws Throwable {
			
		

//		File f= new File("C:\\Users\\amrut\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
//
//		AppiumDriverLocalService service = new AppiumServiceBuilder()
//				.withAppiumJS(f).withIPAddress("127.0.0.1").usingPort(4723)
//				.withTimeout(Duration.ofSeconds(300)).build();
//
//		service.start();

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
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Tabs\"));")).click();
		driver.findElement(AppiumBy.accessibilityId("5. Scrollable")).click();
		WebElement tab = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='TAB 4']"));
    
        Point location = tab.getLocation(); 
        Dimension size = tab.getSize();
        Point midPoint= new Point(location.x + size.getWidth() / 2, location.y + size.getHeight() / 2);

        
        int left = midPoint.x - (int) (midPoint.x * 0.25); //
        int right = midPoint.x + (int) (midPoint.x * 0.25);
        Point start = new Point(right, midPoint.y);
        Point end = new Point(left, midPoint.y);
        
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));

		
	}
}

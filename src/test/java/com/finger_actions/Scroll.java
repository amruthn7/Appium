package com.finger_actions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.google.common.collect.ImmutableList;

import genericUtilities.ActionsUtility.ScrollDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Scroll {
	
	static AndroidDriver driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
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
		dc.setCapability("appPackage", "io.appium.android.apis"); 
		dc.setCapability("appActivity", ".ApiDemos"); 

		URL u = new URL("http://localhost:4723");
		 driver = new AndroidDriver(u,dc);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		WebElement element = driver.findElement(AppiumBy.accessibilityId("Grid"));
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Tabs\"));")).click();
		driver.findElement(AppiumBy.accessibilityId("5. Scrollable")).click();
		WebElement tab = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='TAB 4']"));

		Scroll.scroll(tab,ScrollDirection.RIGHT,0.25,driver);
		
	}
	//Scroll action of given element
	public static void scroll(WebElement element, ScrollDirection dir, double scrollRatio,AndroidDriver driver) {
//		public static void scroll(ScrollDirection dir, double scrollRatio,AndroidDriver driver) {

		Dimension size;
		Point midPoint;
        Duration SCROLL_DUR = Duration.ofMillis(300);
        
        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }

        if(element != null){
            midPoint = getCenter(element);
        }else{ //entire screen is scrollable
            size = driver.manage().window().getSize();
            System.out.println(size);
            midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        }

        int bottom = midPoint.y + (int) (midPoint.y * scrollRatio);
        int top = midPoint.y - (int) (midPoint.y * scrollRatio);
        int left = midPoint.x - (int) (midPoint.x * scrollRatio);
        int right = midPoint.x + (int) (midPoint.x * scrollRatio);

        if (dir == ScrollDirection.UP) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR, driver);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR,driver);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR,driver);
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR,driver);
        }
    }

    protected static void swipe(Point start, Point end, Duration duration, AndroidDriver driver) {

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }
    
    private static Point getCenter(WebElement el) {
        Point location = el.getLocation();
        Dimension size = el.getSize();
        return new Point(location.x + size.getWidth() / 2, location.y + size.getHeight() / 2);
    }
}

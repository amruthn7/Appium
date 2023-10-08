package com.AppDemo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Mobile_Browser {

	@Test
	public void chromeTest() throws MalformedURLException {
		
		File file = new File("C:\\Users\\amrut\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");	

		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(file)
				.withIPAddress("127.0.0.1").usingPort(4723)
				.withTimeout(Duration.ofSeconds(300)).build();

		service.start();

		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "POCO X2");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability(MobileCapabilityType.UDID, "302ed886");

		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome"); 
		
		cap.setCapability("chromedriverExecutables", "D:\\Eclipse Workspace\\Selenium Workspaces\\SDET Workspace\\Appium\\src\\test\\resources\\chromedriver.exe"); 
		//appium --allow-insecure chromedriver_autodownload
		
		URL u = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(u, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.instagram.com");
		
		driver.findElement(AppiumBy.xpath("//div[text()='Log In']")).click();
		driver.findElement(AppiumBy.xpath("//input[@name='username']")).sendKeys("abc@gmail.com");
		
	}
}

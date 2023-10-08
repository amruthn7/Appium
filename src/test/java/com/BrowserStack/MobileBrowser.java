package com.BrowserStack;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.appium.java_client.remote.MobileCapabilityType;

public class MobileBrowser {

	public static final String username="amruthn_pHIBe0";
	public static final String accessKey="UqKWQiWnmuYFvcjRzFrF";
	public static final String url="https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub";
	@Test
	public void mobileBrowserTest() throws MalformedURLException {
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy S21");
		//dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
		dc.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		dc.setCapability("build", "2.2.4");
		dc.setCapability("name", "MobileBrowser_Automate");
		
		URL u = new URL(url);
		WebDriver driver = new RemoteWebDriver(u, dc);
		driver.get("https://facebook.com");
	}
}

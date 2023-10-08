package com.BrowserStack;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobileAppTesting {
	public static final String username="amruthn_pHIBe0";
	public static final String accessKey="UqKWQiWnmuYFvcjRzFrF";
	public static final String url="https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub";
	
	@Test
	public void mobileAppTest() throws MalformedURLException {
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy S21");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
		dc.setCapability("Project", "Api Demo project");
		dc.setCapability("build", "ApiDemo 2.2.3");
		dc.setCapability("name", "MobileBrowser_Automate");
		dc.setCapability("app", "bs://77751ecb018cc5e3d677c115e2e1ebfaa51829cd");
		
		URL u = new URL(url);
		AndroidDriver driver = new AndroidDriver(u, dc);
	}
}

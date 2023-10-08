package genericUtilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import pageRepository.GooglePage;
import pageRepository.LoginPage;
import pageRepository.ProductsPage;
import pageRepository.cartPage;

public class BaseAppiumClass {

	AppiumDriverLocalService service;
	public AndroidDriver driver;
	public static AndroidDriver sdriver;
	public DriverUtility dUtil;
	public GesturesUtility gUtil;
	public LoginPage lp;
	public ProductsPage pp;
	public cartPage cp;
	public GooglePage gp;


	@BeforeSuite
	public void startServer() {

		File file = new File("C:\\Users\\amrut\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");	

		service = new AppiumServiceBuilder().withAppiumJS(file)
				.withIPAddress("127.0.0.1").usingPort(4723)
				.withTimeout(Duration.ofSeconds(300)).build();

		service.start();
	}

	@BeforeMethod
	public void openApp() throws MalformedURLException   {
		DesiredCapabilities cap= new DesiredCapabilities();
		//identify specfic devices
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "POCO X2");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability(MobileCapabilityType.UDID, "302ed886");
		cap.setCapability("noReset", true);

		//To open particular app
		cap.setCapability("appPackage", "com.likesby.bludoc"); 
		cap.setCapability("appActivity", ".SplashActivity"); 

		URL u = new URL("http://localhost:4723");
		driver = new AndroidDriver(u, cap);
		sdriver=driver;
		dUtil = new DriverUtility(driver);
		gUtil= new GesturesUtility(driver);
		lp = new LoginPage(driver);
		pp = new ProductsPage(driver);
		cp = new cartPage(driver);
		gp = new GooglePage(driver);
	}

	@AfterMethod
	public void closeApp() {
		driver.quit();
	}

	@AfterSuite
	public void stopServer() {
		service.stop();
	}
}

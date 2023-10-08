package genericUtilities;

import java.time.Duration;

import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;

public class DriverUtility {

	AndroidDriver driver;

	public DriverUtility(AndroidDriver driver) {
		this.driver = driver;
	}

	public void installApp(String apkFilePath) {
		driver.installApp(apkFilePath);
	}
	
	//To check app is installed or not
	public boolean isAppInstalled(String appPackage) {
		boolean appInstalled = driver.isAppInstalled(appPackage);
		return appInstalled;
	}

	//To Activate app
	public void activateApp(String appPackage) {
		driver.activateApp(appPackage);
	}


	//To run app in background
	public void runAppInBackground(int time) {
		driver.runAppInBackground(Duration.ofSeconds(time));
	}

	//To check app is running in background or not
	public ApplicationState checkAppRunningInBackground(String appPackage) {
		ApplicationState queryAppState = driver.queryAppState(appPackage);
		return queryAppState;
	}

	//To hide keyboard
	public void hideKeyboard() {
		driver.hideKeyboard();
	}

	//To open Notifications
	public void openNotifications() {
		driver.openNotifications();
	}

	//To change screen orientation
	public void rotateToLandscape() {
		ScreenOrientation sc = driver.getOrientation();
		driver.rotate(sc.LANDSCAPE);
	}

	public void rotateToPortrait() {
		ScreenOrientation sc = driver.getOrientation();
		driver.rotate(sc.PORTRAIT);
	}

	public void context(String view) {
		driver.context(view);
	}

	public void lock() {
		driver.lockDevice();
	}
	public void unlock() {
		driver.unlockDevice();
	}
	public void deviceLocked() {
		driver.isDeviceLocked();
	}

	public void pause(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitForPageLoad( long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
}

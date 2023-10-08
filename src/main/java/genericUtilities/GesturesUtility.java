package genericUtilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class GesturesUtility {

	AndroidDriver driver;

	public GesturesUtility(AndroidDriver driver) {
		this.driver=driver;
	}

	//snippet from Appium gesture github to longclick
	public void longClickGesture(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"duration", 2000 //2 seconds to long click
				));
	}

	/**
	 * 
	 * @param value
	 */
	public void scroll(String value) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+value+"\"));")).click();;
	}
	
	/**
	 * performing scroll operation based on element
	 * @param direction
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 */
	public void scrollGesture(String direction,int left,int top,int width,int height) {
		boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				"left", left, "top", top, "width", width, "height", height,
				"direction", direction,
				"percent", 3.0
				));
	}


	
	/**
	 * Performing swipe action based on element
	 * @param direction
	 * @param element
	 */
	public void swipeGesture(String direction,WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"direction", direction,
				"percent", 0.75
				));
	}

	
	/**
	 * Performing swipe action based on coordinates
	 * @param direction
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 */
	public void swipeGesture(String direction,int left,int top,int width,int height) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"left", left, "top", top, "width", width, "height", height,
				"direction", direction,
				"percent", 0.75
				));
	}

	
	/**
	 * pinchOpenGesture(zoom in)
	 * @param element
	 */
	public void pinchOpenGesture(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"percent", 0.75 //percentage we want to zoom in 
				));
	}

	
	/**
	 * pinchCloseGesture(zoom out)
	 * @param element
	 */
	public void pinchCloseGesture(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"percent", 0.75 //percentage we want to zoom out
				));
	}
	
	public void dragGesture(int startX,int startY, int endX,int endY) {
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
		    "startX", startX,
		    "startY", startY,
		    "endX", endX,
		    "endY", endY
		));
	}
	
	public void dragGesture(WebElement element, int endX, int endY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId(),
			    "endX", endX,
			    "endY", endY
			));
		}
}

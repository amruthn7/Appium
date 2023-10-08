package genericUtilities;

import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import com.google.common.collect.ImmutableList;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ActionsUtility {
	
	//To get the center of element
	public static Point getCenterOfElement(Point location,Dimension size) {
		return new Point(location.getX()+ size.getWidth()/2,
						location.getY()+ size.getHeight()/2);
	}
	
	//Tap action of given element
	public static void tap(WebElement element,AndroidDriver driver) {
		Point location = element.getLocation();
		Dimension size = element.getSize();
		
		Point centerOfElement = getCenterOfElement(location, size);
		
		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence seq = new Sequence(finger1, 1)
		.addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport() ,centerOfElement))
		.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		.addAction(new Pause(finger1, Duration.ofMillis(200)))
		.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Collections.singletonList(seq));
	}
	
	//Double tap action of given element
	public static void doubleTap(WebElement element,AndroidDriver driver) {
		Point location = element.getLocation();
		Dimension size = element.getSize();
		
		Point centerOfElement = getCenterOfElement(location, size);
		
		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence seq = new Sequence(finger1, 1)
		.addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),centerOfElement))
		.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		.addAction(new Pause(finger1, Duration.ofMillis(100)))
		.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
		.addAction(new Pause(finger1, Duration.ofMillis(100)))
		.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		.addAction(new Pause(finger1, Duration.ofMillis(100)))
		.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Collections.singletonList(seq));
	}
	
	//Long press action of given element
	public static void longPress(WebElement element,AndroidDriver driver) {
		Point location = element.getLocation();
		Dimension size = element.getSize();
		
		Point centerOfElement = getCenterOfElement(location, size);
		
		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence seq = new Sequence(finger1, 1)
		.addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport() ,centerOfElement))
		.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		//long pressing on element for 2 seconds
		.addAction(new Pause(finger1, Duration.ofSeconds(2)))
		.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Collections.singletonList(seq));
	}
	
	//Scroll action of given element
	public static void scroll(WebElement element, ScrollDirection dir, double scrollRatio,AndroidDriver driver) {
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
    
    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }
    
	//Scroll action
	public static void scroll(ScrollDirection dir, double scrollRatio, AndroidDriver driver) {
		
		Dimension size;
		Point midPoint;
        Duration SCROLL_DUR = Duration.ofMillis(300);
        
        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }

        	//entire screen is scrollable
            size = driver.manage().window().getSize();
            System.out.println(size);
            midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));

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
}

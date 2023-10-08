package pageRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class ProductsPage {

	AndroidDriver driver;
	
	@FindBy(id="com.androidsample.generalstore:id/productAddCart")
	private WebElement firstProduct;
		
	@FindBy(id="com.androidsample.generalstore:id/productAddCart")
	private WebElement addToCartBtn;
	
	@FindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartBtn;

	public ProductsPage(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}

	public void addPrdToCart() {
		//firstProduct.click();
		addToCartBtn.click();
		cartBtn.click();
	}
	
	
	
}

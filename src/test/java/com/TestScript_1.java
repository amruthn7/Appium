package com;

import java.net.MalformedURLException;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseAppiumClass;

@Listeners(genericUtilities.ListnersImplemention.class)
public class TestScript_1 extends BaseAppiumClass {

	@Test
	public void addItemToCartTest() throws MalformedURLException, InterruptedException {
		
		dUtil.waitForPageLoad(10);
		lp.letsShop(gUtil,"amruth","Brazil");
		pp.addPrdToCart();
		//fail
		Assert.fail();
		cp.visitToWebsite();
		dUtil.pause(2000);
		dUtil.context("WEBVIEW_com.androidsample.generalstore");
		gp.search("abc"+Keys.ENTER);
	}
}

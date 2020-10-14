package com.utilities;

import org.openqa.selenium.By;

public class pom {
	//Locating the WebElements required
		public static final By signIn= By.xpath("//*[@title='Log in to your customer account']");
		public static final By singInBox = By.xpath("//*[text()='Already registered?']");
		public static final By email = By.id("email");
		public static final By pwd = By.id("passwd");
		public static final By login= By.cssSelector("#SubmitLogin > span");
		public static final By dress = By.cssSelector("#block_top_menu > ul > li:nth-child(2) > a");
		public static final By eveDress = By.cssSelector("#block_top_menu > ul > li:nth-child(2) > ul > li:nth-child(2) > a");
		public static final By image =By.xpath("//*[@title='Printed Dress' and @itemprop='image']");
		public static final By dropdown = By.xpath("//*[@id=\"group_1\"]");
		public static final By addToCart = By.xpath("//*[text()='Add to cart']");
		public static final By checkout = By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a > span");
		public static final By scroll = By.xpath("//*[text()=' Summary']");
		public static final By signOut = By.xpath("//*[@title='Log me out']");
}

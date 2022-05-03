package com.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.LoginPage;
import com.pages.ProductsPage;
import com.pages.BasePage;

public class LoginTest extends BaseTest {
	@Test(priority=1)
	@Parameters(value = {"chrome"})
	public void verifyLoginPageTitle() {
		String loginPageTitle = page.getInstance(LoginPage.class).getLoginPageTitle();
		Assert.assertEquals(loginPageTitle, "Swag Labs");
		
	}	
	@Test(priority=2)
	public void verifyLogin_AuthorizedUser() {
		ProductsPage productsPage = page.getInstance(LoginPage.class).doLogin("standard_user", "secret_sauce");
		String productsPageHeader_Actual = productsPage.getProductsPageHeader();
		Assert.assertEquals(productsPageHeader_Actual, "PRODUCTS");
		productsPage.logOut();
	}

	@Test(priority=3)
	public void verifyLogin_UnAuthorizedUser() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.doLogin_WithWrongPassword("standard_user", "Test");
		boolean verify = loginPage.verify_UnAuthorizationError();
		Assert.assertEquals(verify, true, "User is Authorized");
		
	}
}
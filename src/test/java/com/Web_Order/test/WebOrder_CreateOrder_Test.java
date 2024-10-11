package com.Web_Order.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Web_Order.common.Web_Orders_BasePage;
import com.Web_Order.common.Web_Orders_BaseSetup;
import com.Web_Order.home.WebOrders_HomePage;
import com.Web_Order.home.WebOrders_OrdersPage;
import com.Web_Order.home.WebOrders_SignInPage;
import com.Web_Order.home.WebOrders_ViewAllOrdersPage;
import com.Web_Order.utils.WebOrders_TestData;

public class WebOrder_CreateOrder_Test extends Web_Orders_BaseSetup{
	WebOrders_SignInPage signIn;
	WebOrders_HomePage homePage;
	Web_Orders_BasePage basePage;
	WebOrders_OrdersPage orderPage;
	WebOrders_ViewAllOrdersPage allOrderPage;
	private WebDriver driver;


	@BeforeClass
	public void setUp() {
		driver = getDriver();
		basePage = new Web_Orders_BasePage(driver);
	}

	@Test(description = "Validate reset button", dataProvider = "Reset Form", dataProviderClass = WebOrders_TestData.class, priority = 1, enabled = false)
	public void verifyCheckAll(String product, String qty, String custName, String street, String city, String state,
			String zip, String card, String cardNr, String expiry, String expectedResult) throws Exception {
		test = extent.createTest("Test Case 1", "Verify if all fields are cleared after clicking reset button");
		signIn = basePage.GoToHomePageAndSignIn("Tester", "test");
		signIn.verifySignInPageText();
		homePage = new WebOrders_HomePage(driver);
		orderPage = homePage.clickOnOrdersTab();
		orderPage.resetForm(product, qty, custName, street, city, state, zip, card, cardNr, expiry, expectedResult);
		basePage.logout();

	}
	
	@Test(description = "create and verify new Orders", dataProvider = "OrdersExcelData", dataProviderClass = WebOrders_TestData.class, priority = 2, enabled = true)
	public void createAndVerifyOrder(String product, String qty, String custName, String street, String city, String state,
			String zip, String card, String cardNr, String expiry, String expectedResult) throws Exception {
		test = extent.createTest("Test Case 2", "Create and Verify if order is created successfully");
		signIn = basePage.GoToHomePageAndSignIn("Tester", "test");
		signIn.verifySignInPageText();
		homePage = new WebOrders_HomePage(driver);
		orderPage = homePage.clickOnOrdersTab();
		orderPage.createOrder(product, qty, custName, street, city, state, zip, card, cardNr, expiry, expectedResult);
		
		if(expectedResult.equalsIgnoreCase("success")) 
		{
		allOrderPage = homePage.clickOnAllOrdersTab();
		allOrderPage.verifyUpdatedOrder(product, qty, custName, street, city, state, zip, card, cardNr, expiry);
		}
		basePage.logout();

		
	}
}

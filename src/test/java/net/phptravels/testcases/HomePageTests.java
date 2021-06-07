package net.phptravels.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import net.phptravels.base.TestBase;
import net.phptravels.pages.HomePage;
import net.phptravels.utlis.TestUtils;

public class HomePageTests extends TestBase{

	HomePage homePage;

	public HomePageTests() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}

	@Test(priority=1, enabled = true)
	public void verifyHomePageTitle() {
		String actualPageTitle = TestUtils.getPageTitle();
		Assert.assertEquals(actualPageTitle, "PHPTRAVELS | Travel Technology Partner", "Page title mismatch");
	}

	@Test(priority = 2, enabled = true)
	public void selectInrCurrency(){
		homePage.selectCurrency();
		Assert.assertTrue(TestUtils.checkVisibilityOfElement(homePage.dpdnInr, 3000));
		}

	@Test(priority =3, enabled = true)
	public void selectEngLanguage() {
		homePage.selectLanguage();
		}

	@Test(priority = 4, enabled = true)
	public void searchHotel() {
		homePage.selectHotel();
		}
	
	@AfterMethod
	public void teadDown() {
		driver.quit();
	}
}

package net.phptravels.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import net.phptravels.base.TestBase;
import net.phptravels.pages.BookAsGuestPage;
import net.phptravels.pages.HomePage;
import net.phptravels.pages.HotelDetailsPage;
import net.phptravels.utlis.TestUtils;

public class BookAsGuestTest extends TestBase{

	BookAsGuestPage bookAsGuestPage;
	HomePage homePage;
	HotelDetailsPage hotelDetailsPage;

	public BookAsGuestTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		bookAsGuestPage = new BookAsGuestPage();
		homePage = new HomePage();
		hotelDetailsPage = new HotelDetailsPage();
		
		homePage.selectHotel();
		hotelDetailsPage.selectRoom();
		hotelDetailsPage.bookRoom();
		
	}

	@Test(priority = 1, enabled = true)
	public void verifyBookAsGuestPageTitle() {
		String actualPageTitle = TestUtils.getPageTitle();
		Assert.assertEquals(actualPageTitle, "Jumeirah Beach Hotel","Page title mismatch");
	}

	@Test(priority = 2, enabled = true)
	public void confirmGuestBooking() {
		bookAsGuestPage.inputName(prop.getProperty("guestFirstName"), prop.getProperty("guestLastName"));
		bookAsGuestPage.inputEmail(prop.getProperty("guestEmail"), prop.getProperty("confirmGuestEmail"));
		bookAsGuestPage.inputPhoneNumber(prop.getProperty("guestPhoneNumber"));
		bookAsGuestPage.selectCountry();
		bookAsGuestPage.selectExtras();
		bookAsGuestPage.confirmBooking();
	}

		@AfterMethod
		public void tearDown() {
			driver.quit();
		}

}

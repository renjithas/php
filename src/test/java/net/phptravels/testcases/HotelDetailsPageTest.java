package net.phptravels.testcases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import net.phptravels.base.TestBase;
import net.phptravels.pages.HomePage;
import net.phptravels.pages.HotelDetailsPage;
import net.phptravels.utlis.TestUtils;

public class HotelDetailsPageTest extends TestBase{

	HotelDetailsPage hotelDetailsPage;
	HomePage homePage;

	public HotelDetailsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		hotelDetailsPage = new HotelDetailsPage();
		homePage = new HomePage();
		
		homePage.selectHotel();
	}

	@Test(priority = 1, enabled = true)
	public void verifyHotelDetailsPageTitle() {
		String actualPageTitle = TestUtils.getPageTitle();
		Assert.assertEquals(actualPageTitle, "Jumeirah Beach Hotel","Page title mismatch");
	}

	@Test(priority=2, enabled = true)
	public void verifyEnteredDetails() {
		validateCheckInDate();
		validateCheckOutDate();
		validateAdultsCount();
	}

	@Test(priority = 3, enabled = true)
	public void bookRoom() {
		hotelDetailsPage.selectRoom();
		hotelDetailsPage.bookRoom();
	}

	public void validateCheckInDate() {
		String actualCheckInDate = hotelDetailsPage.getCheckInDate();
		String checkInDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		if(actualCheckInDate.equals(checkInDate)) {
			Assert.assertTrue(true);
		}
	}

	public void validateCheckOutDate() {
		String actualCheckOutDate = hotelDetailsPage.getCheckOutDate();
		String checkOutDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		if(actualCheckOutDate.equals(checkOutDate)) {
			Assert.assertTrue(true);
		}
	}

	public void validateAdultsCount() {
		String actualCountAdults = hotelDetailsPage.getAdultCount();
		if(actualCountAdults.equals("1")) {
			Assert.assertTrue(true);
		}
	}
	public void validateChildrenCount() {
		String actualCountChildren = hotelDetailsPage.getChildCount();
		if(actualCountChildren.equals("2")) {
			Assert.assertTrue(true);
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}

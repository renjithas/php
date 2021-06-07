package net.phptravels.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import net.phptravels.base.TestBase;
import net.phptravels.utlis.TestUtils;

public class HotelDetailsPage extends TestBase {

	@FindBy(id = "checkin")
	WebElement calendarCheckIn;

	@FindBy(id = "checkout")
	WebElement calendarCheckOut;

	@FindBy(xpath = "//*[@class='input-group  bootstrap-touchspin bootstrap-touchspin-injected']/input[@name='adults']")
	WebElement txtAdults;

	@FindBy(xpath = "//*[@class='input-group  bootstrap-touchspin bootstrap-touchspin-injected']/input[@name='child']")
	WebElement txtChildren;

	@FindBy(xpath = "//*[@class='book_button btn btn-success btn-block btn-lg chk']")
	WebElement btnBookNow;


	public HotelDetailsPage() {
		PageFactory.initElements(driver, this);
	}

	public String getCheckInDate() {
		return calendarCheckIn.getAttribute("value");
	}

	public String getCheckOutDate() {
		return calendarCheckOut.getAttribute("value");
	}

	public String getAdultCount() {
		return txtAdults.getAttribute("value");
	}

	public String getChildCount() {
		return txtChildren.getAttribute("value");
	}

	public void selectRoom(){

		List<WebElement> listRoomPrice = driver.findElements(By.xpath(".//p[@class='price text-center go-text-right']/span"));
		Map<Integer, String> mapRoomPrice = new TreeMap<Integer, String>();
		List<Integer> listIntPrice = new ArrayList<Integer>();

		for(WebElement element: listRoomPrice) {
			String value = element.getText();
			int key = Integer.parseInt(value.replaceAll(",", ""));

			listIntPrice.add(key);
			mapRoomPrice.put(key, value);
		}

		Collections.sort(listIntPrice); //Collections can be used to sort lists; collections cannot be used for maps
		System.out.println(mapRoomPrice.get(listIntPrice.get(0)));

		WebElement chkbxSelectRoom = driver.findElement(By.xpath(".//input[@id='"+mapRoomPrice.get(listIntPrice.get(0))+"']"));
		
		TestUtils.jsExecutorClick(chkbxSelectRoom);
	}
	
	public BookAsGuestPage bookRoom() {
		TestUtils.jsExecutorClick(btnBookNow);
		return new BookAsGuestPage();
	}
}

package net.phptravels.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import net.phptravels.base.TestBase;
import net.phptravels.utlis.TestUtils;

public class HomePage extends TestBase {

	@FindBy(id="dropdownCurrency")
	WebElement dpdnCurrency;

	@FindBy(xpath = ".//a[text()='INR']")
	WebElement linkInr;

	@FindBy(xpath = "//*[@class='dropdown dropdown-currency']/a[@id='dropdownCurrency'and contains(text(),'INR')]")
	public WebElement dpdnInr;

	@FindBy(id="en")
	WebElement linkEnglish;

	@FindBy(xpath = "//*[@class='dropdown dropdown-language']/a[@id='dropdownLangauge']")
	WebElement dpdnLanguage;
	
	@FindBy(xpath = ".//a[@class='text-center hotels active']")
	WebElement btnHotel;

	@FindBy(xpath = "//*[@name='dest']/parent::div/child::div/a")
	WebElement dpdnHotel;
	
	@FindBy(xpath = "//*[@id='select2-drop']/div/input")
	WebElement txtDest;

	@FindBy(id="checkin")
	WebElement txtCheckInDate;

	@FindBy(id="checkout")
	WebElement txtCheckOutDate;
	
	@FindBy(xpath = "//*[@id='flights']/preceding::button[@type='submit']")
	WebElement btnSubmit;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public void selectCurrency() {
		dpdnCurrency.click();
		linkInr.click();
	}

	public String getCurrencyValue() {
		TestUtils.waitForElement(dpdnInr, 3000);
		return driver.findElement(By.xpath("//*[@class='dropdown dropdown-currency']/a[@id='dropdownCurrency']")).getText();
	}

	public void selectLanguage() {
		dpdnLanguage.click();
		linkEnglish.click();
	}

	public HotelDetailsPage selectHotel() {
		btnHotel.click();
		listHotel();
		inputDate();
		adultCount();
		childrenCount();
		btnSubmit.click();
		return new HotelDetailsPage();
	}
	//destination
	public void listHotel() {
		dpdnHotel.click();
			
		List<WebElement> listHotel = driver.findElements(By.xpath(".//ul[@class='select2-result-sub']/li/div"));
		for(int i=0; i<listHotel.size(); i++) {
			System.out.println(listHotel.get(i).getText());
			if(listHotel.get(i).getText().contains(prop.getProperty("hotelName"))){
				listHotel.get(i).click();
				break;
			}
		}
	}

	//input check in and check out date
	public void inputDate() {
		try {
			//check in date
			LocalDate date = LocalDate.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String checkInDate = date.format(format);
			txtCheckInDate.click();
			txtCheckInDate.clear();
			txtCheckInDate.sendKeys(checkInDate);

			date = date.plusDays(1);
			String checkoutDate = date.format(format);
			txtCheckOutDate.click();
			txtCheckOutDate.clear();
			txtCheckOutDate.sendKeys(checkoutDate);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adultCount() {
		int adultCount =1, delta;
		int defaultAdultCount=Integer.parseInt(driver.findElement(By.xpath("//div[@id='hotels']/descendant::input[@name='adults']")).getAttribute("value"));
		WebElement element;
		if(defaultAdultCount>adultCount){
		    delta=defaultAdultCount-adultCount;
		    element=driver.findElement(By.xpath("//div[@id='hotels']/descendant::input[@name='adults']/following-sibling::span/button[@class='btn btn-white bootstrap-touchspin-down ']"));
		}else{
		    delta=adultCount-defaultAdultCount;
		    element=driver.findElement(By.xpath("//div[@id='hotels']/descendant::input[@name='adults']/following-sibling::span/button[@class='btn btn-white bootstrap-touchspin-up ']"));
		}
		for(int i=0;i<delta;i++){
		  element.click();  
		} 
	}
	
	public void childrenCount() {
		int childrenCount =2, diff;
		int defaultChildrenCount=Integer.parseInt(driver.findElement(By.xpath("//*[@name='children']")).getAttribute("value"));
		WebElement element1;
		if(defaultChildrenCount>childrenCount) {
			diff=defaultChildrenCount-childrenCount;
			element1=driver.findElement(By.xpath("//*[@name='children']/parent::div/span/button[@class='btn btn-white bootstrap-touchspin-down ']"));
		}else {
			diff=childrenCount-defaultChildrenCount;
			element1=driver.findElement(By.xpath("//*[@name='children']/parent::div/span/button[@class='btn btn-white bootstrap-touchspin-up ']"));
		}
		for(int i=0; i<diff; i++) {
			element1.click();
		}
	}
	
}

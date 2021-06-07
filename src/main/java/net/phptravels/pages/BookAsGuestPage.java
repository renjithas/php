package net.phptravels.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import net.phptravels.base.TestBase;
import net.phptravels.utlis.TestUtils;

public class BookAsGuestPage extends TestBase{

	@FindBy(id = "guesttab")
	WebElement tabGuest;
	
	@FindBy(name = "firstname")
	WebElement txtFirstName;
	
	@FindBy(name = "lastname")
	WebElement txtLastName;
	
	@FindBy(name = "email")
	WebElement txtEmail;
	
	@FindBy(name = "confirmemail")
	WebElement txtConfirmEmail;
	
	@FindBy(name = "phone")
	WebElement txtPhone;
	
	@FindBy(xpath = "//span[text()='Select Country']")
	WebElement dpdwnCountry;
	
	@FindBy(xpath = ".//button[@type='submit' and @name ='guest']")
	WebElement btnConfirmBooking;
	
	public BookAsGuestPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void selectGuestTab() {
		tabGuest.click();
	}
	
	public void inputName(String firstName, String lastName) {
		txtFirstName.sendKeys(firstName);
		txtLastName.sendKeys(lastName);		
	}
	
	public void inputEmail(String email, String confrimEmail) {
		txtEmail.sendKeys(email);
		txtConfirmEmail.sendKeys(confrimEmail);
	}
	
	public void inputPhoneNumber(String number) {
		txtPhone.sendKeys(number);
	}
	
	public void selectCountry(){
		TestUtils.jsExecutorScrollToView(dpdwnCountry);
		dpdwnCountry.click();
				
		List<WebElement> listCountry = driver.findElements(By.xpath("//*[@class='chosen-drop']/ul/li"));
		for(WebElement element: listCountry) {
			String value = element.getText();
			if(value.equalsIgnoreCase("India")) {
				element.click();
				break;
			}
		}
	}
	
	public void selectExtras() {
		List<WebElement> listExtras = driver.findElements(By.xpath("//tbody/tr/td/label/span/span[text()='Yes']"));
		for(WebElement element: listExtras) {
			TestUtils.jsExecutorClick(element);
		}
	}
	
	public void confirmBooking() {
		TestUtils.jsExecutorScrollToView(btnConfirmBooking);
		btnConfirmBooking.click();
		}
}

package net.phptravels.utlis;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.phptravels.base.TestBase;

public class TestUtils extends TestBase {

	public static int PAGE_LOAD_TIMEOUT = 10;
	public static int IMPLICIT_WAIT = 10;

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");

		FileUtils.copyFile(srcFile, new File(currentDir+"/screenshot/"+ System.currentTimeMillis()+".png"));
	}

	public static String getPageTitle() {
		return driver.getTitle();
	}

	public static void waitForElement(By locator, int timeoutInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public static void waitForElement(WebElement element, int timeoutInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementAndClick(WebElement element, int timeoutInSec) {
		waitForElement(element, timeoutInSec);
		element.click();
	}

	public static boolean checkVisibilityOfElement(WebElement element, int timeoutInSec) {
		try {
			waitForElement(element, timeoutInSec);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void jsExecutorClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void jsExecutorScrollToView(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}

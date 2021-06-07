import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import net.phptravels.base.TestBase;

public class Test extends TestBase{
	public static void main(String[] args) throws InterruptedException {
		
		LocalDate date = LocalDate.now();
		System.out.println(date);
		
		
	}
	
}

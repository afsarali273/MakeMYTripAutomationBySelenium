/**
 * 
 */
package com.assignment.makemytrip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.assignment.makemytrip.utility;

import junit.framework.Assert;

/**
 * @author Afsar
 *
 */
public class MMTFlightBooking {

	public static WebDriver driver;

	@BeforeTest
	public void init(){

		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();


	}
	@AfterTest
	public void teardown(){

		//driver.close();
		//driver.quit();
	}

	@Test
	public void  FlighSearch() throws Throwable{

		/**
		 * @author Afsar
		 * @param driver
		 * @param City- From(Mumbai, India)
		 * @param City -To (New Delhi, India)
		 * @param Date - dd/mm/yyyy format
		 * @param passenger
		 * @return
		 * 
		 */
		driver.get("https://www.makemytrip.com/flights/");
		utility.SearchFunctionality(driver, "Mumbai", "New Delhi", "27/10/2017", "2");

		Thread.sleep(5000);

		// Flight Name and Price Scraping Functionality

		List<WebElement> listFlight=driver.findElements(By.xpath(".//*[@id='content']/div/div[5]/div[5]/div[2]/div/div/div[2]"));

		for(int i=1;i<listFlight.size();i++){

			String FlightName=listFlight.get(i).findElement(By.xpath(".//div[1]/span/span/span[1]")).getText();
			String Price=listFlight.get(i).findElement(By.xpath(".//div[6]/p/span[2]")).getText();

			// Printing Flight Name and Price 
			System.out.println(FlightName+"  "+Price);

		}

	}

	@Test(description=" 2.Test Cases - Write test cases for Coverfox Term Insurance page -"
			+ "3. Test Data - Provide test data to validate an email id field.")
	public void coverfox_Validation(){

		/**
		 * Test Cases :
		 * TC001_To verify that Title of the Page should be displayed as ""
		 * TC002_To verify that the header content is "Take care of your family even when you aren't around. Compare top rated Term plans now"
		 * Tc003_To verify that Bike,CAR,TERM Life section ,Helth, Travel section is available
		 * Tc004_To verify that Male ,Female radio button is present 
		 * Tc005_ to verify that age drop down box is displayed
		 * TC006_To verify that email input box is dispalyed 
		 * 
		 *  Quest-003
		 *  To verify that (3. Test Data - Provide test data to validate an email id field.)
		 *  bool isValidEmail(string emailId)
		 */

		driver.navigate().to("https://www.coverfox.com/term-insurance/");

		try {
			if(driver.getTitle().contains("Term Insurance: Compare Quotes & Calculate Premium Online")){

				System.out.println("Tc001_ Page Title is matching with original : "+driver.getTitle());

			}else {

				System.out.println("Verification failed for Page Title ");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try {
			String headercontent=driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/span/span/span")).getText();

			//Tc002
			Assert.assertEquals("Take care of your family even when you aren't around. Compare top rated Term plans now.", headercontent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<WebElement> list_section=driver.findElements(By.xpath(".//*[@id='content']/div/ul/li"));

			List<String>cha=new ArrayList<String>();
			for(WebElement ele:list_section){

				cha.add(ele.getText());
			}

			if(cha.contains("Bike") && cha.contains("CAR") && cha.remove("Term Life") && cha.contains("Health") && cha.contains("Travel")){

				Assert.assertTrue("Verification passed for TC003", true);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		//======================================================================================================================
		try {
			//Question -03- email validation
			//bool isValidEmail(string emailId)

			Boolean validemail=utility.isValidEmail(driver, "afsar123@gmail.com");

			if(validemail){

				Assert.assertTrue("Email is invalid ", false);
			}else{

				Assert.assertTrue("Email is Valid ", true);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		//====================================================================================================================
	}

}

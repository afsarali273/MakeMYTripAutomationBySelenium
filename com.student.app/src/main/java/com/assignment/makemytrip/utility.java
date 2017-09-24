/**
 * 
 */
package com.assignment.makemytrip;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author Afsar
 *
 */
public class utility {

	//public static WebDriver driver;

	public static void ClickOnElement_byJS(WebDriver driver,WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}

	//This will return Month by Entering numeric Month
	public static String return_month(String EnterMonth){

		String monthreturned=null;
		switch(EnterMonth){

		case "01":
			monthreturned="JANUARY";
			break;
		case "02":
			monthreturned="FEBRUARY";
			break;
		case "03":
			monthreturned="MARCH";
			break;
		case "04":
			monthreturned="ARIL";
			break;
		case "05":
			monthreturned="MAY";
			break;
		case "06":
			monthreturned="JUNE";
			break;
		case "07":
			monthreturned="JULY";
			break;
		case "08":
			monthreturned="AUGUST";
			break;
		case "09":
			monthreturned="SEPTEMBER";
			break;
		case "10":
			monthreturned="OCTOBER";
			break;
		case "11":
			monthreturned="NOVEMBER";
			break;
		case "12":
			monthreturned="DECEMBER";
			break;		
		}

		return monthreturned;

	}


	// Select Month 

	public static void MonthSelect(WebDriver driver,String Month){

		Boolean flag_month=true;
		String montn1=return_month(Month);

		//System.out.println("Month from switch :"+montn1);

		while(flag_month=true){

			String Monthretrived=driver.findElement(By.xpath(".//*[@id='js-filterOptins']/div/div[3]/div/div[1]/div/div/span[1]")).getText();
			//System.out.println("Month returned as "+Monthretrived);

			if(Monthretrived.equals(montn1)){

				System.out.println("Month Matched ..."+Monthretrived);

				break;
			}else {

				//Clicking on the forward arraow

				ClickOnElement_byJS(driver,driver.findElement(By.xpath(".//*[@id='js-filterOptins']/div/div[3]/div/div[2]/div/a/span")));
			}
		}
	}

	public static void select_date(WebDriver driver,String EnterDate){


		WebElement datereturned;
		Boolean date;
		try {
			datereturned = driver.findElement(By.xpath(".//*[@id='js-filterOptins']/div/div[3]/div/div[1]/table/tbody/tr/td/a[contains(text(),'"+EnterDate+"')]"));
			date = driver.findElement(By.xpath(".//*[@id='js-filterOptins']/div/div[3]/div/div[1]/table/tbody/tr/td/a[contains(text(),'"+EnterDate+"')]")).isDisplayed();


			if(date){	
				datereturned.click();
			}else {

				System.out.println("Please select a Valid Date !!!!");

				//throw new ("Please Enter a valid date ...";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Enter Date in format dd/mm/yyyy */
	public static void DateSelection(WebDriver driver,String EnterDate){

		String arr[]=EnterDate.split("/");

		String date=arr[0];
		String month=arr[1];
		String year=arr[2];

		utility.year(driver,year);

		utility.MonthSelect(driver,month);

		utility.select_date(driver, date);
	}


	public static void select_Passenger(WebDriver driver,String EnterPassengers){

		driver.findElement(By.xpath(".//*[@id='js-adult_counter']/li[contains(text(),'"+EnterPassengers+"')]")).click();


	}



	//This will select year 
	public static void year(WebDriver driver,String year){

		Boolean flag_yr=true;

		while(flag_yr==true){

			try {
				String get_year=driver.findElement(By.xpath(".//*[@id='js-filterOptins']/div/div[3]/div/div[1]/div/div/span[2]")).getText();

				if(get_year.equals(year)){

					System.out.println("Year Matched Succesfully..");
					flag_yr=true;
					break;

				}else if(Integer.parseInt(get_year)>Integer.parseInt(year)){//comparing the years 
					//Clicking Back Arrow
					driver.findElement(By.xpath(".//*[@id='js-filterOptins']/div/div[3]/div/div[1]/div/a/span")).click();

					System.out.println("Entered Date is Lesser than Current Date/year..Please try Again..");
					Assert.assertFalse(true, "Entered Date/year is incorrect ...");
					break;
				}else if((Integer.parseInt(get_year)<Integer.parseInt(year))){

					ClickOnElement_byJS(driver,driver.findElement(By.xpath(".//*[@id='js-filterOptins']/div/div[3]/div/div[2]/div/a/span")));
				}
			} catch (NumberFormatException e) {

				e.printStackTrace();
			}
		}
	}
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

	public static void SearchFunctionality(WebDriver driver,String Enter_From,String Enter_To,String EnterDate,String NumberofPassenger){

		//Clicking on the One Way ,we can provide Multicity,Round trip etc..
		driver.findElement(By.xpath(".//*[@id='js-switch__option']/div/label[contains(text(),'one way')]")).click();
		WebElement From=driver.findElement(By.id("hp-widget__sfrom"));
		From.clear();
		From.sendKeys(Enter_From);

		driver.findElement(By.xpath(".//*[@id='js-filterOptins']/div/div/ul/li/div/p/span[contains(text(),'"+Enter_From+"') ]")).click();

		WebElement To=driver.findElement(By.id("hp-widget__sTo"));
		To.clear();
		To.sendKeys(Enter_To);

		driver.findElement(By.xpath(".//*[@class='locationFilter autocomplete_to']/ul/li/div/p/span[contains(text(),'"+Enter_To+"')]")).click();

		utility.DateSelection(driver, EnterDate);

		driver.findElement(By.id("hp-widget__paxCounter")).click();

		utility.select_Passenger(driver,NumberofPassenger);

		driver.findElement(By.xpath(".//*[@id='js-filterOptins']/div/div[9]/div/p/a")).click();

		driver.findElement(By.id("searchBtn")).click();
	}

	public static Boolean isValidEmail(WebDriver driver, String Email){

		Boolean flag=false;

		driver.findElement(By.xpath(".//*[@id='content']/div/div[2]/span/div/div/div[1]/div[3]/div[2]/div/input")).sendKeys(Email);

		driver.findElement(By.xpath(".//*[@id='content']/div/div[2]/span/div/div/div[2]/button")).click();

		List<WebElement> errorPresent=driver.findElements(By.xpath(".//*[@id='content']/div/div[2]/span/div/div/div[1]/div[3]/div[2]/div/div[contains(text(),'Oh. Looks like that email is not valid. Check again?')]"));

		if(!(errorPresent.size()== 0)){

			flag=true;	
		}	
		return flag;

	}



}


package com;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchRepo {

	static WebDriver driver = new FirefoxDriver();
	static WebDriverWait driverWait = new WebDriverWait(driver, 5);
	static String url = "https://github.com";
	static Logger logger = Logger.getLogger("devLogger");
	
	public static void main(String[] args) {
		// search repositories

		try{
			
			String[] searchArray = {"nodejs", "npm", "angularjs", ""};
			
			driver.get(url);
			
			driver.manage().window().maximize();
			
			logger.debug("--------- Search Repository ------------");
			logger.debug("");

			logger.debug("search for loop started");
			for(String search: searchArray){
				searchRepo(search);
			}
			
			driver.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			logger.debug("");
			logger.debug("---- End -----");
			logger.debug("");
			System.exit(0);
		}
		
		
	}

	public static void searchRepo(String search){
		
		try {
            Thread.sleep(3000); //sleep for 3 secs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		WebElement searchBox = driver.findElement(By.name("q"));
		logger.debug("search query entered");
		searchBox.sendKeys(search);
		searchBox.sendKeys(Keys.ENTER);
		
		try {
            Thread.sleep(3000); //sleep for 3 secs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		//check results
		//driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div[1]/h3")));
		List<WebElement> searchResult = driver.findElements(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div[1]/h3"));
		
		if(searchResult.size() > 0){
			
			Iterator<WebElement> iterator = searchResult.iterator();
			
			while(iterator.hasNext()){
				WebElement element = iterator.next();
				logger.debug("fetched results");
				logger.debug("test case passed");
				System.out.println("Query: " + search + " Result: " + element.getText());
				System.out.println("Test case passed");
			}
		}
		else{
			System.out.println("Search query: " + search);
			if(driver.findElements(By.xpath("/html/body/div[4]/div[1]/div[1]/div/h2")).size() > 0){
				System.out.println("Test case passed");
				logger.debug("null values returns no results as expected");
				logger.debug("test case passed");
			}
			else{
				System.out.println("Test case failed");
				logger.debug("test case failed");
			}
			
		}
		
		driver.navigate().back();
	}
}

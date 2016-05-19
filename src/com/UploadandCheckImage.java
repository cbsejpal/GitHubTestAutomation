package com;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadandCheckImage {

	static WebDriver driver = new FirefoxDriver();
	static WebDriverWait driverWait = new WebDriverWait(driver, 5);
	static String url = "https://github.com/login";
	static Logger logger = Logger.getLogger("devLogger");
	
	public static void main(String[] args) {
		//Upload Image and Check image
		
		logger.debug("--------- Upload and Check Image ------------");
		logger.debug("");
		
		try{
			
			driver.get(url);
			
			driver.manage().window().maximize();
			
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
			WebElement email = driver.findElement(By.id("login_field"));
			logger.debug("email id entered");
			email.sendKeys("sample.10981@gmail.com");
			
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
			WebElement pass = driver.findElement(By.id("password"));
			logger.debug("password entered");
			pass.sendKeys("hahalol123");
			
			WebElement submitButton = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/form/div[4]/input[3]"));

			logger.debug("submit button clicked");
			submitButton.click();
			
			try {
	            Thread.sleep(3000); //sleep for 3 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			uploadImage();
			
			checkImages();
			
			driver.quit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			logger.debug("");
			logger.debug("---- End -----");
			logger.debug("");
			System.exit(0);
		}

		
	}

	public static void uploadImage(){
		if(driver.getCurrentUrl().equalsIgnoreCase("https://github.com/")){
			
			System.out.println("Log in successful"); 
			logger.debug("log in successful");
			
			//go to settings
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/a")));
			driver.findElement(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/a")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/div/div/a[6]")).click();
			
			try {
	            Thread.sleep(3000); //sleep for 3 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			//upload picture
			WebElement uploadPicture = driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div/div[2]/div[1]/div/form/div[2]/dl[1]/dd/div/a/label/input"));
		
			logger.debug("upload picture");
			uploadPicture.sendKeys(System.getProperty("user.dir") + "\\images\\samplePicture.jpg");
			
			if(driver.findElement(By.xpath("/html/body/div[9]/div/div/form/div[3]")).isDisplayed()){
				driver.findElement(By.xpath("/html/body/div[9]/div/div/form/div[3]/button")).click();
				System.out.println("Image upload successful");
				logger.debug("upload successful");
				
				//go to profile
				driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/a")));
				driver.findElement(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/a")).click();
				driver.findElement(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/div/div/a[1]")).click();
				logger.debug("check image");
				
				try {
		            Thread.sleep(3000); //sleep for 3 secs
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
				
				//check image is displayed or not
				if(driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div/div[1]/a/img")).isDisplayed()){
					System.out.println("Image display success");
					
					WebElement image = driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div/div[1]/a/img"));
					
					if(image.getAttribute("alt").length() > 0){
						System.out.println("Alt attribute is set");
					}
					else{
						System.out.println("Alt attribute is not set");
					}
					
					logger.debug("image display success");
					System.out.println("Test case passed");
					logger.debug("test case passed");
				}
				else{
					System.out.println("Test case failed");
					logger.debug("test case failed");
				}
				
			}
			else{
				System.out.println("Upload picture failed");
				logger.debug("upload picture failed");
				System.out.println("Test case failed");
				logger.debug("test case failed");
			}
		}
		else{
			System.out.println("Login failed");
			logger.debug("login failed");
		}
	}
	
	private static void checkImages() {

		System.out.println("");
		
		String shopUrl = "https://github.myshopify.com/";
		
		logger.debug("check images started");
		
		driver.get(shopUrl);
		
		try {
            Thread.sleep(3000); //sleep for 3 secs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		List<WebElement> list = driver.findElements(By.tagName("img"));
		
		if(list.size() > 0){
			
			Iterator<WebElement> iterator = list.iterator();
			
			while(iterator.hasNext()){
				WebElement element = iterator.next();

				if(element.getAttribute("alt").length()>0){
					System.out.println("Alt attribute is set " + element.getAttribute("alt"));
				}
				else{
					System.out.println("Alt attribute is not set");
				}
			}
			logger.debug("test case passed");
			System.out.println("Test case passed");
		}
		else{
			System.out.println("No images found");
		}
	}
}

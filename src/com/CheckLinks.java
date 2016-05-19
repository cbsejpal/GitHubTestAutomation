package com;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckLinks {

	static WebDriver driver = new FirefoxDriver();
	static WebDriverWait driverWait = new WebDriverWait(driver, 5);
	static String url = "https://github.com/login";
	static Logger logger = Logger.getLogger("devLogger");

	public static void main(String[] args) {
		//check all links - Footer, Header
		
		try{
			
			driver.get(url);
			
			driver.manage().window().maximize();
			
			logger.debug("--------- Check Links ------------");
			logger.debug("");
			
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
			
			checkLinks();
			
			driver.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			logger.debug("");
			logger.debug("---- End -----");
			logger.debug("");
			System.exit(0);
		}

		
		//navigate().back()
	}

	public static void checkLinks(){
		
		if(driver.getCurrentUrl().equalsIgnoreCase("https://github.com/")){
			
			System.out.println("Log in successful \n");
			logger.debug("Log in successful");
			//check header links
			
			WebElement pullLink = driver.findElement(By.linkText("Pull requests"));
			String link = pullLink.getAttribute("href");
			
			pullLink.click();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			if(link.equalsIgnoreCase(driver.getCurrentUrl())){
				System.out.println("Pull request link is same");
			}
			else{
				System.out.println("Pull request link is different");
			}
			
			driver.navigate().back();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			WebElement issuesLink = driver.findElement(By.linkText("Issues"));
			link = issuesLink.getAttribute("href");
			
			issuesLink.click();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			if(link.equalsIgnoreCase(driver.getCurrentUrl())){
				System.out.println("Issues link is same");
			}
			else{
				System.out.println("Issues link is different");
			}
			
			driver.navigate().back();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			WebElement gistLink = driver.findElement(By.linkText("Gist"));
			link = gistLink.getAttribute("href");
			
			
			gistLink.click();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			if(link.equalsIgnoreCase(driver.getCurrentUrl())){
				System.out.println("Gist link is same");
			}
			else{
				System.out.println("Gist link is different");
			}
			
			driver.navigate().back();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			//check footer links
			
			WebElement termsLink = driver.findElement(By.linkText("Terms"));
			link = termsLink.getAttribute("href");
			
			termsLink.click();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			if(link.equalsIgnoreCase(driver.getCurrentUrl())){
				System.out.println("Terms link is same");
			}
			else{
				System.out.println("Terms link is different");
			}
			
			driver.navigate().back();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			WebElement privacyLink = driver.findElement(By.linkText("Privacy"));
			link = privacyLink.getAttribute("href");
			
			privacyLink.click();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			if(link.equalsIgnoreCase(driver.getCurrentUrl())){
				System.out.println("Privacy link is same");
			}
			else{
				System.out.println("Privacy link is different");
			}
			
			driver.navigate().back();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			WebElement securityLink = driver.findElement(By.linkText("Security"));
			link = securityLink.getAttribute("href");
			
			securityLink.click();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			if(link.equalsIgnoreCase(driver.getCurrentUrl())){
				System.out.println("Security link is same");
			}
			else{
				System.out.println("Security link is different");
			}
			
			driver.navigate().back();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			WebElement contactLink = driver.findElement(By.linkText("Contact"));
			link = contactLink.getAttribute("href");
			
			contactLink.click();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			if(link.equalsIgnoreCase(driver.getCurrentUrl())){
				System.out.println("Contact link is same");
			}
			else{
				System.out.println("Contact link is different");
			}
			
			driver.navigate().back();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			WebElement helpLink = driver.findElement(By.linkText("Help"));
			link = helpLink.getAttribute("href");
			
			helpLink.click();
			
			try {
	            Thread.sleep(2000); //sleep for 2 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			if(link.equalsIgnoreCase(driver.getCurrentUrl())){
				System.out.println("Help link is same");
			}
			else{
				System.out.println("Help link is different");
			}
			
			driver.navigate().back();
			
			System.out.println();
			System.out.println("Test case passed");
			logger.debug("Test case passed");
			
			driver.close();
			
		}
		else{
			System.out.println("Login failed");
			logger.debug("login failed");
			driver.close();
		}
	}
}

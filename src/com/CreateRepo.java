package com;

import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateRepo {

	static WebDriver driver = new FirefoxDriver();
	static WebDriverWait driverWait = new WebDriverWait(driver, 5);
	static String url = "https://github.com/login";
	static Logger logger = Logger.getLogger("devLogger");
	
	public static void main(String[] args) {
		//dropdown, radio button, checkbox
		//random data generation
		
		try{
			
			driver.get(url);
			
			driver.manage().window().maximize();
			
			logger.debug("--------- Create Repository ------------");
			logger.debug("");
			
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
			WebElement email = driver.findElement(By.id("login_field"));
			logger.debug("Email id entered");
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
			
			Random random = new Random();

			logger.debug("for loop started for creating repos");

			String[] reponames = {"ab", "cd", "de", "ef"};
			
            for (int i = 0; i <reponames.length ; i++) {

                String reponame = random.nextInt(500) + reponames[i] + random.nextInt(500);
				createRepo(reponame);
            }
						
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
		
	}
	
	public static void createRepo(String reponame){
		
		driver.get(url);
		
		driver.manage().window().maximize();
		
		if(driver.getCurrentUrl().equalsIgnoreCase("https://github.com/")){
		
			System.out.println("Log in successful"); 
			logger.debug("Log in successful");
			
			//Click on New repository to create new repo
			driver.findElement(By.linkText("New repository")).click();
			logger.debug("create new repo");
			
			try {
	            Thread.sleep(3000); //sleep for 3 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			WebElement repoName = driver.findElement(By.id("repository_name"));
			repoName.sendKeys(reponame);
			
			WebElement repoDesc = driver.findElement(By.id("repository_description"));
			repoDesc.sendKeys("Sample description");
			
			try {
	            Thread.sleep(1000); //sleep for 1 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			//select private radio button for testing
			driver.findElement(By.id("repository_public_false")).click();
			logger.debug("radio button clicked");
			
			System.out.println("Public selected? " + driver.findElement(By.id("repository_public_true")).isSelected());
			System.out.println("Private selected? " + driver.findElement(By.id("repository_public_false")).isSelected());
			
			try {
	            Thread.sleep(1000); //sleep for 1 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			//now select public radio button
			driver.findElement(By.id("repository_public_true")).click();
			
			System.out.println("Public selected? " + driver.findElement(By.id("repository_public_true")).isSelected());
			System.out.println("Private selected? " + driver.findElement(By.id("repository_public_false")).isSelected());
			
			try {
	            Thread.sleep(1000); //sleep for 1 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			//check auto readme checkbox
			WebElement checkbox = driver.findElement(By.id("repository_auto_init"));
			checkbox.click();
			logger.debug("checbox clicked");
			
			try {
	            Thread.sleep(1000); //sleep for 1 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			//select dropdown1
			driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/form/div[4]/ul/li[1]/div/button")).click();
			driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/form/div[4]/ul/li[1]/div/div/div/div[2]/div/input")).sendKeys("Java");
			driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/form/div[4]/ul/li[1]/div/div/div/div[3]/div[1]/div[52]")).click();
			logger.debug("dropdown 1 selected");
			
			try {
	            Thread.sleep(1000); //sleep for 1 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			//select dropdown2
			driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/form/div[4]/ul/li[2]/div/button")).click();
			driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/form/div[4]/ul/li[2]/div/div/div/div[2]/div/input")).sendKeys("creative");
			driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/form/div[4]/ul/li[2]/div/div/div/div[3]/div[1]")).click();
			logger.debug("dropdown 2 selected");
			
			try {
	            Thread.sleep(1000); //sleep for 1 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			//create repository
			driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/form/div[4]/button")).click();
			
			try {
	            Thread.sleep(3000); //sleep for 3 secs
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
			if(driver.getCurrentUrl().equalsIgnoreCase("https://github.com/repositories")){
				System.out.println("Repo cannot be created with previous same name");
				System.out.println("Test case passed with repo name: " + reponame);
				System.out.println();
				logger.debug("same name repo cannot be created");
				logger.debug("test case passed");
			}
			
			else if(driver.findElements(By.linkText("Initial commit")).size() > 0){
				System.out.println("Repo created successfully");
				System.out.println("Test case passed with repo name: " + reponame);
				System.out.println();
				logger.debug("new repo created");
				logger.debug("test case passed");
				
			}
			else{
				System.out.println("Test case failed");
				logger.debug("test case failed");
			}

		}
		else{
			System.out.println("Login failed");
			logger.debug("login failed");
			driver.close();
		}
		
		
		
	}

}

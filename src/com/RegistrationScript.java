package com;

import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationScript {

	static WebDriver driver = new FirefoxDriver();
	static WebDriverWait driverWait = new WebDriverWait(driver, 5);
	static String url = "https://github.com";
	static Logger logger = Logger.getLogger("devLogger");
	
	public static void main(String[] args) {
		
		//random data generation
		
		logger.debug("--------- Registration Script ------------");
		logger.debug("");
		
		try{
	
			String[] usernames = {"sample.10981", "sample-123zeta", "sample1234"};
			String[] emails = {"sample.10981", "sample-123zeta", "sample"};
			String[] passwords = {"hahalol123", "hahalol123", "sample"};
			
			logger.debug("for loop started for registration");
			//our normal data
			for(int i=0;i<usernames.length; i++){
				register(usernames[i], emails[i]+"@gmail.com", passwords[i]);
			}
			
			//random data
			Random random = new Random();

			logger.debug("for loop started for registering");
			
            for (int i = 0; i <usernames.length ; i++) {

                String uname = random.nextInt(500) + usernames[i] + random.nextInt(500);
                String pwd = random.nextInt(500) + passwords[i] + random.nextInt(500);
                String email = random.nextInt(500) + emails[i] + random.nextInt(500);
				register(uname, email+"@gmail.com", pwd);
            }
			
			
			driver.quit();
			

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			logger.debug("");
			logger.debug("---- End -----");
			logger.debug("");
			System.exit(0);
		}

	}
	
	public static void register(String username, String email, String password){
		
		driver.get(url);
		
		driver.manage().window().maximize();
		
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("user[login]")));
		WebElement userName = driver.findElement(By.name("user[login]"));
		logger.debug("username entered");
		userName.sendKeys(username);
		
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("user[email]")));
		WebElement emailId = driver.findElement(By.name("user[email]"));
		logger.debug("email id entered");
		emailId.sendKeys(email);
		
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("user[password]")));
		WebElement pass = driver.findElement(By.name("user[password]"));
		logger.debug("password entered");
		pass.sendKeys(password);
		
		WebElement submitButton = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div[2]/div[1]/form/button"));
		
		logger.debug("submit button clicked");
		submitButton.click();
		
		try {
            Thread.sleep(5000); //sleep for 5secs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		if(driver.getCurrentUrl().equalsIgnoreCase("https://github.com/join/plan")){
			
			System.out.println("Registration successful");
			logger.debug("registration successful");
			
			//continue
			driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/form/button")).click();
			
			if(driver.getCurrentUrl().equalsIgnoreCase("https://github.com/join/customize")){
				driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/form/a")).click();
				
				if(driver.getCurrentUrl().equalsIgnoreCase("https://github.com/dashboard")){
					logger.debug("test case passed");
					System.out.print("Test Case passed with ");
					System.out.println("Username: " + username + " password: " + password + " email: " + email);
					
					driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/a")));
					WebElement logoutDiv = driver.findElement(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/a"));
					
					logoutDiv.click();
					
					//sign out link
					driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/div/div/form/button")));
					driver.findElement(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/div/div/form/button")).click();
					System.out.println("Log out successfull");
					logger.debug("log out successful");
				}
				else{
					//if there is some error
					System.out.println("Test Case failed");
					logger.debug("test case failed");
				}
			}
			else{
				//if there is some error
				System.out.println("Test Case failed");
				logger.debug("test case failed");
			}
		}
		else{
			System.out.println("Registration failed");
			logger.debug("registration failed");
			
			if(driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div[2]/div/form/div[2]")).isDisplayed()){
				logger.debug("errors while registering. Cannot register with invalid values");
				logger.debug("test case passed");
				System.out.print("Test case passed with ");
				System.out.println("Username: " + username + " password: " + password + " email: " + email);
			}
			else{
				logger.debug("test case failed");
				System.out.println("Test case failed");
			}
		}
		
	}

}

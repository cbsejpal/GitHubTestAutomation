package com;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginScript {

	static WebDriver driver = new FirefoxDriver();
	static WebDriverWait driverWait = new WebDriverWait(driver, 5);
	static String url = "https://github.com/login";
	static Logger logger = Logger.getLogger("devLogger");
	static String Path_TestData = System.getProperty("user.dir") + "\\data\\automation_sheet.xlsx";
	static String File_TestData = "automation_sheet.xlsx";
	
	public static void main(String[] args) {

		logger.debug("--------- Login Script ------------");
		logger.debug("");
		
		try{
			
			String[] usernames = {"sample.10981@gmail.com", "cbsejpal@gmail.com", "sample@gmail.com"};
			String[] passwords = {"hahalol123", "haha", "sample"};
			
			logger.debug("for loop for login started");
			/*for(int i=0;i<usernames.length; i++){
				login(usernames[i], passwords[i]);
			}*/
			
			ExcelUtils.setExcelFile(Path_TestData, File_TestData);
    		
    		String uname = ExcelUtils.getCellData(2, 2);

            String pwd = ExcelUtils.getCellData(2, 3);
            
            System.out.println("uname " + uname +  "pwd " + pwd);
			
            
			driver.quit();
			
		}catch(Exception e){
			System.out.println(e);
		}
		finally{
			logger.debug("");
			logger.debug("---- End -----");
			logger.debug("");
			System.exit(0);
		}

		
	}
	
	public static void login(String username, String password){
	
		driver.get(url);
		
		driver.manage().window().maximize();
		
		if(driver.getTitle().length() > 0){
			System.out.println("Page Title is: " + driver.getTitle());
		}
		else{
			System.out.println("Title not set");
		}
				
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));
		WebElement email = driver.findElement(By.id("userid"));
		logger.debug("email id entered");
		email.sendKeys(username);
		
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pwd")));
		WebElement pass = driver.findElement(By.id("pwd"));
		logger.debug("password entered");
		pass.sendKeys(password);
		
		WebElement submitButton = driver.findElement(By.xpath("/html/body/div/form/div/div/div[7]/input"));
				///html/body/div[4]/div[1]/div/form/div[4]/input[3]"));

		logger.debug("submit button clicked");
		submitButton.click();
		
		try {
            Thread.sleep(5000); //sleep for 5secs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		if(driver.getCurrentUrl().equalsIgnoreCase("https://github.com/")){

			System.out.println("Login Successful with username: "+username + " password: " + password);
			logger.debug("login successful");
			
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/a")));
			WebElement logoutDiv = driver.findElement(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/a"));
			
			logoutDiv.click();
			logger.debug("log out clicked");
			
			//sign out link
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/div/div/form/button")));
			driver.findElement(By.xpath("/html/body/div[2]/div/ul[2]/li[3]/div/div/form/button")).click();
			System.out.println("Log out successfull");
			logger.debug("log out successful");
			logger.debug("test case passed");
			System.out.println("Test Case passed");
			
		}
		else{
			
			System.out.println("Login failed with username: "+username + " password: " + password);
			logger.debug("login failed");
			
			WebElement element = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/form/div[3]/div/div"));
			
			
			//check if error message div appeared or not
			if(element.isDisplayed()){
				System.out.println("Test Case passed");
				System.out.println("Error Message: " + element.getText());
				
				logger.debug("Not able to log in with incorrect credentials");
				logger.debug("test case passed");
			}
			else{
				System.out.println("Test Case failed");
				logger.debug("test case failed");
			}
			
		}
	}

}

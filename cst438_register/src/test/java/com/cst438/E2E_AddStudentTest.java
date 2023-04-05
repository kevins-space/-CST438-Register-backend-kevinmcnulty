

	
	package com.cst438;

	import static org.junit.jupiter.api.Assertions.assertEquals;
	import static org.junit.jupiter.api.Assertions.assertNotNull;
	import static org.junit.jupiter.api.Assertions.assertTrue;

	import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

	import org.junit.jupiter.api.Test;
	import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.domain.StudentRepository;

	/*
	 * This example shows how to use selenium testing using the web driver 
	 * with Chrome browser.
	 * 
	 *  - Buttons, input, and anchor elements are located using XPATH expression.
	 *  - onClick( ) method is used with buttons and anchor tags.
	 *  - Input fields are located and sendKeys( ) method is used to enter test data.
	 *  - Spring Boot JPA is used to initialize, verify and reset the database before
	 *      and after testing.
	 *      
	 *    Make sure that TEST_COURSE_ID is a valid course for TEST_SEMESTER.
	 *    
	 *    URL is the server on which Node.js is running.
	 */

@SpringBootTest
public class E2E_AddStudentTest {

    public static final int SLEEP_DURATION = 3000;
    private WebDriver driver;

    @Test
    void testAddStudent() throws InterruptedException {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        // Puts an Implicit wait for 10 seconds before throwing exception
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            // Navigate to the web page
            driver.get("http://localhost:3000");
            Thread.sleep(SLEEP_DURATION);

            // Click the Add Student button
            WebElement addButton = driver.findElement(By.xpath("//a[@href='/add-student']"));
            addButton.click();
            Thread.sleep(SLEEP_DURATION);
           
           
            driver.findElement(By.xpath("//button[@id='x']")).click();

            // Wait for the Add Student dialog to appear
         

            // Fill in the form fields
            WebElement nameField = driver.findElement(By.cssSelector("input[name='name']"));
            WebElement emailField = driver.findElement(By.name("email"));
            WebElement addButtonInDialog = driver.findElement(By.id("Add"));
            
            nameField.sendKeys("");
            emailField.sendKeys("");

            addButtonInDialog.click();
            Thread.sleep(SLEEP_DURATION);
            
            WebElement toast3 = waitForElement(By.xpath("//div[@class='Toastify__toast-body']"), 12);
            assertNotNull(toast3);
            assertTrue(toast3.getText().contains("Please fill out all fields"));
            Thread.sleep(SLEEP_DURATION);
            
            nameField.sendKeys("test");
            emailField.sendKeys("test@csumb.edu");

            addButtonInDialog.click();
            Thread.sleep(SLEEP_DURATION);

            WebElement toast2 = waitForElement(By.xpath("//div[@class='Toastify__toast-body']"), 12);
            assertTrue(toast2.getText().contains("Failed to add student"));
            
            nameField.clear();
            nameField.sendKeys("jason");
            Thread.sleep(SLEEP_DURATION);

            emailField.clear();
            emailField.sendKeys("jtgolfing24@gmail.com");
            Thread.sleep(SLEEP_DURATION);

            addButtonInDialog.click();
            Thread.sleep(SLEEP_DURATION);

            WebElement toast = waitForElement(By.xpath("//div[@class='Toastify__toast-body']"), 12);
            assertNotNull(toast);
            assertTrue(toast.getText().contains("Student added successfully"));
            Thread.sleep(SLEEP_DURATION);
          
           
            
        } catch (Exception ex) {
            throw ex;
        } finally {
            driver.quit();
        }
    }

		    private WebElement waitForElement(By locator, int timeoutSeconds) throws InterruptedException {
		        WebElement element = null;
		        int timeoutMillis = timeoutSeconds * 1000;
		        int pollIntervalMillis = 100;

		        long startTimeMillis = System.currentTimeMillis();
		        while (System.currentTimeMillis() < startTimeMillis + timeoutMillis) {
		            try {
		                element = driver.findElement(locator);
		                if (element.isDisplayed()) {
		                    return element;
		                }
		            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException ignored) {}

		            Thread.sleep(pollIntervalMillis);
		        }

		        return null;
		    }
		}

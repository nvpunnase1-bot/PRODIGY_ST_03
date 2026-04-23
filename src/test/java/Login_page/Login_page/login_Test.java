package Login_page.Login_page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class login_Test 
{
	WebDriver driver;

    @BeforeMethod
    public void setup() 
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        
    }
    //(Valid Login)
    @Test(priority=1)
    public void validLoginTest() 
   {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click(); 
        
        String expectedTitle="Swag Labs";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Login sucessfully");
    }

    //(Invalid Login)
    @Test(priority=2)
    public void invalidLoginTest() 
    {
        driver.findElement(By.id("user-name")).sendKeys("Username_Wrong");
        driver.findElement(By.id("password")).sendKeys("Password_Wrong");
        driver.findElement(By.id("login-button")).click();
        
        boolean isErrorDisplayed =driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();
        Assert.assertTrue(isErrorDisplayed,"massage not displayed!");
    }

    //(Empty Fields)
    @Test(priority=3)
    public void emptyFieldsTest() 
    {
    	driver.findElement(By.id("login-button")).click(); 
    	
      System.out.println(driver.findElement(By.xpath("//h3[@data-test='error']")).getText());
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
 }



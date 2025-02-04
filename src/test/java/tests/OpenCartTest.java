package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.Assert;
import pages.*;
import java.util.UUID;

public class OpenCartTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void setUp() {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--ignore-certificate-errors"); 
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://opencart.abstracta.us/");
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void testUserRegistration() {
        String randomEmail = "test_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        
        RegisterPage registerPage = homePage.navigateToRegisterPage();
        registerPage.registerUser("Test", "User", randomEmail, "1234567890", "Test@123");
        
        Assert.assertEquals(registerPage.getSuccessMessage(), 
                          "Account");
    }

    @Test(priority = 2)
    public void testProductSearch() {
        SearchResultPage searchResultPage = homePage.searchProduct("iPhone");
        Assert.assertTrue(searchResultPage.hasSearchResults(), 
                        "No products found in search results");
    }

    @Test(priority = 3)
    public void testAddToCart() {
        SearchResultPage searchResultPage = homePage.searchProduct("MacBook");
        searchResultPage.addFirstProductToCart();
        
        Assert.assertTrue(searchResultPage.getSuccessMessage()
                        .contains("Success: You have added"));
        Assert.assertTrue(searchResultPage.getCartTotal()
                        .contains("1 item(s)"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
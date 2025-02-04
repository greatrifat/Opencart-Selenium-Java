package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.Assert;
import pages.*;
import java.util.UUID;

public class ShoppingCartTest {
    private WebDriver driver;
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private String testEmail;
    private String testPassword;

    @BeforeClass
    public void setUp() {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--ignore-certificate-errors"); 
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://opencart.abstracta.us/");
        homePage = new HomePage(driver);
        
        // Create test account for checkout
        testEmail = "test_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        testPassword = "Test@123";
        RegisterPage registerPage = homePage.navigateToRegisterPage();
        registerPage.registerUser("Test", "User", testEmail, "1234567890", testPassword);
    }

    @BeforeMethod
    public void addProductToCart() {
        // Add a product to cart before each test
        SearchResultPage searchResultPage = homePage.searchProduct("iPhone");
        searchResultPage.addFirstProductToCart();
        // Wait for cart to update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void testUpdateCartQuantity() {
        cartPage = new CartPage(driver);
        cartPage.updateQuantity(0, 2);
        
        Assert.assertTrue(cartPage.getSuccessMessage()
            .contains("Success: You have modified your shopping cart!"));
        Assert.assertTrue(cartPage.getCartTotal()
            .contains("2 item(s)"));
    }

    @Test(priority = 2)
    public void testRemoveItemFromCart() {
        cartPage = new CartPage(driver);
        int initialCount = cartPage.getCartItemsCount();
        cartPage.removeItem(0);
        
        Assert.assertEquals(cartPage.getCartItemsCount(), initialCount - 1);
    }

    @Test(priority = 3)
    public void testCompleteCheckoutProcess() {
        cartPage = new CartPage(driver);
        checkoutPage = cartPage.proceedToCheckout();
        
        checkoutPage.loginDuringCheckout(testEmail, testPassword);
        checkoutPage.completeCheckout();
        
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), 
            "Your order has been placed!");
    }

    @AfterMethod
    public void navigateHome() {
        driver.get("http://opencart.abstracta.us/");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
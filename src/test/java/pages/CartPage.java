package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends BasePage {
    @FindBy(css = ".table-responsive tbody tr")
    private List<WebElement> cartItems;

    @FindBy(css = "input[name^='quantity']")
    private List<WebElement> quantityInputs;

    @FindBy(css = "button[data-original-title='Update']")
    private List<WebElement> updateButtons;

    @FindBy(css = "button[data-original-title='Remove']")
    private List<WebElement> removeButtons;

    @FindBy(xpath = "//strong[contains(text(),'Checkout')]/..")
    private WebElement checkoutButton;

    @FindBy(css = "#content > h1")
    private WebElement pageTitle;

    @FindBy(className = "alert-success")
    private WebElement successMessage;

    @FindBy(css = ".table-responsive tfoot")
    private WebElement cartTotal;
    
    @FindBy(id = "cart")
    private WebElement cartDropdown;
    
 

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void updateQuantity(int itemIndex, int quantity) {
        waitForElementToBeVisible(quantityInputs.get(itemIndex));
        quantityInputs.get(itemIndex).clear();
        quantityInputs.get(itemIndex).sendKeys(String.valueOf(quantity));
        updateButtons.get(itemIndex).click();
    }

    public void removeItem(int itemIndex) {
        waitForElementToBeClickable(removeButtons.get(itemIndex));
        removeButtons.get(itemIndex).click();
    }

    public CheckoutPage proceedToCheckout() {
    	waitForElementToBeClickable(cartDropdown);
        cartDropdown.click();
        
        // Wait for drop down animation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementToBeClickable(checkoutButton);
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

    public int getCartItemsCount() {
        return cartItems.size();
    }

    public String getSuccessMessage() {
        waitForElementToBeVisible(successMessage);
        return successMessage.getText();
    }

    public String getCartTotal() {
        waitForElementToBeVisible(cartTotal);
        return cartTotal.getText();
    }
}
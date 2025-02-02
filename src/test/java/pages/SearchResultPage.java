package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class SearchResultPage extends BasePage {
    @FindBy(className = "product-thumb")
    private List<WebElement> productThumbnails;

    @FindBy(css = "[onclick*='cart.add']")
    private WebElement addToCartButton;

    @FindBy(className = "alert-success")
    private WebElement successAlert;

    @FindBy(id = "cart-total")
    private WebElement cartTotal;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasSearchResults() {
        return !productThumbnails.isEmpty();
    }

    public void addFirstProductToCart() {
        waitForElementToBeClickable(addToCartButton);
        addToCartButton.click();
    }

    public String getSuccessMessage() {
        waitForElementToBeVisible(successAlert);
        return successAlert.getText();
    }

    public String getCartTotal() {
        waitForElementToBeVisible(cartTotal);
        return cartTotal.getText();
    }
}
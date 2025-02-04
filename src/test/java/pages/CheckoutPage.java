package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(id = "button-login")
    private WebElement loginButton;

    @FindBy(id = "button-payment-address")
    private WebElement paymentAddressButton;

    @FindBy(id = "button-shipping-address")
    private WebElement shippingAddressButton;

    @FindBy(id = "button-shipping-method")
    private WebElement shippingMethodButton;

    @FindBy(name = "agree")
    private WebElement termsCheckbox;

    @FindBy(id = "button-payment-method")
    private WebElement paymentMethodButton;

    @FindBy(id = "button-confirm")
    private WebElement confirmOrderButton;

    @FindBy(css = "#content h1")
    private WebElement confirmationMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void loginDuringCheckout(String email, String password) {
        waitForElementToBeVisible(emailInput);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void completeCheckout() {
        waitForElementToBeClickable(paymentAddressButton);
        paymentAddressButton.click();

        waitForElementToBeClickable(shippingAddressButton);
        shippingAddressButton.click();

        waitForElementToBeClickable(shippingMethodButton);
        shippingMethodButton.click();

        waitForElementToBeClickable(termsCheckbox);
        termsCheckbox.click();
        paymentMethodButton.click();

        waitForElementToBeClickable(confirmOrderButton);
        confirmOrderButton.click();
    }

    public String getConfirmationMessage() {
        waitForElementToBeVisible(confirmationMessage);
        return confirmationMessage.getText();
    }
}
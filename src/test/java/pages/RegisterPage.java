package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
    @FindBy(id = "input-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "input-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-telephone")
    private WebElement telephoneInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(id = "input-confirm")
    private WebElement confirmPasswordInput;

    @FindBy(name = "agree")
    private WebElement privacyPolicyCheckbox;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(css = "#content h1")
    private WebElement successMessage;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void registerUser(String firstName, String lastName, String email, 
                           String telephone, String password) {
        waitForElementToBeVisible(firstNameInput);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        telephoneInput.sendKeys(telephone);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        privacyPolicyCheckbox.click();
        continueButton.click();
    }

    public String getSuccessMessage() {
        waitForElementToBeVisible(successMessage);
        return successMessage.getText();
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropdown;

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(name = "search")
    private WebElement searchBox;

    @FindBy(css = ".btn-default")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage navigateToRegisterPage() {
        waitForElementToBeClickable(myAccountDropdown);
        myAccountDropdown.click();
        waitForElementToBeClickable(registerLink);
        registerLink.click();
        return new RegisterPage(driver);
    }

    public SearchResultPage searchProduct(String productName) {
        waitForElementToBeVisible(searchBox);
        searchBox.clear();
        searchBox.sendKeys(productName);
        searchButton.click();
        return new SearchResultPage(driver);
    }
}
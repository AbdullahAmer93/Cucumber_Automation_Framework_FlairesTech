package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Global_Vars;

public class Login_PO extends Base_PO{

    private @FindBy(css = "[name='username']") WebElement usernameElement;
    private @FindBy(css = "[type='password']") WebElement passwordElement;
    private @FindBy(css = ".orangehrm-login-button") WebElement loginButtonElement;

    public Login_PO() {
        super();
    }

    public void navigate_Login_Page(){
        navigateTo_URL(Global_Vars.WEBDRIVER_HOMEPAGE_URL);
    }

    public void setUsername_textField(String usernameText) {
        sendKeys(usernameElement, usernameText);
    }

    public void setPassword_textField(String passwordText) {
        sendKeys(passwordElement, passwordText);
    }

    public void clickLoginButton() {
        waitForWebElementAndClick(loginButtonElement);
    }
}

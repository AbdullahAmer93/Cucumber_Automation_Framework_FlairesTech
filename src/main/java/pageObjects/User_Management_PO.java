package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Global_Vars;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User_Management_PO extends Base_PO {

    public User_Management_PO() {
        super();
    }

    @FindBy(css = ".orangehrm-horizontal-padding .oxd-text--span")
    private WebElement records;

    @FindBy(css = ".orangehrm-header-container .oxd-button--secondary")
    private WebElement addButton;

    @FindBy(css = ".oxd-form .oxd-grid-item--gutters:nth-of-type(4) .oxd-input-field-bottom-space div:nth-of-type(2)")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(name = "userRole")
    private WebElement userRoleDropdown;

    @FindBy(name = "employeeName")
    private WebElement employeeNameField;

    @FindBy(name = "status")
    private WebElement statusDropdown;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    @FindBy(name = "searchSystemUser[userName]")
    private WebElement searchUsernameField;

    @FindBy(css = "button[type='button'][name='search']")
    private WebElement searchButton;

    @FindBy(xpath = "//tr/td[2][contains(text(),'No Records Found')]")
    private WebElement noRecordsFoundMessage;

    public int extractNumberFromElement() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(records));
        String elementText = records.getText();
        // Regular expression to extract the number inside parentheses
        String regex = "\\((\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(elementText);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalArgumentException("No number found in parentheses");
        }
    }

    public void printNumber() {
        System.out.println(extractNumberFromElement());
    }

    public void clickAddButton() {
        waitForWebElementAndClick(addButton);
    }
    public void selectFromDropdown(WebElement dropdownElement, String value) {
        // Wait for the dropdown to be visible
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(dropdownElement));

        // Create a Select object to interact with the dropdown
        Select select = new Select(dropdownElement);

        // Select the option by visible text
        select.selectByVisibleText(value);
    }

    public void fillUserDetails(String username, String password, String confirmPassword, String userRole, String employeeName, String status) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
        selectFromDropdown(userRoleDropdown, userRole);
        employeeNameField.sendKeys(employeeName);
        selectFromDropdown(statusDropdown, status);
    }

    public void clickSaveButton() {
        waitForWebElementAndClick(saveButton);
    }

    public void searchForUser(String username) {
        searchUsernameField.sendKeys(username);
        waitForWebElementAndClick(searchButton);
    }

    public void deleteUser(String username) {
        searchForUser(username);
        WebElement deleteButton = getDriver().findElement(By.xpath("//tr[td[2][text()='" + username + "']]//button[text()='Delete']"));
        waitForWebElementAndClick(deleteButton);
    }

    public boolean isNoRecordsFoundMessageDisplayed() {
        return noRecordsFoundMessage.isDisplayed();
    }
}

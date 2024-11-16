package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Global_Vars;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;
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

    @FindBy(xpath = "//div[@id='app']/div[1]/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']//form[@class='oxd-form']/div[@class='oxd-form-row']/div/div[4]/div/div[2]/input")
    private WebElement usernameField;

    @FindBy(xpath = "//div[@id='app']//form[@class='oxd-form']/div[@class='oxd-form-row user-password-row']/div/div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']/div//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@id='app']//form[@class='oxd-form']/div[@class='oxd-form-row user-password-row']/div/div[@class='oxd-grid-item oxd-grid-item--gutters']/div//input[@type='password']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//div[@id='app']/div[1]/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']//form[@class='oxd-form']/div[@class='oxd-form-row']/div/div[1]/div/div[2]/div[@class='oxd-select-wrapper']/div")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//div[@id='app']/div[1]/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']//form[@class='oxd-form']/div[@class='oxd-form-row']/div/div[2]/div//div[@class='oxd-autocomplete-wrapper']/div/input[@placeholder='Type for hints...']")
    private WebElement employeeNameField;

    @FindBy(xpath = "//div[@id='app']/div[1]/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']//form[@class='oxd-form']/div[@class='oxd-form-row']/div/div[3]/div/div[2]/div[@class='oxd-select-wrapper']/div")
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
//        // Wait for the dropdown to be visible
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
//        wait.until(ExpectedConditions.visibilityOf(dropdownElement));
//
//        // Create a Select object to interact with the dropdown
//        Select select = new Select(dropdownElement);
//
//        // Select the option by visible text
//        select.selectByVisibleText(value);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
        statusDropdown.click();
        List<WebElement> options = statusDropdown.findElements(By.xpath("//div[@id='app']/div[1]/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']//form[@class='oxd-form']/div[@class='oxd-form-row']/div/div[3]/div/div[2]/div[@class='oxd-select-wrapper']/div/div[@class='oxd-select-text-input']"));
        options.get(0).click();
    }

    public void fillUserDetails(String username, String password, String confirmPassword, String userRole, String employeeName, String status) {
        waitForClickable(By.cssSelector(".oxd-form .oxd-grid-item--gutters:nth-of-type(4) .oxd-input-field-bottom-space div:nth-of-type(2)"));
        usernameField.click();
        usernameField.sendKeys(username);
        passwordField.click();
        passwordField.sendKeys(password);
        confirmPasswordField.click();
        confirmPasswordField.sendKeys(confirmPassword);
        selectFromDropdown(userRoleDropdown, userRole);
        employeeNameField.click();
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

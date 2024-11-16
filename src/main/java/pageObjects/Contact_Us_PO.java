package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Contact_Us_PO extends Base_PO{
    public Contact_Us_PO(){
        super();
    }
    private @FindBy(css = ".oxd-sidepanel-body .oxd-main-menu-item-wrapper:nth-of-type(1) .oxd-main-menu-item--name") WebElement AdminButton;
    public void clickOnAdminTab(){
        waitForWebElementAndClick(AdminButton);
    }


}

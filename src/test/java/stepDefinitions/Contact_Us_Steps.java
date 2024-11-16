package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.Contact_Us_PO;

public class Contact_Us_Steps {

    private Contact_Us_PO contactUs_PO;

    public Contact_Us_Steps(Contact_Us_PO contactUs_PO) {
        this.contactUs_PO = contactUs_PO;
    }

    @When("I click on Admin tab")
    public void i_click_on_admin_tab() {
        contactUs_PO.clickOnAdminTab();
    }
    }

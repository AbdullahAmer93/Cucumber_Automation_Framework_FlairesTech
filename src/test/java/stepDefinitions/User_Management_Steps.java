package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.User_Management_PO;
import static org.testng.AssertJUnit.assertEquals;

public class User_Management_Steps {

    private final User_Management_PO userManagementPO;

    public User_Management_Steps(User_Management_PO userManagementPO) {
        this.userManagementPO = userManagementPO;
    }

    @Then("get number of records")
    public void verify_number_of_records() {
        userManagementPO.extractNumberFromElement();
        //assertEquals("Number of records does not match", expectedNumberOfRecords, actualNumberOfRecords);
    }

    @And("Add a new user with username {string}, password {string}, confirm password {string}, role {string}, employee name {string}, status {string}")
    public void add_new_user(String username, String password, String confirmPassword, String role, String employeeName, String status) {
        userManagementPO.clickAddButton();
        userManagementPO.fillUserDetails(username, password, confirmPassword, role, employeeName, status);
    }

    @And("Click save button")
    public void click_save_button() {
        userManagementPO.clickSaveButton();
    }

    @Then("Verify the number of records increased by {int}")
    public void verify_number_of_records_increased_by(int increment) {
        int initialRecords = userManagementPO.extractNumberFromElement();
        int finalRecords = userManagementPO.extractNumberFromElement();
        assertEquals("The number of records did not increase by " + increment, initialRecords + increment, finalRecords);
    }

    @And("Search for the user with username {string}")
    public void search_for_user(String username) {
        userManagementPO.searchForUser(username);
    }

    @And("Delete the user with username {string}")
    public void delete_user(String username) {
        userManagementPO.deleteUser(username);
    }

    @Then("Verify the number of records decreased by {int}")
    public void verify_number_of_records_decreased_by(int decrement) {
        int initialRecords = userManagementPO.extractNumberFromElement();
        int finalRecords = userManagementPO.extractNumberFromElement();
        assertEquals("The number of records did not decrease by " + decrement, initialRecords - decrement, finalRecords);
    }
}

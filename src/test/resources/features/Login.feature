@login @regression
Feature: WebDriver University - login page

  Background:
    Given I access login page

@login1
  Scenario: Validate successful login with correct credentials
    When I enter username Admin
    And I enter password admin123
    And I click on login button
    And I click on Admin tab

  Scenario: Add, verify, and delete a user
    When I enter username Admin
    And I enter password admin123
    And I click on login button
    And I click on Admin tab
    And get number of records
    When Add a new user with username "testUser", password "Test@123", confirm password "Test@123", role "Admin", employee name "John Charles", status "Enabled"
    And Click save button
    Then Verify the number of records increased by 1
    When Search for the user with username "testUser"
    And Delete the user with username "testUser"
    Then Verify the number of records decreased by 1

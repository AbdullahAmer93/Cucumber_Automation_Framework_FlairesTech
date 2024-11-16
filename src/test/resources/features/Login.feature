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
    ##Then Verify the number of records is numberOfRecords

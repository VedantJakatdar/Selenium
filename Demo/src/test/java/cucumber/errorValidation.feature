@tag
Feature: Check login functionality

  @ErrorValidation
  Scenario Outline: Check login functionality with invalid email and password
    Given I landed on login page
    When I logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username  | password | 
      | golu1234 |  Golu@1234   |

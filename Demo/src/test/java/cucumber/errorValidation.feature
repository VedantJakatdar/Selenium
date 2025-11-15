@tag
Feature: Check login functionality

  @ErrorValidation
  Scenario Outline: Check login functionality with invalid email and password
    Given I landed on login page
    When I logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username  | password | 
      | golu123@gmail.com |  Golu@1234   |

@tag
Feature: Positive test of submitting the order

 Background: 
 			Given I landed on login page

  @PlaceOrder
  Scenario Outline: Place order in ecom website
    Given I logged in with <username> and <password>
    When I added <product> to the cart
    And I verified <product> on cart page
    And I selected country "Ind"
    Then I got message "Order Placed Successfully"

    Examples: 
      | username  				| password 		| product			 |
      | golu123@gmail.com |  Golu@123   |  ZARA COAT 3 |
      
  @VerifyOrder
  Scenario Outline: Place order in ecom website
    Given I logged in with <username> and <password>
    When I went to the orders page
    Then I verified <product> on orders page

    Examples: 
      | username  				| password 		| product			 |
      | golu123@gmail.com |  Golu@123   |  ZARA COAT 3 |
      
    

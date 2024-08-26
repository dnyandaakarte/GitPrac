
@tag
Feature: Purchase order from ecommerce website

	Background:
	Given I landed on Ecommerce Page

  @tag2
  Scenario Outline: Positive test for submitting order
    Given Logged in with username <name> and password <password>
    When I add product <productname> from cart
    And Checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                        | Password | productname |
      | testautomation123@gmail.com | Test@123 | ZARA COAT 3 |

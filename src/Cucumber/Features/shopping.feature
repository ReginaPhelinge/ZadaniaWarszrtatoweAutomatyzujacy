Feature: User orders the product and pays
  Scenario: I can order and buy products in CodersLab shop
    Given I m logged in to CodersLab shop page
    When I chose product from CodersLab shop
    And I  choose the size M and quantity 5
    And I add product to Cart
    Then I'm going to the checkout option
    And I verify created address  "Jagodowa" , "Krakow" , "33-300" , "United Kingdom" , "222222555"
    And I select the collection method - PrestaShop pick up in store
    And I select the payment option - Pay by Check
    And I click on : order with an obligation to pay
    And I will do a screenshot
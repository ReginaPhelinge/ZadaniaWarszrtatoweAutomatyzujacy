Feature: Create new address after login

  Scenario Outline: I can create new address
    Given I m logged in to CodersLab shop
    When I go to Addresses page
    And I create new address
    And I complete with data <arg0> , <arg1> ,<arg2>,<arg3>,<arg4>,<arg5>
    And I check if address is correct
    And I click save
    Then I can see success message with text "Address successfully added!"
    Examples:
      | arg0          | arg1       | arg2     | arg3     | arg4             | arg5        |
      | " My Address" | "Jagodowa" | "Krakow" | "33-300" | "United Kingdom" | "222222555" |

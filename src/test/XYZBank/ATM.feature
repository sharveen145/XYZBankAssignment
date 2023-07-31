Feature: ATM Test Cases

  Scenario Outline: User should able to perform <actionType> successfully

    Given User insert card and enter valid <password>
    Then user select transaction type as <transaction>
    And user
    Examples:
      | password | transaction |
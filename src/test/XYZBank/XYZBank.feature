Feature: XYZ GUI Assignments

  @Question1
  Scenario Outline: Add, verify and delete the added customers as a <role>
    Given navigate to XYZ Bank and Login as <role>
    Then user click on <menu> menu button from the dashboard
    And user add customer with Christopher, Connely, and L789C349
    And user add customer with Frank, Christopher, and A897N450
    And user add customer with Christopher, Minka, and M098Q585
    And user add customer with Connely, Jackson, and L789C349
    And user add customer with Jackson, Frank, and L789C349
    And user add customer with Minka, Jackson, and A897N450
    And user add customer with Jackson, Connely, and L789C349
    Then user click on <menu2> menu button from the dashboard
    And verify the users have added successfully
    Then delete user with Jackson and Frank
    Then delete user with Christopher and Connely
    Then close the browser and quit browser
    Examples:
      | role         | menu         | menu2     |
      | Bank Manager | Add Customer | Customers |


    @Question2
    Scenario Outline: As a <role> perform transaction and verify the current balance
      Given navigate to XYZ Bank and Login as <role>
      When user select name as <user> and click Login
      And user choose account <account>
      And user credit 50000 into account and verify current balance
      And user debit 3000 into account and verify current balance
      And user debit 2000 into account and verify current balance
      And user credit 5000 into account and verify current balance
      And user debit 10000 into account and verify current balance
      And user debit 15000 into account and verify current balance
      And user credit 1500 into account and verify current balance
      Then close the browser and quit browser

      Examples:
      | role      | user             | account |
      | Customer  | Hermoine Granger | 1003    |



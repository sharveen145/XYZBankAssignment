Feature: XYZ Bank API Assignments

  @Question3
Scenario Outline: CRUD function New user
  Given I perform POST operation for "/user" as <user>
  And I perform GET for newly created user
  Then I should see the user name as <user>
  And I perform PUT operation for newly created user
  Then I should see the user name as <updateUser>
  And I should perform DELETE for newly created user
  Then I should verify the newly created user is deleted
  Examples:
    | user  | updateUser |
    | Shan  | Sharveen   |
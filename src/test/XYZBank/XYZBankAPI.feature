Feature: XYZ Bank API Assignments

  @Question3
Scenario Outline: CRUD function for user API
  Given i perform POST operation for user as <user>
  Then i perform GET and verify newly created user
  And i perform PUT operation for newly created user with <updateUser>
  Then i perform GET and verify the updated first name as <updateUser>
  And i should perform DELETE for newly created user
  Then i perform GET and should verify the newly created user is deleted
  Examples:
    | user  | updateUser |
    | Shan  | Sharveen   |
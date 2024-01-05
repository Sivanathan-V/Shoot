Feature: Verify Cyclos Login

  @uiDemo
  Scenario Outline: Verify Login with Valid Credentials
    Given User is on the Cyclos LoginPage
    When User should login "<userName>"
    Then User should verify after Login success message "Demo users"
    Examples:
      | userName |
      | demo     |
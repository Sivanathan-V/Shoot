Feature: Verify Demo Login

@mobileDemo
Scenario: Demo run execution
Given I have launched the Demo app
When I accept user licence
And I enter login credentials
And I select flight details
Then I should see the confirmation message
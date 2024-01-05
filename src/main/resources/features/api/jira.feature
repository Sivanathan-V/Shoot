@api @jira
Feature: Jira module

  @createTicket
  Scenario: post bug in JIRA
  Given I login to cyclops demo website with wrong credentials
  When I post defect in jira
  Then I should verify status code "201"
  And I add screenshot to the defect
  Then I should verify status code "200"
  And I should see the defect details
  When I update my defect
  Then I should verify status code "204"
  When I delete the defect
  Then I should verify status code "204"
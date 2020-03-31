@Login
Feature: Register
  Scenario: Contest Page, Submit Entry
    Given Contest Page is open
    When I press Submit Entry
    And I check Terms of Use checkbox
    And I press Join Button
    Then All register warnings are active
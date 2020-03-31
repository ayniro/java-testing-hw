@Transition
Feature: Pages Transition
  Scenario: Home To Login
    Given Home Page is open
    When I press Login button on Home page
    Then Login Page has been opened
  Scenario: Home To Contest
    Given Home Page is open
    When I press Contest Button on Home page
    Then Contest Page has been opened
  Scenario: Contest To Home
    Given Contest Page is open
    When I press Home button on Contest page
    Then Home Page has been opened
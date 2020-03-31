@Login
Feature: Login
  Scenario: Login Page, Empty Fields
    Given Login Page is open
    When I press SignIn
    Then Login Email warning is active
    And Login Password warning is active
  Scenario: Login Page, Incorrect Email, Empty Password
    Given Login Page is open
    And I enter email "Sometimes stonks go down, but then they go up"
    And I enter password ""
    Then Login Email warning is active
  Scenario: Login Page, Failed Login
    Given Login Page is open
    When I enter email "$SPY_100P_4/17@gmail.com"
    And I enter password "BearsBearsBears"
    And I press SignIn
    Then Failed Login message is active
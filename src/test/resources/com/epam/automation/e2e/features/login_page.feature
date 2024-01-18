Feature: Login page
  Report Portal login page UI tests

  Scenario: Authorized user should be able to login
    Given user navigate to login page
    When user login into application with "default" and password "1q2w3e"
    Then FILTERS page appears
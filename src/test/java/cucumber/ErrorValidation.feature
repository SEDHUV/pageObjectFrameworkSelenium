Feature: Checking ErrorValidation

  Scenario Outline: negative Login Cases
    Given I landed on Ecommerce page
    Given Logged with username<username> and Password<password>
    Then "Incorrect email or password." displayed in the login page
    Examples:
      |username|password|
      |sedhuaema@gmail.com|Sdhu@14|
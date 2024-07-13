@regression
Feature: purchase the order from E-commerce Website

  Background:
    Given I landed on Ecommerce page

  Scenario Outline: positive Test of submitting the Order
    Given Logged with username<username> and Password<password>
    When I add the <productName> to cart
    And checkout the <productName> and submit
    Then "Thankyou for the order." displayed in the confirmation page
    Examples:
    |username|password|productName|
    |sedhuhaema@gmail.com|Sedhu@14|ADIDAS ORIGINAL|
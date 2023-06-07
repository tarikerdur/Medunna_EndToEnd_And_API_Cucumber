@e2e
Feature: Medunna Room Creation

  Background: Sign In Medunna
    Given go to "medunnaUrl"
    When click on user icon
    And click on sign in option
    And enter username
    And enter password
    And click on sign in submit button

  Scenario Outline: Create Room on UI
    When Click on ItemsAndTitles
    And click on Room option
    And click on Create a new room button
    And enter "<room_number>" room number input
    And select Suite option from Room Type dropdown
    And click on Status checkbox
    And enter "<price>" in Price input
    And enter "<description>" in Description input
    And click on Save button
    And close the application
    Examples:
      | room_number | price | description                 |
      | 72244940    | 123   | Created For End To End Test |
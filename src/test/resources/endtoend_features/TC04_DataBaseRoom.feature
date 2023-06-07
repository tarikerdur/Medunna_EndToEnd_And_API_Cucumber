@e2e @database
Feature: Room DataBase Test

  Scenario: Get DataBase and Assert that Room has been created
    Given connect to database
    Then read room and validate
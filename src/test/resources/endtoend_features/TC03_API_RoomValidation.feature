@e2e @api
Feature: Get and Validate Room

  Scenario: Get API Request and Assert
    Given send get request by id
    Then validate response body
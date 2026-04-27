Feature: Cart API Testing

  Scenario: TC_26 Get all carts
    Given user sets carts endpoint
    When user sends carts GET request
    Then carts response should be 200

  Scenario: TC_27 Validate cart list
    Given user sets carts endpoint
    When user sends carts GET request
    Then cart list should not be empty

  Scenario: TC_28 Create cart valid data
    Given user sets cart payload with userId 5 productId 1 quantity 2 date "2024-10-10"
    When user sends cart POST request
    Then cart create response should be success

  Scenario: TC_29 Missing userId
    Given user sets cart payload with only products productId 1 quantity 2
    When user sends cart POST request
    Then invalid cart response should be handled

  Scenario: TC_30 Missing products
    Given user sets cart payload with only userId 5
    When user sends cart POST request
    Then invalid cart response should be handled

  Scenario: TC_31 Invalid productId
    Given user sets cart payload with userId 5 productId 9999 quantity 2
    When user sends cart POST request
    Then invalid cart response should be handled

  Scenario: TC_32 Invalid quantity
    Given user sets cart payload with userId 5 productId 1 quantity 0
    When user sends cart POST request
    Then invalid cart response should be handled

  Scenario: TC_33 Negative quantity
    Given user sets cart payload with userId 5 productId 1 quantity -1
    When user sends cart POST request
    Then invalid cart response should be handled

  Scenario: TC_34 Large quantity
    Given user sets cart payload with userId 5 productId 1 quantity 9999
    When user sends cart POST request
    Then cart request should be handled

  Scenario: TC_35 Invalid date format
    Given user sets cart payload with userId 5 productId 1 quantity 2 date "wrong-date"
    When user sends cart POST request
    Then invalid cart response should be handled

  Scenario: TC_36 Empty body
    Given user sets empty cart payload
    When user sends cart POST request
    Then invalid cart response should be handled

  Scenario: TC_37 Extra fields
    Given user sets cart payload with userId 5 extra field "test"
    When user sends cart POST request
    Then cart request should be handled

  Scenario: TC_38 Response validation
    Given user sets cart payload with userId 5 productId 1 quantity 2
    When user sends cart POST request
    Then response should have cart structure

  Scenario: TC_39 Response time
    Given user sets cart payload with userId 5 productId 1 quantity 2
    When user sends cart POST request
    Then cart response time should be below 2 seconds

  Scenario: TC_40 Content type check
    Given user sets cart payload with userId 5 productId 1 quantity 2
    When user sends cart POST request
    Then cart content type should be json
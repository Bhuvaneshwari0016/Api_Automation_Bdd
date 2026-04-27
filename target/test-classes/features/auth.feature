Feature: Authentication API Test Cases

  Scenario: TC_01 Valid login
    Given user sets login payload with username "johnd" and password "m38rmF$"
    When user sends POST request to "/auth/login"
    Then response status should be 201
    And response should contain token

  Scenario: TC_02 Invalid password
    Given user sets login payload with username "mor_2314" and password "wrong"
    When user sends POST request to "/auth/login"
    Then response status should not be 200
    And response should be error

  Scenario: TC_03 Invalid username
    Given user sets login payload with username "bhuvana" and password "m38rmF$"
    When user sends POST request to "/auth/login"
    Then response status should not be 200
    And response should be error

  Scenario: TC_04 Empty username
    Given user sets login payload with username "" and password "83r5^_"
    When user sends POST request to "/auth/login"
    Then response status should be 400
    And response should be error

  Scenario: TC_05 Empty password
    Given user sets login payload with username "mor_2314" and password ""
    When user sends POST request to "/auth/login"
    Then response status should be 400
    And response should be error

  Scenario: TC_06 Missing fields
    Given user sets empty login payload
    When user sends POST request to "/auth/login"
    Then response status should be 400

  Scenario: TC_07 SQL injection attempt
    Given user sets login payload with username "' OR 1=1--" and password "anything"
    When user sends POST request to "/auth/login"
    Then request should fail

  Scenario: TC_08 Long input values
    Given user sets login payload with username "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" and password "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
    When user sends POST request to "/auth/login"
    Then response should be handled properly

  Scenario: TC_09 Special characters
    Given user sets login payload with username "@#$%^" and password "^&*()"
    When user sends POST request to "/auth/login"
    Then response should be handled safely

  Scenario: TC_10 Response time
    Given user sets login payload with username "johnd" and password "m38rmF$"
    When user sends POST request to "/auth/login"
    Then response time should be less than 2 seconds
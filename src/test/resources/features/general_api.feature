Feature: General API validations for Login, Product and Cart APIs

  Background:
    Given base url is configured

  # TC_41 – Invalid endpoint
  Scenario Outline: Invalid endpoint returns 404
    When user sends "<method>" request to "<endpoint>"
    Then response status code should be 404

    Examples:
      | method | endpoint            |
      | GET    | /auth/logins       |
      | GET    | /productss   |
      | GET    | /cartss     |


  # TC_42 – Method not allowed
  Scenario Outline: Method not allowed validation
    When user sends "<method>" request to "<endpoint>"
    Then response status code should be 404

    Examples:
      | method | endpoint     |
      | GET    | /auth/login  |
      | PUT    | /products    |
      | PUT    | /carts       |


  # TC_45 – API availability
  Scenario Outline: API availability check
    When user sends GET request to "<endpoint>"
    Then API should be available

    Examples:
      | endpoint     |
      | /auth/login  |
      | /products    |
      | /carts       |
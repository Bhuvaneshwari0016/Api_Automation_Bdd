Feature: Products API Testing
 
Scenario: TC_11 Get all products
Given user sets products endpoint
When user sends products GET request
Then products response should be 200
 
Scenario: TC_12 Validate non empty list
Given user sets products endpoint
When user sends products GET request
Then products list should not be empty
 
Scenario: TC_13 Validate product fields
Given user sets products endpoint
When user sends products GET request
Then first product should have id title and price
 
Scenario: TC_14 Validate data types
Given user sets products endpoint
When user sends products GET request
Then id and price types should be valid
 
Scenario: TC_15 Get product by valid ID
Given user sets product id 1
When user sends products GET request
Then products response should be 200
 
Scenario: TC_16 Get product by invalid ID
Given user sets product id 9999
When user sends products GET request
Then invalid product response should be handled
 
Scenario: TC_17 Negative ID
Given user sets product path "-1"
When user sends products GET request
Then invalid product response should be handled
 
Scenario: TC_18 String instead of ID
Given user sets product path "abc"
When user sends products GET request
Then invalid product response should be handled
 
Scenario: TC_19 Check response time
Given user sets products endpoint
When user sends products GET request
Then products response time should be below 2 seconds
 
Scenario: TC_20 Validate price greater than zero
Given user sets products endpoint
When user sends products GET request
Then all prices should be greater than zero
 
Scenario: TC_21 Check title not empty
Given user sets products endpoint
When user sends products GET request
Then all titles should not be empty
 
Scenario: TC_22 API without endpoint
Given user sets invalid endpoint "/productss"
When user sends products GET request
Then products response should be 404
 
Scenario: TC_23 Unsupported method
Given user sets products endpoint
When user sends POST request to products endpoint
Then unsupported method should be handled
 
Scenario: TC_24 Check headers
Given user sets products endpoint
When user sends products GET request
Then products response content type should be json
 
Scenario: TC_25 Large response handling
Given user sets products endpoint
When user sends products GET request
Then large response should be handled
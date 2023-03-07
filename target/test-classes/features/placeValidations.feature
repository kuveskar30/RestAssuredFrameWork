#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Validating Place APIs

#Scenario/Scenario Outline represents test case here
  @AddPlace @Regression
  Scenario Outline: Verify if place added successfully using addPlace API
    Given Add Place Payload with "<placeName>" "<address>" "<contact>"
    When user calls "addPlaceApi" with "POST" http request
    Then The API call is successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<placeName>" using "getPlaceApi"
    
    Examples:
    |placeName			|address							 |contact		 |
    |washington gardern| near central townhall| 1122336655|
    #|sri hospital		| near KH school, virar| 55698745	 |
    
@DeletePlace @Regression
Scenario: Verify if delete place functionallity working
    Given Delete Place Payload
    When user calls "deletePlaceApi" with "POST" http request
    Then The API call is successful with status code 200
    And "status" in response body is "OK"

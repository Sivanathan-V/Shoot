@api @Booking
Feature: Booking module API

  @token
  Scenario: Verify user is able to get the token for the application
    Given User adds the header
    When User add payload for basic authentication
    And User send "POST" request for creating token
    Then Verify the status code is 200
    And User get the logtoken

  @bookingList
  Scenario: Verify user is able to view the Booking List
    Given User adds the header
    When User send "GET" request to booking list endpoint
    Then Verify the status code is 200

  @createBooking
  Scenario Outline: Verify user able to create booking
    Given User adds the header
    When User adds the payload for create booking "<firstname>","<lastname>","<totalprice>","<depositpaid>","<checkin>","<checkout>","<additionalneeds>"
    And User send "POST" request for creating a booking
    Then Verify the status code is 200

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Sharon    | Lewis    |       1000 | true        | 2023-06-01 | 2023-06-10 | breakfast       |

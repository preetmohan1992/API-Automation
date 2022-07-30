Feature: User List Page

Scenario: Get User list and Verify
Given I have a end point URI
When I invoke the getuser end point "GetUserAPI" and I verify the status 
And I verify the response body
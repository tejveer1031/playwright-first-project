Feature: User Login

  Scenario: Successful login with valid credentials
    Given User is on main dashboard page
    And navigate to login page
    Then User is on the login page
    When User enters valid username and password
    And User clicks the "Login" button
    Then User should be logged in successfully log in and navigate to main dashboard


#  Scenario: Login with invalid password
#    Given User is on the login page
#    When User enters valid username
#    And User enters invalid password
#    And User clicks the "Login" button
#    Then User should see an error message "Invalid credentials"
#    And User should remain on the login page
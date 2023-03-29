**/check-login** has been modified to return boolean yes or no based on validaty of the token and accepts the token as the body

**user-token** collection has been modified to include the lastaddeddate.

_application.properties_ have been modified to include the Github authorisation server properties

**GitHubController** has been modified to handle the GitHub Authorisation Callback and save the token ( a simple UUID token).

**SpringSecurityConfig** has been modified to allow only certain URLs and all other URLs need to be authorised to access the application/


Acceptance Criteria:
1. Implement OAuth 2.0 Authorization Code Grant Flow into UserManagementService(_Check in SpringSecurityConfig class)_ . Use UUID for
   tokenizing transmitted data(_Check in GitHubController class_). If you will implement JWT – it is considered a bonus.
2. The end result of this assignment should provide you with a working and tested code, providing
   OAuth 2.0 Authorization Code Grant Flow with GitHub as Authorization Provider.
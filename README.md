**/check-login** has been modified to return boolean yes or no based on validaty of the token and accepts the token as the body

**user-token** collection has been modified to include the lastaddeddate.

_application.properties_ have been modified to include the Github authorisation server properties

**GitHubController** has been modified to handle the GitHub Authorisation Callback and save the token ( a simple UUID token).

**SpringSecurityConfig** has been modified to allow only certain URLs and all other URLs need to be authorised to access the application/


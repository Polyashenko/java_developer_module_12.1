This is a Java web application based on the code from the GitHub repository
https://github.com/Polyashenko/java_developer_servlet_module_11
The application displays the current time in the specified time zone and stores the last selected time zone in a cookie.

## Task 1 - Connect Thymeleaf

To complete Task 1, Thymeleaf has been integrated into the project. The following steps were taken:

1. Thymeleaf was added as a dependency in the project.

2. An HTML template was created for rendering the time.

3. A servlet was implemented that performs the following actions:
    - Calculates the current time in the specified time zone.
    - Passes the calculated parameters and the appropriate HTML template to Thymeleaf.
    - Returns the results from the Thymeleaf template in the servlet.

## Task 2 - Store Time Zone in Cookie

To complete Task 2, the application stores the last selected time zone in a cookie. The steps for Task 2 were
implemented as follows:

1. When a user accesses the URL with a query parameter "timezone," this time zone value is stored in a cookie named
   "lastTimezone."

2. If a user tries to access the page without passing the "timezone" parameter, the application attempts to retrieve
   the time zone from the "lastTimezone" cookie.

3. If the time zone is not found in the cookie, the application uses the UTC time zone as the default.

Example:
- When a user first accesses the URL "http://localhost:8080/time," the result is displayed as "2022-01-05 10:05:01 UTC".

- If the user then accesses the URL "http://localhost:8080/time?timezone=UTC+2," the result is displayed as "2022-01-05
  12:05:01 UTC+2," and "UTC+2" is stored in the "lastTimezone" cookie.

- If the user accesses "http://localhost:8080/time" again, the application retrieves the time zone "UTC+2" from the
  "lastTimezone" cookie and displays the result accordingly.

## Task 3 - Upload Code to GitHub

The code for this project has been uploaded to a new GitHub repository. Here are the steps that were taken to complete
Task 3:

1. A new GitHub repository was created.

2. All the necessary project files, including the code and configuration files, were added to the repository.

3. Care was taken to ensure that no unnecessary or extraneous files were included in the repository.

4. The code was committed to the repository and pushed to GitHub, making it available for others to view and use.

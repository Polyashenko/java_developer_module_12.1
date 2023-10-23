### Task 1 - Create TimeServlet

Create a servlet named `TimeServlet` that responds to a GET request at the `/time` endpoint and returns an HTML page
displaying the current time in the UTC timezone.

The page should display the time (with second precision) and the timezone. For example, "2022-01-05 12:05:01 UTC."

Since this is a GET request, you can test it in a web browser. Start the program and make sure it works correctly by
entering a URL like http://localhost:8080/time in your browser and checking the result.

### Task 2 - Extend TimeServlet to Accept Timezones

Extend the `TimeServlet` from the previous task to accept a query parameter named `timezone` and return the time in the
specified timezone.

For example, if you open a URL like `http://localhost:8080/time?timezone=UTC+2`, you should get a result like
"2022-01-05 12:05:01 UTC+2."

If the `timezone` parameter is not provided, the servlet should return the time in UTC.

### Task 3 - Add a WebFilter for Invalid Timezones

Users may provide an invalid timezone as the `timezone` parameter. In such cases, return a web page with the content
"Invalid timezone" and an HTTP response code of 400 (Bad Request).

To achieve this, create a web filter named `TimezoneValidateFilter`. This filter should intercept requests to the
`/time` endpoint, check for the presence of the `timezone` parameter, and validate it.

You can use the `TimeZone` class for timezone validation.
[Javadoc](https://docs.oracle.com/javase/7/docs/api/java/util/TimeZone.html).

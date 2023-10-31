package org.example;

import java.io.IOException;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


public class TimezoneValidateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // Ініціалізація фільтра
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Отримуємо значення параметра "timezone" з запиту
        String timezoneParam = request.getParameter("timezone");

        // Валідація часового поясу
        if (isValidTimezone(timezoneParam)) {
            // Якщо часовий пояс коректний, продовжуємо ланцюг фільтрів та обробки запиту
            chain.doFilter(request, response);
        } else {
            // Якщо часовий пояс некоректний, відправляємо відповідь з HTTP кодом 400
            response.setContentType("text/html");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid timezone");
        }
    }

    @Override
    public void destroy() {
        // Завершення роботи фільтра
    }

    private boolean isValidTimezone(String timezoneParam) {
        return timezoneParam == null || timezoneParam.trim().isEmpty() || ZoneId.getAvailableZoneIds().contains(timezoneParam);
    }

}

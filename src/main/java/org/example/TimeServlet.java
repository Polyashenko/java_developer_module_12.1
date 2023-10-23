package org.example;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Встановлюємо MIME тип відповіді на текст/HTML
        response.setContentType("text/html");

        // Отримуємо значення параметра "timezone" з запиту
        String timezoneParam = request.getParameter("timezone");
        TimeZone timeZone = TimeZone.getTimeZone("UTC"); // За замовчуванням UTC

        // Якщо параметр "timezone" був переданий, то встановлюємо відповідний часовий пояс
        if (timezoneParam != null && !timezoneParam.isEmpty()) {
            timeZone = TimeZone.getTimeZone(timezoneParam);
        }

        // Отримуємо поточний час у форматі "yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        String currentTimeStr = sdf.format(currentTime);

        // Відправляємо HTML сторінку у відповідь
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Current time in UTC</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Current time in UTC</h1>");
        response.getWriter().println("<p>Time: " + currentTimeStr + " UTC</p>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}

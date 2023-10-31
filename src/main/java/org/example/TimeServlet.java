package org.example;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    public static final String DEFAULT_TIMEZONE = "UTC";
    public static final String PARAM_TIMEZONE = "timezone";
    public static final String CONTENT_TYPE_HTML = "text/html";
    public static final int MILLISECONDS_IN_HOUR = 3600000; // 60 * 60 * 1000 :)

    private transient TemplateEngine engine;

    /**
     * Initializes the servlet by setting up the Thymeleaf template engine with custom settings.
     */
    @Override
    public void init() {
        engine = new TemplateEngine();
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("/Users/dmytropoliashenko/work/Java/GoIt/java_developer_module_12/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE_HTML);
        TimeZone timezone;
        String timezoneParam = request.getParameter(PARAM_TIMEZONE);
        if (timezoneParam != null && !timezoneParam.trim().isEmpty()) {
            ZoneId zoneId = ZoneId.of(timezoneParam);
            timezone = TimeZone.getTimeZone(zoneId);
            saveTimezoneToCookie(response, timezone);
        } else {
            timezone = getTimezoneFromCookie(request);
        }


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(timezone);
        String currentTime = dateFormat.format(new Date());


        Context context = new Context();
        context.setVariable("currentTime", currentTime);
        context.setVariable("availableTimezones", ZoneId.getAvailableZoneIds());


        engine.process("time", context, response.getWriter());
        response.getWriter().close();
    }

    // Метод для отримання часового поясу із Cookie, за замовчуванням - UTC
    private TimeZone getTimezoneFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("lastTimezone".equals(cookie.getName())) {
                    String timezoneID = cookie.getValue();
                    return TimeZone.getTimeZone(timezoneID);
                }
            }
        }
        // Якщо Cookie "lastTimezone" не знайдено, повертаємо часовий пояс UTC за замовчуванням
        return TimeZone.getTimeZone(DEFAULT_TIMEZONE);
    }

    // Метод для збереження часового поясу в Cookie
    private void saveTimezoneToCookie(HttpServletResponse response, TimeZone timezone) {
        Cookie timezoneCookie = new Cookie("lastTimezone", timezone.getID());
        response.addCookie(timezoneCookie);
    }
}

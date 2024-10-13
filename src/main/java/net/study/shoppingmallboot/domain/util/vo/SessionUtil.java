package net.study.shoppingmallboot.domain.util.vo;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {
    private static final Map<String, HttpSession> repository = new ConcurrentHashMap<>();

    private static final String SESSION_NAME = "JSESSION_ID";
    private static final String HISTORY_NAME = "history";


    public static HttpSession getSession(String sessionId) {
        return repository.get(sessionId);
    }

    public static void addSession(String sessionId, HttpSession session) {
        repository.put(sessionId, session);
    }

    public static boolean containsSession(String sessionId) {
        return repository.containsKey(sessionId);
    }

    public static Optional<String> getSessionId(Cookie[] cookies) {
        return getCookieValue(cookies, SESSION_NAME);
    }

    public static Cookie createSessionCookie(String sessionId) {
        return createCookie(SESSION_NAME, sessionId);
    }

    public static Cookie createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60 * 60 * 24);

        return cookie;
    }

     public static Optional<String> getCookieValue(Cookie[] cookies, String cookieName) {
         return Arrays.stream(cookies)
                 .filter(cookie -> cookie.getName().equals(cookieName)
                         && !cookie.getValue().equals(""))
                 .findAny()
                 .map(Cookie::getValue);
    }

    public static void removeSession(String sessionId) {
        if (!containsSession(sessionId)) return;

        repository.get(sessionId).invalidate();
        repository.remove(sessionId);
    }
}

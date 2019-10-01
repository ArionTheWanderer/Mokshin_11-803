import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LLC {
    private static final String ADMIN = "admin";

    private static final String NAME = "name";

    private static final String PASSWORD = "password";

    private static final String REMEMBER = "Remember_me";

    private static final String TRUE = "true";


    public boolean login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String name = request.getParameter(NAME);
        String password = request.getParameter(PASSWORD);
        if (ADMIN.equals(name) && ADMIN.equals(password)){
            session.setAttribute(NAME, name);
            session.setAttribute(PASSWORD, password);
            setCookies(request, response);
            return true;
        } else return false;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getSession(true);
    }

    public boolean checkUser(HttpSession session) throws ServletException, IOException {
        if (session.getAttribute(NAME) != null && session.getAttribute(PASSWORD) != null && session.getAttribute(NAME).equals(ADMIN) && session.getAttribute(PASSWORD).equals(ADMIN)) {
            return true;
        } else return false;
    }

    public boolean checkCookies(HttpServletRequest request) throws ServletException, IOException {
        String cookieValue = ADMIN;
        Cookie[] cookies = request.getCookies();
        boolean f = false;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieValue.equals(c.getValue())) {
                    f = true;
                    break;
                }
            }
        }
        if (f)
            return true;
        else return false;
    }

    public void setCookies(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter(REMEMBER) != null && request.getParameter(REMEMBER).equals(TRUE)) {
            Cookie cookie = new Cookie(ADMIN, ADMIN);
            cookie.setMaxAge(60);
            response.addCookie(cookie);
        }
    }
}

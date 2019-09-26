import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class Login extends HttpServlet {

    private static final String FORM = "<body>\n" + "<h1>Login</h1>" + "<br>" +
            "    <form action=\"/web_application_war_exploded/login\" method=\"post\">\n" +
            "        <input name=\"name\" type=\"text\">\n" +
            "        <br>\n" +
            "        <input name=\"password\" type=\"text\">\n" +
            "        <input type=\"submit\">\n" +
            "        <label><input name=\"Remember_me\" type=\"checkbox\" value=\"true\">" + "Remember me for a month" + "</label>" + "\n" +
            "    </form>\n" + "</body>";

    private static final String FORM2 = "<body>\n" + "<h1>Login</h1>" + "<br>" +
            "    <form action=\"/web_application_war_exploded/login\" method=\"post\">\n" +
            "        <input name=\"name\" type=\"text\" value=\"admin\">\n" +
            "        <br>\n" +
            "        <input name=\"password\" type=\"text\">\n" +
            "        <input type=\"submit\">\n" +
            "        <label><input name=\"Remember_me\" type=\"checkbox\" value=\"true\">" + "Remember me for a month" + "</label>" + "\n" +
            "    </form>\n" + "</body>";

    private static final String WRONG_DATA = "Wrong profile data";

    private static final String ADMIN = "admin";

    private static final String NAME = "name";

    private static final String PASSWORD = "password";

    private static final String PROFILE = "/profile";

    private static final String REMEMBER = "Remember_me";

    private static final String TRUE = "true";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String name = request.getParameter(NAME);
        String password = request.getParameter(PASSWORD);
        PrintWriter out = response.getWriter();
        if (ADMIN.equals(name) && ADMIN.equals(password)){
            session.setAttribute(NAME, name);
            session.setAttribute(PASSWORD, password);
            if(request.getParameter(REMEMBER) != null && request.getParameter(REMEMBER).equals(TRUE)) {
                Cookie cookie = new Cookie(ADMIN, ADMIN);
                cookie.setMaxAge(60*60*24*30);
                response.addCookie(cookie);
            }
            String path = request.getContextPath() + PROFILE;
            response.sendRedirect(path);
        } else out.print(WRONG_DATA);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session.getAttribute(NAME) != null && session.getAttribute(PASSWORD) != null && session.getAttribute(NAME).equals(ADMIN) && session.getAttribute(PASSWORD).equals(ADMIN)) {
            String path = request.getContextPath() + PROFILE;
            response.sendRedirect(path);
        } else {
            String cookieValue = ADMIN;
            Cookie[] cookies = request.getCookies();
            boolean f = false;
            if (cookies != null) {
                for(Cookie c: cookies) {
                    if(cookieValue.equals(c.getValue())) {
                        f = true;
                        break;
                    }
                }
            }
            if (f)
                out.print(FORM2);
            else out.print(FORM);
        }

    }
}

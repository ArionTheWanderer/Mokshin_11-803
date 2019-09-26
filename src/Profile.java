import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;



public class Profile extends HttpServlet {

    private static final String PROFILE ="\n" + "<body>\n" + "<h1>This is your profile<h1>" + "\n" +
            "    <form action=\"/web_application_war_exploded/profile\" method=\"post\">\n" +
            "        <input type=\"submit\" value =\"Logout\">\n" +
            "    </form>\n" + "</body>";

    private static final String ADMIN = "admin";

    private static final String NAME = "name";

    private static final String PASSWORD = "password";

    private static final String LOGOUT = "/logout";

    private static final String LOGIN = "/login";



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        String path = request.getContextPath() + LOGOUT;
        response.sendRedirect(path);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session.getAttribute(NAME) != null && session.getAttribute(PASSWORD) != null && session.getAttribute(NAME).equals(ADMIN) && session.getAttribute(PASSWORD).equals(ADMIN)) {
            out.print(PROFILE);
        } else {
            String path = request.getContextPath() + LOGIN;
            response.sendRedirect(path);
        }
    }
}

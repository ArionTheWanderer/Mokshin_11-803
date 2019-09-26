import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {

    private static final String GUY = "guy";

    private static final String GROUP = "group";

    private static final String AUTHORIZE = "Please, authorize";

    private static final String FORM = "<body>\n" +
            "    <form action=\"/web_application_war_exploded/news\" method=\"post\">\n" +
            "        <input name=\"guy\" type=\"text\">\n" +
            "        <br>\n" +
            "        <input name=\"group\" type=\"text\">\n" +
            "        <input type=\"submit\">\n" +
            "    </form>>\n" +
            "</body>";

    private static final String WELCOME = "Welcome to the ";

    private static final String COMMA = " ,";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String param1 = request.getParameter(GUY);
        String param2 = request.getParameter(GROUP);
        if (param1 != null || param2 != null)
            out.print(WELCOME + param2 + COMMA + param1);
        else out.print(AUTHORIZE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print(FORM);
        String param1 = request.getParameter(GUY);
        String param2 = request.getParameter(GROUP);
        if (param1 != null || param2 != null)
            out.print(WELCOME + param2 + COMMA + param1);
        else out.print(AUTHORIZE);
    }
}

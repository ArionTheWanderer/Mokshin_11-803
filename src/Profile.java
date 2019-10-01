import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Profile extends HttpServlet {

    private LLC llc = new LLC();

    private BL bl = new BL();

    private static final String PROFILE1 ="\n" + "<body>\n" + "<h1>This is your profile<h1>" + "\n" + "<table>\n" +
            "<tr><th colspan=\"2\" style=\"text-align:center;\">Orders</th></tr>\n";

    private static final String PROFILE2 = "</table>\n" + "<form action=\"/web_application_war_exploded/profile\" method=\"post\">\n" +
            "        <input name=\"name\" type=\"text\">\n" +
            "        <br>\n" +
            "        <input name=\"button\" type=\"submit\">\n" +
            "    </form>\n" +
            "    <form action=\"/web_application_war_exploded/profile\" method=\"post\">\n" +
            "        <input name=\"logout\" type=\"submit\" value =\"Logout\">\n" +
            "    </form>\n" + "</body>";


    private static final String LOGOUT = "/logout";

    private static final String LOGIN = "/login";

    private static final String LOGOUT_PARAM = "logout";

    private static final String NAME = "name";

    private static final String BUTTON = "button";

    ArrayList<Good> currentGoods = new ArrayList<>();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter(LOGOUT_PARAM) != null) {
            currentGoods.clear();
            request.getSession().invalidate();
            String path = request.getContextPath() + LOGOUT;
            response.sendRedirect(path);
        } else if (request.getParameter(BUTTON) != null && request.getParameter(NAME) != null){
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            String name = request.getParameter(NAME);
            Good g = new Good(Good.getCurrentId(), name);
            String output;
            currentGoods.add(g);
            out.print(PROFILE1);
            if (currentGoods != null) {
                for (Good good: currentGoods) {
                    output = "<tr><td>" + good.getId() + "</td><td>" + good.getName() + "</td><tr>";
                    out.print(output + "\n");
                }
            }
            out.print(PROFILE2);
            session.removeAttribute("goods");
            session.setAttribute("goods", currentGoods);
        } else if (request.getParameter(BUTTON) != null && request.getParameter(NAME).equals("")){

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String output;
        if (llc.checkUser(session)) {
            out.print(PROFILE1);
            ArrayList<Good> goods = bl.getGoods(session);
            if (goods != null) {
                boolean f = currentGoods.addAll(goods);
                System.out.println(f);
                for (Good good: goods) {
                    output = "<tr><td>" + good.getId() + "</td><td>" + good.getName() + "</td><tr>";
                    out.print(output + "\n");
                }
                session.removeAttribute("goods");
            }
            out.print(PROFILE2);
        } else {
            String path = request.getContextPath() + LOGIN;
            response.sendRedirect(path);
        }
    }
}

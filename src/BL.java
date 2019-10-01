import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class BL {
    private ArrayList<Good> goodsAL;

    public ArrayList<Good> getGoods(HttpSession session) {
        if (session.getAttribute("goods") != null) {
            ArrayList<Good> goods = (ArrayList<Good>) session.getAttribute("goods");
            if (goods != null) {
                for (Good good: goods) {
                    goodsAL.add(good);
                }
            }
        }
        return goodsAL;
    }


}

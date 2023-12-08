import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller-servlet")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String x = req.getParameter("check");
            String y = req.getParameter("inputY");
            String r = req.getParameter("inputR");
            if ( x != null && y != null && r != null
                    && !y.isEmpty() && !r.isEmpty() && !x.isEmpty()
                        && !y.equals("-") && !y.equals("+")) {
                req.getRequestDispatcher("/area-check-servlet").forward(req, resp);
            }
        } catch (NumberFormatException ex){
            System.out.println("ERROR: invalid client input.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}

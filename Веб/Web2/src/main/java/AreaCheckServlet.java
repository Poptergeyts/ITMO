import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/area-check-servlet")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LocalDateTime start = LocalDateTime.now();
        float x = Float.parseFloat(req.getParameter("check"));
        float y = Float.parseFloat(req.getParameter("inputY"));
        float r = Float.parseFloat(req.getParameter("inputR"));

        PrintWriter writer = resp.getWriter();

        fillTable(writer, x, y, r, start);
    }

    private void fillTable(PrintWriter writer, float x, float y, float r, LocalDateTime start){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String st = start.format(formatter);

        writer.println("<tr>");
        if (checkArea(x, y ,r)){
            writer.println("<td>Yes</td>");
        } else{
            writer.println("<td>No</td>");
        }
        writer.println("<td>" + x + "</td>");
        writer.println("<td>" + y + "</td>");
        writer.println("<td>" + r + "</td>");
        writer.println("<td>" + st + "</td>");

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);

        writer.println("<td>" + duration.toMillis() + "</td>");
        writer.println("</tr>");
    }

    private boolean checkArea(float x, float y, float r) {
        boolean point_in_rect = (x <= r && x >= 0) && (y <= r / 2.0 && y >=0);
        float tf1 = -(r) * (-y);
        float tf2 = (-x) * (-r);
        float tf3 = (-x) * (r) - (-r) * (-r - y);
        boolean point_in_triangle = (tf3 >= 0 && tf2 >= 0 && tf1 >= 0) || (tf3 <= 0 && tf2 <= 0 && tf1 <= 0);
        boolean point_in_circle = (x >= 0 && y <= 0) && ((x * x) + (y * y)) <= r * r / 4.0;
        return (point_in_circle || point_in_triangle || point_in_rect);
    }
}

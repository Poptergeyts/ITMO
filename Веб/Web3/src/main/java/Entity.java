import lombok.Data;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
@Data
public class Entity implements Serializable {

    private boolean entry;
    private float x;
    private float y;
    private float r;
    private String time;
    private String script;

    private List<Entity> list = new ArrayList<>();

    public void print() {
        LocalDateTime start = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String st = start.format(formatter);
        Entity entity = new Entity();
        entity.setEntry(ifTheCoordinatesIn(x, y, r));
        entity.setX(x);
        entity.setY(y);
        entity.setR(r);
        entity.setTime(st);
        entity.setScript("1");
        db.makeDefaultTable();
        db.addEntity(entity);
        list.add(entity);
    }

    public void clear() {
        list.clear();
    }

    private boolean ifTheCoordinatesIn(float x, float y, float r){
        boolean point_in_rect = (x <= r && x >= 0) && (y <= r / 2.0 && y >=0);
        float tf1 = -(r) * (-y);
        float tf2 = (-x) * (-r);
        float tf3 = (-x) * (r) - (-r) * (-r - y);
        boolean point_in_triangle = (tf3 >= 0 && tf2 >= 0 && tf1 >= 0) || (tf3 <= 0 && tf2 <= 0 && tf1 <= 0);
        boolean point_in_circle = (x >= 0 && y <= 0) && ((x * x) + (y * y)) <= r * r / 4.0;
        return (point_in_circle || point_in_triangle || point_in_rect);
    }

    @Override
    public String toString() {
        return  "Result: " + entry + "\n" +
                "X: " + x + "\n" +
                "Y: " + y + "\n" +
                "R: " + r + "\n" +
                "Time: " + time + "\n";
    }
}

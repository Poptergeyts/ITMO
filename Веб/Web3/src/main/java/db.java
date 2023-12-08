import lombok.Data;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
@Data
public class db implements Serializable {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/studs";
    private static final String USER = "s367652";
    private static final String PASSWORD = "7DcNVdyo3w43KKpa";
    private static Connection connection = makeAConnection();

    public db() {
        makeDefaultTable();
    }

    private static Connection makeAConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return con;
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    static void makeDefaultTable(){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS information(id serial PRIMARY KEY, entry boolean, x float, y float, r float, t text, script text)");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addEntity(Entity entity){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO information(entry, x, y, r, t, script) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setBoolean(1, entity.isEntry());
            ps.setFloat(2, entity.getX());
            ps.setFloat(3, entity.getY());
            ps.setFloat(4, entity.getR());
            ps.setString(5, entity.getTime());
            ps.setString(6, entity.getScript());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void clearTable(){
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("TRUNCATE TABLE information");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Entity> getAllEntities() {
        ArrayList<Entity> entities = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM information");

            while (resultSet.next()) {
                Entity e = new Entity(); // создаем новый экземпляр на каждой итерации
                e.setEntry(resultSet.getBoolean("entry"));
                e.setX(resultSet.getFloat("x"));
                e.setY(resultSet.getFloat("y"));
                e.setR(resultSet.getFloat("r"));
                e.setTime(resultSet.getString("t"));
                e.setScript(resultSet.getString("script"));
                entities.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

}

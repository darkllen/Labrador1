import DB.Connect;
import DB.Database;
import Interface.Menu;

public class Main {
    public static void main(String[] args) {
        Menu.create(600,400);
        Connect connect = new Connect();
        System.out.println("s");
        Database database = new Database(connect.connectToDB());
        System.out.println("s");
        System.out.println(database.getFaculties().get(0).getName());

    }

}
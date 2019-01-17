import DB.Connect;
import DB.Database;

public class Main {
    public static void main(String[] args) {
        Connect connect = new Connect();
        System.out.println("s");
        Database database = new Database(connect.connectToDB());
        System.out.println("s");
        System.out.println(database.getFaculties().get(0).getName());
}

}
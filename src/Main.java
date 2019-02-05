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
        System.out.println(database.getCafedrasByName("Caf"));
        //database.insertNewFaculty("test");
       // database.updateFacultyById(database.getFaculties().get(0).getId(),"smth");
        //int cId=database.getCafedras().get(0).getId();
        //database.insertNewPerson(cId,"Q","W","e",3,4,1);

    }

}
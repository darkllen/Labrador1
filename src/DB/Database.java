package DB;

import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Statement statement;

    public Database(Statement statement) {
        this.statement = statement;
    }

    public void insertNewFaculty(String facultyName){
      String query =  "INSERT INTO `Faculty` (`name`) VALUES (\""+facultyName+"\")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertNewCafedra(int idFaculty, String cafedraName){
        String query =  "INSERT INTO `Cafedra` (`id_faculty`, `name`) VALUES (\""+idFaculty+"\",\""+cafedraName+"\")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO add possibility to add teacher without course and group
    public void insertNewPerson(int idCafedra, String name, String surname, String father_name, int studentTeacher, int course, int group){
        String query =  "INSERT INTO `People` (`id_cafedra`, `name`, `surname`, `father_name`, `student_teacher`, `course`, `s_group`) VALUES (\""+idCafedra+"\",\""+name+"\",\""+surname+"\",\""+father_name+"\",\""+studentTeacher+"\",\""+course+"\",\""+group+"\")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

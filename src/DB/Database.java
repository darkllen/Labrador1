package DB;

import Model.Cafedra;
import Model.Faculty;
import Model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public ArrayList<Faculty> getFaculties(){
        ArrayList<Faculty> arrayList = new ArrayList();
        String query = "Select * from `Faculty`";
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Faculty faculty = new Faculty(rs.getInt("id"),rs.getString("name"));
                arrayList.add(faculty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void updateFacultyById(int id, String name){
        String query = "UPDATE `Faculty` SET `name` = '"+name+"' WHERE `Faculty`.`id`"+id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cafedra> getCafedrasByFacultyId(int facultyId){
        ArrayList<Cafedra> arrayList = new ArrayList();
        String query = "Select * from `Cafedra` where id_faculty =" +facultyId;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Cafedra cafedra = new Cafedra(rs.getInt("id"),rs.getString("name"),facultyId);
                arrayList.add(cafedra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
    public ArrayList<Cafedra> getCafedrasByName(String name){
        ArrayList<Cafedra> arrayList = new ArrayList();
        String query = "Select * from `Cafedra` where name =" +name;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Cafedra cafedra = new Cafedra(rs.getInt("id"),name,rs.getInt("id_faculty"));
                arrayList.add(cafedra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void insertNewCafedra(int idFaculty, String cafedraName){
        String query =  "INSERT INTO `Cafedra` (`id_faculty`, `name`) VALUES (\""+idFaculty+"\",\""+cafedraName+"\")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cafedra> getCafedras(){
        ArrayList<Cafedra> arrayList = new ArrayList();
        String query = "Select * from `Cafedra`";
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Cafedra cafedra = new Cafedra(rs.getInt("id"),rs.getString("name"),rs.getInt("id_faculty"));
                arrayList.add(cafedra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void updateCafedra(int id, int facultyId, String name){
        String query = "UPDATE `Cafedra` SET `id_faculty` = '"+facultyId+"', `name` = '"+name+"' WHERE `Cafedra`.`id` = "+id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertNewPerson(int idCafedra, String name, String surname, String father_name, int studentTeacher, int course, int group){
        String query =  "INSERT INTO `People` (`id_cafedra`, `name`, `surname`, `father_name`, `student_teacher`, `course`, `s_group`) VALUES (\""+idCafedra+"\",\""+name+"\",\""+surname+"\",\""+father_name+"\",\""+studentTeacher+"\",\""+course+"\",\""+group+"\")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }
    public ArrayList<Person> getPeople(){
        ArrayList<Person> people = new ArrayList();
        String query = "Select * from `Person`";
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Person person = new Person(rs.getInt("id"),rs.getInt("cafedra_"),rs.getString("name"),rs.getString("surname"),rs.getString("father_name"),rs.getInt("student_teacher"),rs.getInt("course"),rs.getInt("s_group"));
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }
    public ArrayList<Person> getPeopleByCafedraId(int cafedraId){
        ArrayList<Person> people = new ArrayList();
        String query = "Select * from `Person` where id_cafedra ="+cafedraId;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Person person = new Person(rs.getInt("id"),rs.getInt("cafedra_"),rs.getString("name"),rs.getString("surname"),rs.getString("father_name"),rs.getInt("student_teacher"),rs.getInt("course"),rs.getInt("s_group"));
                people.add(person);;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public void updatePersonById(int id,int idCafedra, String name, String surname, String father_name, int studentTeacher, int course, int group){
String query = "UPDATE `People` SET `id_cafedra` = '"+idCafedra+"', `name` = '"+name+"', `surname` = '"+surname+"', `father_name` = '"+father_name+"', `studentTeacher` = '"+studentTeacher+"', `course` = '"+course+"', `father_name` = '"+group+"' WHERE `People`.`id` = "+id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Person> findPerson(int idCafedra, String name, String surname, String father_name, int studentTeacher, int course, int group){
        ArrayList<Person> people= getPeople();
        if (idCafedra!=0) people=findPersonByIdCafedra(people, idCafedra);
        if (!name.equals("")) people=findPersonByName(people, name);
        if (!surname.equals("")) people=findPersonBySurname(people, surname);
        if (!father_name.equals("")) people=findPersonByFatherName(people, father_name);
        if (studentTeacher!=0) people=findPersonByStudentTeacher(people, studentTeacher);
        if (course!=0) people=findPersonByCourse(people, course);
        if (group!=0) people=findPersonByGroup(people, group);
        return people;
    }
    private ArrayList<Person> findPersonByIdCafedra(ArrayList<Person> people, int cafedra_id){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getCafedraId()==cafedra_id){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }
    private ArrayList<Person> findPersonByName(ArrayList<Person> people, String  name){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getName().equals(name)){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    private ArrayList<Person> findPersonBySurname(ArrayList<Person> people, String surname){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getSurname().equals(surname)){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    private ArrayList<Person> findPersonByFatherName(ArrayList<Person> people, String fatherName){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getFatherName().equals(fatherName)){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    private ArrayList<Person> findPersonByStudentTeacher(ArrayList<Person> people, int studentTeacher){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).isATeacher()==studentTeacher){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    private ArrayList<Person> findPersonByGroup(ArrayList<Person> people, int group){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getGroup()==group){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    private ArrayList<Person> findPersonByCourse(ArrayList<Person> people, int course){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getCafedraId()==course){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }



}

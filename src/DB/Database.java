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

    /**
     * insert new faculty with this facultyName
     * @param facultyName
     */
    public void insertNewFaculty(String facultyName){
      String query =  "INSERT INTO `Faculty` (`name`) VALUES (\""+facultyName+"\")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteFaculty(int id) throws SQLException {
        String  query = "delete from `Faculty` where id="+id;
        statement.execute(query);
        ArrayList<Cafedra> cafedras = getCafedrasByFacultyId(id,0);
        query = "delete from `Cafedra` where id_faculty="+id;
        statement.execute(query);
        for (int i=0;i<cafedras.size();i++){
            query = "delete from `People` where id_cafedra="+cafedras.get(i).getFacultyId();
            statement.execute(query);
        }
    }
    public void deleteСafedra(int id) throws SQLException {
        String  query = "delete from `Cafedra` where id="+id;
        statement.execute(query);
        query = "delete from `People` where id_cafedra="+id;
        statement.execute(query);

    }
    public void deletePeople(int id) throws SQLException {
        String  query = "delete from `People` where id="+id;
        statement.execute(query);

    }

    /**
     * @param sortAD 1 for descending sort, 0 for ascending sort by name
     * @return arrayList of all faculties
     */
    public ArrayList<Faculty> getFaculties(int sortAD){
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
        sortFaculty(arrayList,sortAD);
        return arrayList;
    }
    public ArrayList<Faculty> getFacultiesByName(String name){
        ArrayList<Faculty> arrayList = new ArrayList();
        String query = "Select * from `Faculty` where name="+name;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Faculty faculty = new Faculty(rs.getInt("id"),rs.getString("name"));
                arrayList.add(faculty);
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        }
        return arrayList;
    }

    /**
     *replace faculty with this id with new name
     * @param id
     * @param name
     */
    public void updateFacultyById(int id, String name){
        String query = "UPDATE `Faculty` SET `name` = '"+name+"' WHERE `Faculty`.`id`="+id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param facultyId
     * @param sortAD 1 for descending sort, 0 for ascending sort by name
     * @return all cafedras with this id
     */
    public ArrayList<Cafedra> getCafedrasByFacultyId(int facultyId, int sortAD){
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
        sortCafedras(arrayList,sortAD);
        return arrayList;
    }

    /**
     *
     * @param name
     * @return all cafedras with this name
     */
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

    /**
     * create new cafedra
     * @param idFaculty
     * @param cafedraName
     */
    public void insertNewCafedra(int idFaculty, String cafedraName){
        String query =  "INSERT INTO `Cafedra` (`id_faculty`, `name`) VALUES (\""+idFaculty+"\",\""+cafedraName+"\")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param sortAD 1 for descending sort, 0 for ascending sort by name
     * @return all cafedras
     */
    public ArrayList<Cafedra> getCafedras(int sortAD){
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
        sortCafedras(arrayList,sortAD);
        return arrayList;
    }

    /**
     * replace cafedra with this id
     * @param id
     * @param facultyId
     * @param name
     */
    public void updateCafedra(int id, int facultyId, String name){
        String query = "UPDATE `Cafedra` SET `id_faculty` = '"+facultyId+"', `name` = '"+name+"' WHERE `Cafedra`.`id` = "+id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * create new person
     * @param idCafedra
     * @param name
     * @param surname
     * @param father_name
     * @param studentTeacher
     * @param course
     * @param group
     */
    public void insertNewPerson(int idCafedra, String name, String surname, String father_name, String studentTeacher, String  course, String group){
        String query =  "INSERT INTO `People` (`id_cafedra`, `name`, `surname`, `father_name`, `student_teacher`, `course`, `s_group`) VALUES (\""+idCafedra+"\",\""+name+"\",\""+surname+"\",\""+father_name+"\",\""+studentTeacher+"\",\""+course+"\",\""+group+"\")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }

    /**
     * @param sortColumn 0 for name, 1 for surname, 2 for father_name, 3 for studentTeacher, 4 for course, 5 for group
     * @param sortAD 1 for descending sort, 0 for ascending sort
     * @return all people
     */
    public ArrayList<Person> getPeople(int sortColumn, int sortAD){
        ArrayList<Person> people = new ArrayList();
        String query = "Select * from `People`";
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Person person = new Person(rs.getInt("id"),rs.getInt("id_cafedra"),rs.getString("name"),rs.getString("surname"),rs.getString("father_name"),rs.getString("student_teacher"),rs.getString("course"),rs.getString("s_group"));
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sortPerson(people,sortColumn,sortAD);
        return people;
    }

    /**
     *
     * @param cafedraId
     * @param sortColumn 0 for name, 1 for surname, 2 for father_name, 3 for studentTeacher, 4 for course, 5 for group
     * @param sortAD 1 for descending sort, 0 for ascending sort
     * @return all people with this cafedraId
     */
    public ArrayList<Person> getPeopleByCafedraId(int cafedraId, int sortColumn, int sortAD){
        ArrayList<Person> people = new ArrayList();
        String query = "Select * from `People` where id_cafedra ="+cafedraId;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Person person = new Person(rs.getInt("id"),rs.getInt("id_cafedra"),rs.getString("name"),rs.getString("surname"),rs.getString("father_name"),rs.getString("student_teacher"),rs.getString("course"),rs.getString("s_group"));
                people.add(person);;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sortPerson(people,sortColumn,sortAD);
        return people;
    }

    /**
     * replace person with this id
     * @param id
     * @param idCafedra
     * @param name
     * @param surname
     * @param father_name
     * @param studentTeacher
     * @param course
     * @param group
     */
    public void updatePersonById(int id,int idCafedra, String name, String surname, String father_name, String studentTeacher, String course, String group){
        String query = "UPDATE People SET id_cafedra = '"+idCafedra+"', name = '"+name+"', surname = '"+surname+"', father_name = '"+father_name+"', student_teacher = '"+studentTeacher+"', course = '"+course+"', s_group = '"+group+"' WHERE People.id = "+id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * find person by any of these params.
     * Params that will not be used should be 0 for int and "" for String
     * @param idCafedra
     * @param name
     * @param surname
     * @param father_name
     * @param studentTeacher
     * @param course
     * @param group
     * @param sortColumn 0 for name, 1 for surname, 2 for father_name, 3 for studentTeacher, 4 for course, 5 for group
     * @param sortAD 1 for descending sort, 0 for ascending sort
     * @return
     */
    public ArrayList<Person> findPerson(int idCafedra, String name, String surname, String father_name, String studentTeacher, String course, String group, int sortColumn, int sortAD){
        ArrayList<Person> people= getPeople(0,0);
        if (idCafedra!=0) people=findPersonByIdCafedra(people, idCafedra);
        if (!name.equals("")) people=findPersonByName(people, name);
        if (!surname.equals("")) people=findPersonBySurname(people, surname);
        if (!father_name.equals("")) people=findPersonByFatherName(people, father_name);
        if (!studentTeacher.equals("")) people=findPersonByStudentTeacher(people, studentTeacher);
        if (!course.equals("")) people=findPersonByCourse(people, course);
        if (!group.equals("")) people=findPersonByGroup(people, group);
        sortPerson(people,sortColumn, sortAD);
        return people;
    }

    //All next methods should be used just in findPeople();

    /**
     *
     * @param people
     * @param cafedra_id
     * @return
     */
    private ArrayList<Person> findPersonByIdCafedra(ArrayList<Person> people, int cafedra_id){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getCafedraId()==cafedra_id){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    /**
     *
     * @param people
     * @param name
     * @return
     */
    private ArrayList<Person> findPersonByName(ArrayList<Person> people, String  name){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getName().equals(name)){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    /**
     *
     * @param people
     * @param surname
     * @return
     */
    private ArrayList<Person> findPersonBySurname(ArrayList<Person> people, String surname){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getSurname().equals(surname)){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    /**
     *
     * @param people
     * @param fatherName
     * @return
     */
    private ArrayList<Person> findPersonByFatherName(ArrayList<Person> people, String fatherName){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getFatherName().equals(fatherName)){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    /**
     *
     * @param people
     * @param studentTeacher
     * @return
     */
    private ArrayList<Person> findPersonByStudentTeacher(ArrayList<Person> people, String studentTeacher){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).isATeacher().equals(studentTeacher)){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    /**
     *
     * @param people
     * @param group
     * @return
     */
    private ArrayList<Person> findPersonByGroup(ArrayList<Person> people, String group){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getGroup().equals(group)){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }

    /**
     *
     *
     * @param people
     * @param course
     * @return
     */
    private ArrayList<Person> findPersonByCourse(ArrayList<Person> people, String course){
        ArrayList<Person> personArrayList= new ArrayList<>();
        for (int i=0;i<people.size();i++){
            if (people.get(i).getCourse().equals(course)){
                personArrayList.add(people.get(i));
            }
        }
        return personArrayList;
    }


    private void sortCafedras(ArrayList<Cafedra> cafedras, int sortAD){
        switch (sortAD){
            case 0:
                for (int i = cafedras.size()-1;i>=1;i--){
                    for (int j=0;j<i;j++){
                        if (cafedras.get(j).getName().toLowerCase().compareTo(cafedras.get(i).getName().toLowerCase())<0){
                            Cafedra temp = cafedras.get(i);
                            cafedras.set(i,cafedras.get(j));
                            cafedras.set(j,temp);
                        }
                    }
                }
                break;
            case 1:
                for (int i = cafedras.size()-1;i>=1;i--){
                    for (int j=0;j<i;j++){
                        if (cafedras.get(j).getName().toLowerCase().compareTo(cafedras.get(i).getName().toLowerCase())>0){
                            Cafedra temp = cafedras.get(i);
                            cafedras.set(i,cafedras.get(j));
                            cafedras.set(j,temp);
                        }
                    }
                }
        }
    }
    private void sortFaculty(ArrayList<Faculty> faculties, int sortAD){
        switch (sortAD){
            case 0:
                for (int i = faculties.size()-1;i>=1;i--){
                    for (int j=0;j<i;j++){
                        if (faculties.get(j).getName().toLowerCase().compareTo(faculties.get(i).getName().toLowerCase())<0){
                            Faculty temp = faculties.get(i);
                            faculties.set(i,faculties.get(j));
                            faculties.set(j,temp);
                        }
                    }
                }
                break;
            case 1:
                for (int i = faculties.size()-1;i>=1;i--){
                    for (int j=0;j<i;j++){
                        if (faculties.get(j).getName().toLowerCase().compareTo(faculties.get(i).getName().toLowerCase())>0){
                            Faculty temp = faculties.get(i);
                            faculties.set(i,faculties.get(j));
                            faculties.set(j,temp);
                        }
                    }
                }
        }
    }
    public void sortPerson(ArrayList<Person> people, int sotrColumn, int sortAD){
        switch (sotrColumn){
            case 0:
                switch (sortAD){
                    case 0:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (people.get(j).getName().toLowerCase().compareTo(people.get(i).getName().toLowerCase())<0){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (people.get(j).getName().toLowerCase().compareTo(people.get(i).getName().toLowerCase())>0){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                }
                break;
            case 1:
                switch (sortAD){
                    case 0:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (people.get(j).getSurname().toLowerCase().compareTo(people.get(i).getSurname().toLowerCase())<0){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (people.get(j).getSurname().toLowerCase().compareTo(people.get(i).getSurname().toLowerCase())>0){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                }
                break;
            case 2:
                switch (sortAD){
                    case 0:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (people.get(j).getFatherName().toLowerCase().compareTo(people.get(i).getFatherName().toLowerCase())<0){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (people.get(j).getFatherName().toLowerCase().compareTo(people.get(i).getFatherName().toLowerCase())>0){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                }
                break;
            case 3:
                switch (sortAD){
                    case 0:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (people.get(j).isATeacher().toLowerCase().compareTo(people.get(i).isATeacher().toLowerCase())<0){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (people.get(j).isATeacher().toLowerCase().compareTo(people.get(i).isATeacher().toLowerCase())>0){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                }
                break;
            case 4:
                switch (sortAD){
                    case 0:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (Integer.valueOf(people.get(j).getCourse()) < Integer.valueOf(people.get(i).getCourse())){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (Integer.valueOf(people.get(j).getCourse()) > Integer.valueOf(people.get(i).getCourse())){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                }
                break;
            case 5:
                switch (sortAD){
                    case 0:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (Integer.valueOf(people.get(j).getGroup()) < Integer.valueOf(people.get(i).getGroup())){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = people.size()-1;i>=1;i--){
                            for (int j=0;j<i;j++){
                                if (Integer.valueOf(people.get(j).getGroup()) > Integer.valueOf(people.get(i).getGroup())){
                                    Person temp = people.get(i);
                                    people.set(i,people.get(j));
                                    people.set(j,temp);
                                }
                            }
                        }
                }

        }
    }
}

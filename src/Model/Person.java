package Model;

public class Person {

    /**
     * @param id
     * @param cafedraId
     * @param name
     * @param surname
     * @param fatherName
     * @param isATeacher
     * @param course
     * @param group
     */
    public Person(int id, int cafedraId, String name, String surname, String fatherName, String isATeacher, String course, String group) {
        this.id = id;
        this.cafedraId = cafedraId;
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
        this.isATeacher = isATeacher;
        this.course = course;
        this.group = group;
    }

    private int id;
    private int cafedraId;
    private String name;
    private String surname;
    private String fatherName;
    private String isATeacher;
    private String course;
    private String group;

    public int getId(){return id;}

    public int getCafedraId() {
        return cafedraId;
    }

    public void setCafedraId(int cafedraId) {
        this.cafedraId = cafedraId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String isATeacher() {
        return isATeacher;
    }

    public void setATeacher(String ATeacher) {
        isATeacher = ATeacher;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String  getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

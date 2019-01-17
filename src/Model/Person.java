package Model;

public class Person {

    public Person(int cafedraId, String name, String surname, String fatherName, boolean isATeacher, int course, int group) {
        this.cafedraId = cafedraId;
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
        this.isATeacher = isATeacher;
        this.course = course;
        this.group = group;
    }

    private int cafedraId;
    private String name;
    private String surname;
    private String fatherName;
    private boolean isATeacher;
    private int course;
    private int group;

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

    public boolean isATeacher() {
        return isATeacher;
    }

    public void setATeacher(boolean ATeacher) {
        isATeacher = ATeacher;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}

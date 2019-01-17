package Model;

public class Cafedra extends Faculty {

    public Cafedra(int id, String name, int facultyId) {
        super(id, name);
        this.facultyId = facultyId;
    }

    private int facultyId;

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }
}

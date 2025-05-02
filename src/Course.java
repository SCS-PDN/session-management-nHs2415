package src;

public class Course {
    private String id;
    private String name;
    private String instructor;

    public Course(String id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course)) return false;
        Course other = (Course) obj;
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

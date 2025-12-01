import java.util.regex.Pattern;

// CO - 4, TOPIC - Transition from procedural to object-oriented logic
// CO - 4, TOPIC - Defining and using classes & objects
// CO - 5, TOPIC - Interfaces and multiple inheritance in Java
class Student extends Person implements Displayable {
    // CO - 4, TOPIC - Access specifiers and encapsulation
    private int rollNo;
    private float marks;
    private String email; // CO - 3, TOPIC - Regular Expressions (regex) - text validation (part of field)
    
    // CO - 4, TOPIC - Static data and methods
    private static int studentCount = 0;

    // CO - 4, TOPIC - Constructors
    public Student(int rollNo, String name, float marks, String email) {
        // CO - 4, TOPIC - Use of this keyword
        super(name, 20); // Default age
        this.rollNo = rollNo;
        this.marks = marks;
        this.email = email;
        studentCount++;
    }

    // CO - 4, TOPIC - Method overloading
    public Student(int rollNo, String name, int age, float marks, String email) {
        super(name, age);
        this.rollNo = rollNo;
        this.marks = marks;
        this.email = email;
        studentCount++;
    }

    // CO - 4, TOPIC - Access specifiers and encapsulation (getters/setters)
    public int getRollNo() {
         return rollNo;
         }
    public void setRollNo(int rollNo) {
         // CO - 4, TOPIC - Use of this keyword
         this.rollNo = rollNo; 
        }
    public float getMarks() { return marks; }
    public void setMarks(float marks) { 
        // CO - 4, TOPIC - Use of this keyword
        this.marks = marks; 
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // CO - 3, TOPIC - Regular Expressions (regex) - pattern matching and text validation
    public static boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // CO - 4, TOPIC - Static data and methods
    public static int getStudentCount() {
        return studentCount;
    }

    // CO - 5, TOPIC - Method overriding, super keyword
    // CO - 4, TOPIC - Method overloading (different parameters)
    @Override
    public void displayInfo() {
        System.out.printf("Roll No: %d, Name: %s, Age: %d, CGPA: %.2f, Email: %s%n", 
                          rollNo, getName(), getAge(), marks, email);
    }

    // CO - 5, TOPIC - Interfaces - implementing interface methods
    @Override
    public String getDisplayString() {
        return String.format("Student[RollNo=%d, Name=%s, CGPA=%.2f]", rollNo, getName(), marks);
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-10.2f %-25s", rollNo, getName(), marks, email);
    }
}
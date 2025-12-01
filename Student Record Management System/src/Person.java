// CO - 4, TOPIC - Defining and using classes & objects
// CO - 4, TOPIC - Access specifiers and encapsulation
public class Person {
    // CO - 4, TOPIC - Access specifiers and encapsulation (private fields)
    private String name;
    private int age;

    // CO - 4, TOPIC - Constructors
    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }

    // CO - 4, TOPIC - Constructors (overloaded constructor)
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // CO - 4, TOPIC - Access specifiers and encapsulation (public getters/setters)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // CO - 4, TOPIC - Methods, parameters, return types
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

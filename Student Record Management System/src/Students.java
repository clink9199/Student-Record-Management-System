import java.util.*;
import java.io.*;

public class Students {
    public static void main(String[] args) {
        // CO - 6, TOPIC - Java Collections Framework - List
        List<Student> studentList = new ArrayList<>();
        
        // CO - 6, TOPIC - Exception Handling: try-catch
        // Try to load existing data from file
        try {
            studentList = FileManager.loadStudentsFromFile();
        } catch (Exception e) {
            System.out.println("Starting with empty student list.");
        }
        
        // CO - 1, TOPIC - Input/output operations (Scanner)
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        // CO - 1, TOPIC - Iterative constructs: do-while
        do {
            System.out.println("\n--- Student CRUD Operations ---");
            System.out.println("1. INSERT Student");
            System.out.println("2. DISPLAY All Students");
            System.out.println("3. SEARCH Student by Roll No (Recursive Binary Search)");
            System.out.println("4. UPDATE Student");
            System.out.println("5. DELETE Student by Roll No");
            System.out.println("6. SORT Students by CGPA (Bubble Sort)");
            System.out.println("7. DISPLAY Student Count (Static Method)");
            System.out.println("8. SAVE to File (Text)");
            System.out.println("9. LOAD from File (Text)");
            System.out.println("10. EXPORT to Binary File");
            System.out.println("11. IMPORT from Binary File");
            System.out.println("0. EXIT");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }
            // CO - 1, TOPIC - Control flow: switch-case
            switch (choice) {
                case 1 -> insertStudent(studentList, scanner);
                case 2 -> displayStudents(studentList);
                case 3 -> searchStudent(studentList, scanner);
                case 4 -> updateStudent(studentList, scanner);
                case 5 -> deleteStudent(studentList, scanner);
                case 6 -> bubbleSortStudents(studentList);
                case 7 -> displayStudentCount(); // CO - 4, TOPIC - Static data and methods
                case 8 -> saveToFile(studentList); // CO - 6, TOPIC - File handling
                case 9 -> studentList = loadFromFile(); // CO - 6, TOPIC - File handling
                case 10 -> exportToBinary(studentList); // CO - 6, TOPIC - File handling: byte stream
                case 11 -> studentList = importFromBinary(); // CO - 6, TOPIC - File handling: byte stream
                case 0 -> {
                    System.out.println("Exiting...");
                    // Auto-save before exit
                    try {
                        FileManager.saveStudentsToFile(studentList);
                    } catch (Exception e) {
                        System.out.println("Could not save data: " + e.getMessage());
                    }
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    // CO - 1, TOPIC - Debugging and error tracing techniques
    public static void insertStudent(List<Student> list, Scanner sc) {
        try {
            System.out.print("Enter Student Roll No: ");
            int rollNo = sc.nextInt();
            sc.nextLine();

            boolean exists = list.stream().anyMatch(stu -> stu.getRollNo() == rollNo);
            if (exists) {
                System.out.println("A student with this roll number already exists!");
                return;
            }

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Error: Name cannot be empty!");
                return;
            }

            System.out.print("Enter Student CGPA: ");
            float marks = sc.nextFloat();
            sc.nextLine(); // Consume newline

            if (marks < 0.0 || marks > 10.0) {
                System.out.println("Error: CGPA must be between 0.0 and 10.0!");
                return;
            }

            // CO - 3, TOPIC - Regular Expressions (regex) - text validation
            String email;
            int attempts = 0;
            while (attempts < 3) {
                System.out.print("Enter Student Email: ");
                email = sc.nextLine().trim();
                if (Student.validateEmail(email)) {
                    list.add(new Student(rollNo, name, marks, email));
                    System.out.println("Student inserted successfully!");
                    return;
                } else {
                    System.out.println("Invalid Email! Please enter a valid email format.");
                    attempts++;
                }
            }
            System.out.println("Error: Maximum attempts exceeded. Student not added.");

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input type! Please enter correct data types.");
            sc.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred - " + e.getMessage());
            sc.nextLine();
        }
    }

    public static void displayStudents(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        // CO - 3, TOPIC - String vs StringBuilder vs StringBuffer
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-20s %-10s %-25s%n", "Roll No", "Name", "CGPA", "Email"));
        sb.append("----------------------------------------------------------------------\n");
        for (Student stu : list) {
            sb.append(stu.toString()).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void searchStudent(List<Student> list, Scanner sc) {
        try {
            if (list.isEmpty()) {
                System.out.println("No students in the system to search!");
                return;
            }

            System.out.print("Enter Roll No to search: ");
            int rollNo = sc.nextInt();
            sc.nextLine(); // Consume newline

            // Sort by Roll No first for Binary Search (Selection Sort Helper)
            selectionSortByRollNo(list);

            // CO - 2, TOPIC - Searching techniques - Binary search
            // CO - 3, TOPIC - Recursion fundamentals
            int index = recursiveBinarySearch(list, 0, list.size() - 1, rollNo);

            if (index != -1) {
                System.out.println("Student found:");
                System.out.printf("%-10s %-20s %-10s %-25s%n", "Roll No", "Name", "CGPA", "Email");
                System.out.println(list.get(index));
            } else {
                System.out.println("Student not found!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input! Please enter a valid roll number.");
            sc.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred - " + e.getMessage());
        }
    }

    // CO - 3, TOPIC - Recursion fundamentals
    public static int recursiveBinarySearch(List<Student> list, int left, int right, int target) {
        if (left <= right) {
            // CO - 3, TOPIC - Bitwise operators (optimization for division by 2)
            int mid = (left + right) >>> 1;

            if (list.get(mid).getRollNo() == target) {
                return mid;
            }
            if (list.get(mid).getRollNo() > target) {
                return recursiveBinarySearch(list, left, mid - 1, target);
            }
            return recursiveBinarySearch(list, mid + 1, right, target);
        }
        return -1;
    }

    // Helper for Search
    public static void selectionSortByRollNo(List<Student> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (list.get(j).getRollNo() < list.get(min_idx).getRollNo())
                    min_idx = j;

            Student temp = list.get(min_idx);
            list.set(min_idx, list.get(i));
            list.set(i, temp);
        }
    }

    // CO - 2, TOPIC - Sorting techniques - Bubble Sort
    public static void bubbleSortStudents(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("No students to sort!");
            return;
        }
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getMarks() < list.get(j + 1).getMarks()) { // Descending order of marks
                    // Swap
                    Student temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted by CGPA (Descending) using Bubble Sort!");
        displayStudents(list);
    }

    public static void updateStudent(List<Student> list, Scanner sc) {
        try {
            if (list.isEmpty()) {
                System.out.println("No students in the system to update!");
                return;
            }

            System.out.print("Enter Roll No to update: ");
            int rollNo = sc.nextInt();
            sc.nextLine();

            for (Student stu : list) {
                if (stu.getRollNo() == rollNo) {
                    System.out.print("Enter new Name: ");
                    String newName = sc.nextLine().trim();
                    if (newName.isEmpty()) {
                        System.out.println("Error: Name cannot be empty! Update cancelled.");
                        return;
                    }

                    System.out.print("Enter new CGPA: ");
                    float newMarks = sc.nextFloat();
                    sc.nextLine(); // Consume newline

                    if (newMarks < 0.0 || newMarks > 10.0) {
                        System.out.println("Error: CGPA must be between 0.0 and 10.0! Update cancelled.");
                        return;
                    }

                    System.out.print("Enter new Email: ");
                    String newEmail = sc.nextLine().trim();

                    if (!Student.validateEmail(newEmail)) {
                        System.out.println("Invalid email. Update failed.");
                        return;
                    }

                    stu.setName(newName);
                    stu.setMarks(newMarks);
                    stu.setEmail(newEmail);
                    System.out.println("Student updated successfully!");
                    return;
                }
            }
            System.out.println("Student not found!");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input type! Please enter correct data types.");
            sc.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred - " + e.getMessage());
        }
    }

    public static void deleteStudent(List<Student> list, Scanner sc) {
        try {
            if (list.isEmpty()) {
                System.out.println("No students in the system to delete!");
                return;
            }

            System.out.print("Enter Roll No to delete: ");
            int rollNo = sc.nextInt();
            sc.nextLine(); // Consume newline

            boolean deleted = list.removeIf(stu -> stu.getRollNo() == rollNo);
            if (deleted) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input! Please enter a valid roll number.");
            sc.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred - " + e.getMessage());
        }
    }

    // CO - 4, TOPIC - Static data and methods
    public static void displayStudentCount() {
        System.out.println("Total number of students: " + Student.getStudentCount());
    }

    // CO - 6, TOPIC - File handling: Reading/writing files
    // CO - 6, TOPIC - Exception Handling: try-catch-finally
    public static void saveToFile(List<Student> list) {
        try {
            FileManager.saveStudentsToFile(list);
        } catch (Exception e) {
            System.out.println("Failed to save to file: " + e.getMessage());
        }
    }

    // CO - 6, TOPIC - File handling: Reading/writing files
    // CO - 6, TOPIC - Exception Handling: try-catch, Custom exception classes
    public static List<Student> loadFromFile() {
        try {
            return FileManager.loadStudentsFromFile();
        } catch (InvalidStudentDataException e) {
            System.out.println("Invalid data in file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Failed to load from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // CO - 6, TOPIC - File handling: byte stream
    public static void exportToBinary(List<Student> list) {
        try {
            FileManager.exportStudentsBinary(list);
        } catch (Exception e) {
            System.out.println("Failed to export to binary file: " + e.getMessage());
        }
    }

    // CO - 6, TOPIC - File handling: byte stream
    public static List<Student> importFromBinary() {
        try {
            return FileManager.importStudentsBinary();
        } catch (Exception e) {
            System.out.println("Failed to import from binary file: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
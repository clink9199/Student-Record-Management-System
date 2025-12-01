import java.io.*;
import java.util.*;

// CO - 6, TOPIC - File handling: Reading/writing files using FileInputStream, FileOutputStream, FileReader, FileWriter
// CO - 6, TOPIC - Exception Handling: try-catch-finally, throw and throws
public class FileManager {
    private static final String FILE_PATH = "students_data.txt";

    // CO - 6, TOPIC - Reading/writing files using FileReader, FileWriter, and buffered streams
    // CO - 6, TOPIC - Exception Handling: throws
    public static void saveStudentsToFile(List<Student> students) throws IOException {
        // CO - 6, TOPIC - File handling: buffered streams
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                // Format: rollNo|name|age|marks|email
                String line = String.format("%d|%s|%d|%.2f|%s",
                        student.getRollNo(),
                        student.getName(),
                        student.getAge(),
                        student.getMarks(),
                        student.getEmail());
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Students data saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
            // CO - 6, TOPIC - Exception Handling: throw
            throw e;
        }
    }

    // CO - 6, TOPIC - Reading/writing files using FileReader, FileWriter, and buffered streams
    // CO - 6, TOPIC - Exception Handling: throws
    public static List<Student> loadStudentsFromFile() throws IOException, InvalidStudentDataException {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("No saved data found. Starting with empty list.");
            return students;
        }

        // CO - 6, TOPIC - File handling: buffered streams
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    String[] parts = line.split("\\|");
                    if (parts.length != 5) {
                        // CO - 6, TOPIC - Custom exception classes, throw
                        throw new InvalidStudentDataException("Invalid data format at line " + lineNumber);
                    }

                    int rollNo = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    float marks = Float.parseFloat(parts[3]);
                    String email = parts[4];

                    students.add(new Student(rollNo, name, age, marks, email));
                } catch (NumberFormatException e) {
                    System.out.println("Warning: Skipping invalid data at line " + lineNumber);
                } catch (InvalidStudentDataException e) {
                    System.out.println("Warning: " + e.getMessage());
                }
            }
            System.out.println("Students data loaded from file successfully!");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            throw e;
        }

        return students;
    }

    // CO - 6, TOPIC - File handling: byte stream
    public static void exportStudentsBinary(List<Student> students) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("students_data.bin")))) {
            
            dos.writeInt(students.size());
            for (Student student : students) {
                dos.writeInt(student.getRollNo());
                dos.writeUTF(student.getName());
                dos.writeInt(student.getAge());
                dos.writeFloat(student.getMarks());
                dos.writeUTF(student.getEmail());
            }
            System.out.println("Students data exported to binary file successfully!");
        }
    }

    // CO - 6, TOPIC - File handling: byte stream
    public static List<Student> importStudentsBinary() throws IOException {
        List<Student> students = new ArrayList<>();
        File file = new File("students_data.bin");

        if (!file.exists()) {
            System.out.println("No binary data file found.");
            return students;
        }

        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream("students_data.bin")))) {
            
            int count = dis.readInt();
            for (int i = 0; i < count; i++) {
                int rollNo = dis.readInt();
                String name = dis.readUTF();
                int age = dis.readInt();
                float marks = dis.readFloat();
                String email = dis.readUTF();
                
                students.add(new Student(rollNo, name, age, marks, email));
            }
            System.out.println("Students data imported from binary file successfully!");
        }

        return students;
    }
}

// CO - 6, TOPIC - Custom exception classes
public class InvalidStudentDataException extends Exception {
    
    // CO - 4, TOPIC - Constructors (overloaded)
    public InvalidStudentDataException() {
        super("Invalid student data provided");
    }

    // CO - 4, TOPIC - Constructors (overloaded)
    public InvalidStudentDataException(String message) {
        super(message);
    }

    // CO - 4, TOPIC - Constructors (overloaded)
    public InvalidStudentDataException(String message, Throwable cause) {
        super(message, cause);
    }
}

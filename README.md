# Student Record Management System

A comprehensive **CLI-based Student Record Management System** built in Java that demonstrates core programming concepts including OOP, data structures, algorithms, file I/O, and exception handling. This project implements topics from **CO1 through CO6** of a Java programming curriculum.

---

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Course Outcomes Implemented](#course-outcomes-implemented)
- [Project Structure](#project-structure)
- [Installation & Setup](#installation--setup)
- [Usage](#usage)
- [Technical Highlights](#technical-highlights)
- [Contributors](#contributors)

---

## 🎯 Overview

The **Student Record Management System** is a Java-based application designed to manage student records efficiently. It provides CRUD operations (Create, Read, Update, Delete) with advanced features like sorting, searching, file persistence, and comprehensive exception handling.

### Key Information Managed:
- **Roll Number** (Unique identifier)
- **Name**
- **Age**
- **CGPA** (0.0 - 10.0)
- **Email** (with regex validation)

---

## ✨ Features

### Core Operations
1. **INSERT Student** - Add new student records with validation
2. **DISPLAY All Students** - View all students in a formatted table
3. **SEARCH Student** - Find students using recursive binary search
4. **UPDATE Student** - Modify existing student information
5. **DELETE Student** - Remove student records
6. **SORT Students** - Sort by CGPA using Bubble Sort algorithm
7. **DISPLAY Student Count** - Show total students using static methods

### File Operations
8. **SAVE to File** - Persist data to text file (students_data.txt)
9. **LOAD from File** - Retrieve data from text file
10. **EXPORT to Binary** - Export data to binary format (students_data.bin)
11. **IMPORT from Binary** - Import data from binary file

### Advanced Features
- ✅ **Email Validation** - Regex-based email format verification
- ✅ **Input Validation** - CGPA range checking, empty field prevention
- ✅ **Exception Handling** - Comprehensive error handling throughout
- ✅ **Auto-save on Exit** - Automatic data persistence
- ✅ **Auto-load on Startup** - Loads existing data automatically
- ✅ **Duplicate Prevention** - Prevents duplicate roll numbers

---

## 📚 Course Outcomes Implemented

### **CO1: Foundations of Logic Building & Control Structures**
- Input/output operations (Scanner)
- Iterative constructs (do-while loops)
- Control flow (switch-case, if-else)
- Debugging and error tracing techniques

### **CO2: 1D, 2D Arrays and Optimized Algorithm Design**
- Sorting techniques (Bubble Sort)
- Searching techniques (Binary Search)
- Algorithm optimization (Selection Sort helper)

### **CO3: Strings, Bitwise Operations, and Recursion**
- Regular Expressions (email validation)
- String vs StringBuilder vs StringBuffer
- Recursion fundamentals (recursive binary search)
- Bitwise operators (>>> for optimization)

### **CO4: Object-Oriented Programming**
- Classes and Objects
- Inheritance (Student extends Person)
- Encapsulation (private fields, public methods)
- Method overloading (multiple constructors)
- Static data and methods
- Use of `this` keyword
- Access specifiers

### **CO5: Inheritance, Polymorphism, Abstraction & Interfaces**
- Inheritance hierarchy (Person → Student)
- Method overriding with `@Override`
- Interface implementation (Displayable)
- Polymorphism (compile-time and runtime)

### **CO6: Exception Handling, File I/O & Collections**
- Custom exception classes (InvalidStudentDataException)
- Exception handling (try-catch-finally, throw, throws)
- File I/O (character and byte streams)
- BufferedReader, BufferedWriter, DataInputStream, DataOutputStream
- Java Collections Framework (ArrayList)

---

## 📁 Project Structure

```
Student Record Management System/
│
├── .vscode/
│   └── settings.json
│
├── bin/                                    ← Compiled .class files
│   ├── Displayable.class
│   ├── FileManager.class
│   ├── InvalidStudentDataException.class
│   ├── Person.class
│   ├── Student.class
│   └── Students.class
│
├── lib/                                    ← External libraries (if any)
│
├── src/                                    ← Source code
│   ├── Person.java                         ← Base class
│   ├── Student.java                        ← Student class (extends Person, implements Displayable)
│   ├── Students.java                       ← Main application class
│   ├── Displayable.java                    ← Interface
│   ├── InvalidStudentDataException.java    ← Custom exception
│   └── FileManager.java                    ← File I/O operations
│
├── students_data.txt                       ← Generated text file storage
├── students_data.bin                       ← Generated binary file storage
├── CO_IMPLEMENTATION_SUMMARY.md            ← Detailed CO implementation guide
└── README.md                               ← This file
```

---

## 🚀 Installation & Setup

### Prerequisites
- **Java Development Kit (JDK)** 11 or higher
- Command line terminal (Terminal on macOS/Linux, Command Prompt/PowerShell on Windows)

### Steps to Run

1. **Navigate to project directory:**
   ```bash
   cd "Student Record Management System"
   ```

2. **Compile the source code:**
   ```bash
   javac -d bin src/*.java
   ```

3. **Run the application:**
   ```bash
   java -cp bin Students
   ```

---

## 💻 Usage

### Main Menu
```
--- Student CRUD Operations ---
1. INSERT Student
2. DISPLAY All Students
3. SEARCH Student by Roll No (Recursive Binary Search)
4. UPDATE Student
5. DELETE Student by Roll No
6. SORT Students by CGPA (Bubble Sort)
7. DISPLAY Student Count (Static Method)
8. SAVE to File (Text)
9. LOAD from File (Text)
10. EXPORT to Binary File
11. IMPORT from Binary File
0. EXIT
Enter your choice:
```

### Example Workflow

1. **Insert a Student:**
   - Choose option `1`
   - Enter Roll No (e.g., 101)
   - Enter Name (e.g., John Doe)
   - Enter CGPA (e.g., 8.5)
   - Enter Email (e.g., john@example.com)

2. **Display All Students:**
   - Choose option `2`
   - View formatted table of all students

3. **Search for a Student:**
   - Choose option `3`
   - Enter Roll No to search
   - System automatically sorts and performs binary search

4. **Save Data:**
   - Choose option `8` to save to text file
   - Data persists in `students_data.txt`

5. **Exit:**
   - Choose option `0`
   - Data automatically saves before exit

---

## 🔧 Technical Highlights

### Design Patterns & Principles
- **Inheritance Hierarchy**: Person (base) → Student (derived)
- **Interface Segregation**: Displayable interface for display operations
- **Encapsulation**: Private fields with public accessor methods
- **Single Responsibility**: Separate classes for different concerns

### Algorithms Implemented
- **Bubble Sort**: O(n²) - Sorts students by CGPA
- **Binary Search**: O(log n) - Recursive implementation for searching
- **Selection Sort**: Helper for binary search preparation

### Exception Handling Strategy
- Custom exceptions for domain-specific errors
- Try-catch blocks for all user inputs
- Proper resource management with try-with-resources
- Meaningful error messages for users

### File Format
**Text File (students_data.txt):**
```
101|John Doe|20|8.50|john@example.com
102|Jane Smith|21|9.20|jane@example.com
```

**Binary File (students_data.bin):**
- Uses DataOutputStream/DataInputStream
- Compact binary representation
- Faster read/write operations

---

## 👥 Contributors

| Name | Role |
|------|------|
| **Shaik Abdul Arshad** | Testing & Documentation |
| **Sri Charan** | Backend Development |
| **Mohammed Faisal** | Backend & Testing |
| **Mohammed Zaid** | Backend Support |
| **G Jashwanth** | UI/UX & Testing |
| **MD Aman** | Testing Support & Documentation |

---

## 📄 License

This project is developed as part of an academic curriculum for educational purposes.

---

## 📞 Support

For issues or questions, please refer to the `CO_IMPLEMENTATION_SUMMARY.md` file for detailed implementation information.

# Task5 : Student Management System
* Overview
   The Student Management System (SMS) is a Java-based desktop application designed to efficiently manage student data. It provides an interactive Graphical User 
   Interface (GUI) built using Java Swing that allows users to add, view, edit, delete, and search student records. The application ensures data persistence by 
   storing information in a MySQL database, combining front-end design with robust back-end data handling.

* Objective
    1. The primary objective of this project is to:
    2. Provide a user-friendly interface for managing student records.
    3. Support CRUD operations (Create, Read, Update, Delete) for student data.
    4. Enable efficient search and filtering of student records.
    5. Implement data validation and error handling for accuracy.
    6. Demonstrate the integration of Java Swing with MySQL database using JDBC.
    7. This project aims to simplify manual record-keeping processes in academic institutions.

* Features
  
    1. Add Student – Input fields to add student details like ID, name, age, course, and grade.
    2. View Students – Displays all records in a tabular format using JTable.
    3. Edit Student – Update existing student details by selecting and modifying records.
    4. Delete Student – Remove student records from the system.
    5. Search Functionality – Search students by ID, name, or course for quick access.
    6. Data Validation – Ensures inputs are correct (e.g., age as a number, mandatory fields not empty).
    7. Database Integration – Uses MySQL for persistent data storage, allowing dynamic data updates.
    8. Exception Handling – Catches errors like SQL issues or invalid inputs gracefully.
    9. Interactive GUI – Clean, organized layout with panels, buttons, text fields, and tables.

* Technologies & Concepts Used
    1. Component/Concept	Purpose
    2. Java Swing	GUI components: JFrame, JPanel, JTable, etc.
    3. MySQL Database	Stores student records persistently
    4. JDBC (Java Database Connectivity)	Connects Java application with MySQL database
    5. CRUD Operations	Add, view, update, delete student records
    6. JTable	Displays tabular data dynamically
    7. Event Handling	Handles user actions like button clicks
    8. Exception Handling	Manages errors (SQL, invalid inputs)
    9. Object-Oriented Programming	Classes, methods, objects for code structure

* Working of the Application
    1. User launches the application (main JFrame).
    2. Add Student: User fills the form and clicks 'Add'; data is validated and inserted into the database.
    3️. View Students: All records are fetched from the database and displayed in a JTable.
    4️. Edit Student: User selects a record, updates fields, and clicks 'Update'; the system updates the database.
    5️. Delete Student: User selects a record and clicks 'Delete'; the system removes it from the database.
    6️. Search Student: User enters a search keyword, and matching records are displayed.
    7️. Clear Fields: Resets form fields for a new entry.
    8️. Error Handling: Alerts user if inputs are invalid or database errors occur.

* Database Design (MySQL)
    1. Column Name	Data Type	Description
    2. id	INT (PK)	Unique Student ID
    3. name	VARCHAR	Student Name
    4. age	INT	Student Age
    5. course	VARCHAR	Student Course
    6. grade	VARCHAR	Student Grade/Result

* Overview of the frame-


  
![sms](https://github.com/user-attachments/assets/16d791a3-285b-436c-b0f7-13dab3ab093d)

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

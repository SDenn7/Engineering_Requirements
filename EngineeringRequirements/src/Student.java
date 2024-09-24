/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author samanthadennison <your.name your.org>
 */
public class Student {
    private final String studentID;
    private final String firstName;
    private final String lastName;
    private final String status;

    public Student(String studentID, String firstName, String lastName, String status) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStatus() {
        return status;
    }
    
}

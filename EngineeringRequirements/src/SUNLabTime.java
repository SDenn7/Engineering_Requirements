/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author samanthadennison <your.name your.org>
 */
public class SUNLabTime {
    private final String studentID;
    private final String status;
    private final java.sql.Timestamp timestamp;

    public SUNLabTime(String studentID, String status, java.sql.Timestamp timestamp) {
        this.studentID = studentID;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStatus() {
        return status;
    }

    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }
    
    
    
}

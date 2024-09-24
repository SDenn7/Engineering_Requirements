
import java.util.Calendar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author samanthadennison <your.name your.org>
 */
public class SUNLab {
    private final String studentID;
    private final String status;
    java.sql.Timestamp currentTime = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

    public SUNLab(String studentID, String status){
        this.studentID = studentID;
        this.status = status;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStatus() {
        return status;
    }
    

    public java.sql.Timestamp getCurrentTimestamp() {
        return currentTime;
    }
        
}

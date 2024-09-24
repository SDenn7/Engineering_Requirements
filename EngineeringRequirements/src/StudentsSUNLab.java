/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author samanthadennison <your.name your.org>
 */
public class StudentsSUNLab {
    
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement allStudents;
    private static PreparedStatement updateStudent;
    private static ResultSet resultSet;
    
    // This adds a student to the student table
    public static void addStudent(Student student){
        connection = ConnectDB.getConnection();
        
        // Gathers the fields from the student object
        String studentID = student.getStudentID();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        String status = student.getStatus();
        
        try{
            // Creates a connection to the student table
            addStudent = connection.prepareStatement("insert into app.student (studentID, firstName, lastName, status) values (?,?,?,?)");
            // Inserts the fields from the student object into a row of the student table
            addStudent.setString(1, studentID);
            addStudent.setString(2, firstName);
            addStudent.setString(3, lastName);
            addStudent.setString(4, status);
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
          
        }
        
        
    }
    
    public static ArrayList<Student> allStudents(){
        connection = ConnectDB.getConnection();
        // Creates an empty array of student objects to list all students 
        ArrayList<Student> students = new ArrayList<Student>();
        
        try{
            // Connects to the student table
            allStudents = connection.prepareStatement("select * from app.student order by studentID");
            resultSet = allStudents.executeQuery();
            
            // Iterates through the rows of the student table
            while(resultSet.next()){
                // Collects the information from a row into a student object
                students.add(new Student(
                    resultSet.getString("studentID"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("status")));
            }
         
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        // Returns a list of students, listed numerically by student ID
        return students;
    }
    
    public static void updateStudent(Student student, String newStatus){
        connection = ConnectDB.getConnection();
        
        // Gathers the fields from the student object
        String studentID = student.getStudentID();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        String status = student.getStatus();
        
        try{
            // Connects to the student table
            updateStudent = connection.prepareStatement("update app.student set status = ? where studentID = ? and firstName = ? and lastName = ? and status = ?");
            // Updates the status, keeps all other fields the same
            updateStudent.setString(1, newStatus);
            updateStudent.setString(2, studentID);
            updateStudent.setString(3, firstName);
            updateStudent.setString(4, lastName);
            updateStudent.setString(5, status);
            updateStudent.executeUpdate();
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
    
    
}

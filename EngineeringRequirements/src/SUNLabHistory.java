/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author samanthadennison <your.name your.org>
 */
public class SUNLabHistory {
    private static Connection connection;
    private static PreparedStatement addSunLabHistory;
    private static PreparedStatement allSunLabHistory;
    private static PreparedStatement sortedSunLabHistory;
    private static PreparedStatement timeRangeSunLabHistory;
    private static PreparedStatement deleteSunLabHistory;
    private static ResultSet resultSet;
    
    public static void addSunLabHistory(SUNLab sun){
        connection = connection = ConnectDB.getConnection();
        
        String studentID = sun.getStudentID();
        String status = sun.getStatus();
        java.sql.Timestamp timestamp = sun.getCurrentTimestamp();
        
        try{
            // Creates a connection to the SUNLab table
            addSunLabHistory = connection.prepareStatement("insert into app.sunLab (studentID, status, timestamp) values (?,?,?)");
            // Inserts the fields from the SUNLab object into a row of the SUNLab table
            addSunLabHistory.setString(1, studentID);
            addSunLabHistory.setString(2, status);
            //addSunLabHistory.setBoolean(3, checkedIn);
            addSunLabHistory.setTimestamp(3, timestamp);
            addSunLabHistory.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
        
    }
    
    public static ArrayList<SUNLab> allSunLabHistory(){
        connection = ConnectDB.getConnection();
        // Creates an empty array of SUNLab objects to list all SUNLab objects 
        ArrayList<SUNLab> history = new ArrayList<SUNLab>();
        
        try{
            // Connects to the SUNLab table
            allSunLabHistory = connection.prepareStatement("select * from app.sunLab order by studentID");
            resultSet = allSunLabHistory.executeQuery();
            
            // Iterates through the rows of the SUNLab table
            while(resultSet.next()){
                // Collects the information from a row into a SUNLab object
                history.add(new SUNLab(
                    resultSet.getString("studentID"),
                    resultSet.getString("status")));
            }
         
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        // Returns a list of SUNLab objects, listed numerically by student ID
        return history;
    }
    
    public static ArrayList<SUNLabTime> sortedSunLabHistory(String sortBy){
        connection = ConnectDB.getConnection();
        // Creates an empty array of SUNLabTime objects to list all SUNLabTime objects 
        ArrayList<SUNLabTime> history = new ArrayList<SUNLabTime>();
        
        try{
            // Sorts student ID in descending order
            if (sortBy.equals("Descending")){
                sortedSunLabHistory = connection.prepareStatement("select * from app.sunLab order by studentID desc");
            }
            // Sorts student ID in ascending order
            if (sortBy.equals("Ascending") || sortBy.equals("---")){
                sortedSunLabHistory = connection.prepareStatement("select * from app.sunLab order by studentID");
            }
            
            // Sorts timestamp by most recent
            if (sortBy.equals("Most Recent")){
                sortedSunLabHistory = connection.prepareStatement("select * from app.sunLab order by timestamp desc");
            }
            // Sorts timestamp by oldest
            if (sortBy.equals("Oldest")){
                sortedSunLabHistory = connection.prepareStatement("select * from app.sunLab order by timestamp");
            }
            
            
            resultSet = sortedSunLabHistory.executeQuery();
            
            // Iterates through the rows of the SUNLab table
            while(resultSet.next()){
                // Collects the information from a row into a SUNLab object
                history.add(new SUNLabTime(
                    resultSet.getString("studentID"),
                    resultSet.getString("status"),
                    resultSet.getTimestamp("timestamp")));
            }
         
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        // Returns a list of SUNLabTime objects, listed by sorting style
        return history;
    }
    
    public static ArrayList<SUNLabTime> timeRangeSunLabHistory(String day, String month, String year, String dayTo, String monthTo, String yearTo){
        connection = ConnectDB.getConnection();
        
        // Creates an empty array of SUNLab objects to list all SUNLab objects 
        ArrayList<SUNLabTime> history = new ArrayList<SUNLabTime>();
        int dayInt = 0;
        int monthInt = 0;
        int dayIntTo = 0;
        int monthIntTo = 0;
        // Converts string parameters to integers
        if (!day.equals("None")){
            dayInt = Integer.valueOf(day);
        }
        if (!month.equals("None")){
            monthInt = Integer.valueOf(month);
        }
        int yearInt = Integer.valueOf(year);
        if (!dayTo.equals("None")){
            dayIntTo = Integer.valueOf(dayTo);
        }
        if (!monthTo.equals("None")){
            monthIntTo = Integer.valueOf(monthTo);
        }
        int yearIntTo = Integer.valueOf(yearTo);
        if ((dayTo.equals("None")) && (!monthTo.equals("None")) &&
                (day.equals("None")) && (!month.equals("None"))){
            ArrayList <String> dayToList = Time.days(yearTo, monthTo);

            dayIntTo = Integer.valueOf(dayToList.get(dayToList.size()-1));

            dayInt = 1;
        }
        else if ((monthTo.equals("None")) && (month.equals("None"))){
            dayIntTo = 31;
            monthIntTo = 12;
            dayInt = 1;
            monthInt = 1;
            
        }
        Calendar fromDate = Calendar.getInstance();
        fromDate.set(yearInt, (monthInt-1), dayInt);
        fromDate.set(Calendar.HOUR_OF_DAY, 0);
        fromDate.set(Calendar.MINUTE, 0);
        fromDate.set(Calendar.SECOND, 0);
        
        Calendar toDate = Calendar.getInstance();

        toDate.set(yearIntTo, (monthIntTo-1), dayIntTo);
        toDate.set(Calendar.HOUR_OF_DAY, 23);
        toDate.set(Calendar.MINUTE, 59);
        toDate.set(Calendar.SECOND, 59);
        
        try{
            // Connects to the SUNLab table
            timeRangeSunLabHistory = connection.prepareStatement("select * from app.sunLab order by timestamp");
            resultSet = timeRangeSunLabHistory.executeQuery();
            
            // Iterates through the rows of the SUNLab table
            while(resultSet.next()){
                // Gets the timestamp from the table
                java.sql.Timestamp time = resultSet.getTimestamp("timestamp");
                // Sets the timestamp to the time
                Calendar cal = Calendar.getInstance();
                cal.setTime(time);
                // Finds the year from the timestamp
                int timestampYear = (cal.get(Calendar.YEAR));
                
                
                
                // If only year is selected
                if ((month.equals("None")) && (day.equals("None"))){
                    // Adds SUNLabTime object to array if timestamp matches year
                    if ((timestampYear >= yearInt) && (timestampYear <= yearIntTo)){
                        history.add(new SUNLabTime(
                            resultSet.getString("studentID"),
                            resultSet.getString("status"),
                            resultSet.getTimestamp("timestamp")));
                    }
                }
                
                // If only month and year are selected
                else if (day.equals("None")){
                    // Adds SUNLabTime object if month and year match timestamp
                    if (cal.before(toDate) && cal.after(fromDate)){
                        history.add(new SUNLabTime(
                            resultSet.getString("studentID"),
                            resultSet.getString("status"),
                            resultSet.getTimestamp("timestamp")));
                    }
                    
                }
                
                // If day, month, and year are selected
                else if ((!month.equals("None")) && (!day.equals("None")) && (!year.equals("None"))){
                    // Adds SUNLabTime object if day, month, and year match timestamp
                    if (cal.before(toDate) && cal.after(fromDate)){
                        
                        history.add(new SUNLabTime(
                            resultSet.getString("studentID"),
                            resultSet.getString("status"),
                            resultSet.getTimestamp("timestamp")));
                    }
                    
                    
                }
            }
                
        }
         
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        // Returns a list of SUNLabTime objects, listed by timestamp
        return history;
    }
    
    public static void deleteSunLabHistory(){
        connection = connection = ConnectDB.getConnection();
        
        // Number of years records are stored
        int numOfYears = 5;
        // Set fiveYearsAgo to date five years ago
        Calendar fiveYearsAgo = Calendar.getInstance();
        fiveYearsAgo.add(Calendar.YEAR, -numOfYears);
        
        try{
            deleteSunLabHistory = connection.prepareStatement("select * from app.sunLab order by studentID");
            resultSet = deleteSunLabHistory.executeQuery();
            
            
            while(resultSet.next()){
                // Gets the timestamp from the table
                String id = resultSet.getString("studentID");
                String status = resultSet.getString("status");
                java.sql.Timestamp time = resultSet.getTimestamp("timestamp");
                
                // Sets the timestamp to the time
                Calendar cal = Calendar.getInstance();
                cal.setTime(time);

                // If the timestamp is from 5 years ago, the entry is deleted from the table
                if (cal.before(fiveYearsAgo) == true){
                    deleteSunLabHistory = connection.prepareStatement("delete from app.sunLab where studentID = ? and status = ? and timestamp = ?");
                    // Deletes the entry matching the timestamp
                    deleteSunLabHistory.setString(1, id);
                    deleteSunLabHistory.setString(2, status);
                    deleteSunLabHistory.setTimestamp(3, time);
                    deleteSunLabHistory.executeUpdate();
                }
            }

            
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
}

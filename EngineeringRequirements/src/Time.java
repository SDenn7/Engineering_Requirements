
import java.util.ArrayList;
import java.util.Calendar;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author samanthadennison <your.name your.org>
 */
public class Time {
    
    // Returns arrayList of all valid years
    public static ArrayList<String> years(){
        // Find today's year
        int year = Calendar.getInstance().get(Calendar.YEAR);
        // Number of years records are stored
        int numOfYears = 5;

        // Create an array with all valid years
        ArrayList<String> yearList = new ArrayList<String>();
        yearList.add("- Select Year -");
        for (int i = year; i>=(year-numOfYears); i--){
            yearList.add(Integer.toString(i));
        }
        
        
        return yearList;
    }
    
    // Returns arrayList of all months
    public static ArrayList<String> months(){
        ArrayList<String> monthList = new ArrayList<String>();
        monthList.add("- Select Month -");
        for (int i = 1; i<=12; i++){
            monthList.add(Integer.toString(i));
        }
        return monthList;
    }
    
    // Returns arrayList of all days in a month
    public static ArrayList<String> days(String year, String month){
        ArrayList<String> dayList = new ArrayList<String>();
        dayList.add("- Select Day -");
        if (year.equals("- Select Year -")){
            year = "1";
        }
        int currentYear = Integer.valueOf(year);
        int numOfDays = 0;
        
        // If the month has 30 days
        if (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")) {
            numOfDays = 30;
        }
        // If the month is February
        else if (month.equals("2")){
            // If it is a leap year, there are 29 days
            if (currentYear%4 == 0){
                numOfDays = 29;
            }
            else{
                numOfDays = 28;
            }
        }

        else{
            numOfDays = 31;
        }
        
        
        for (int i = 1; i <= numOfDays; i++){
            dayList.add(Integer.toString(i));
        }
        
        return dayList;
        
    }
    
    
}

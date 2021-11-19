package com.ahmadZufarJsmartMH;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Complaint here.
 *
 * @author Zufar
 * @version 02/10/2021
 */
public class Complaint extends Serializable
{  
    public Date date;
    public String desc;
    
    public Complaint(String desc){
        this.date = new Date();
        this.desc = desc;
    }
    
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String theDate = format.format(date);
        return "Complaint{date=" + theDate + ", desc='" + desc + "'}";
    }
       
}

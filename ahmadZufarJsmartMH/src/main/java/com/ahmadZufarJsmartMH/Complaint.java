package com.ahmadZufarJsmartMH;
import com.ahmadZufarJsmartMH.dbjson.Serializable;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Merupakan Class Complaint untuk mengatur complaint pada Jmart
 *
 * @author Zufar
 * @version 19/12/2021
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

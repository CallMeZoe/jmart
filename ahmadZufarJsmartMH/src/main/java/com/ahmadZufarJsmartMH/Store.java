package com.ahmadZufarJsmartMH;
import com.ahmadZufarJsmartMH.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Merupakan Class Store sebagai store pada Jmart
 *
 * @author Zufar
 * @version 19/12/2021
 */
public class Store extends Serializable
{
    public String name;
    public String address;
    public String phoneNumber;
    public double balance;
    public static final String REGEX_PHONE = "^\\d{9,12}$";
    public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    
    public Store(String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address= address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    
    public String toString() {
        return "name: " + this.name + 
                "\naddress: " + this.address + 
                "\nphoneNumber: " + this.phoneNumber;
    }
    
    public boolean validate(){
        Pattern REGEX_NAME = Pattern.compile(this.REGEX_NAME);
        Matcher NamePattern = REGEX_NAME.matcher(this.name);
        boolean namePattern = NamePattern.find();
        Pattern REGEX_PHONE = Pattern.compile(this.REGEX_PHONE);
        Matcher NumberPattern = REGEX_PHONE.matcher(this.name);
        boolean numberPattern = NumberPattern.find();
        
        if(namePattern == true && numberPattern == true){
            return true;
        }
        else{
            return false;
        }
    }

}

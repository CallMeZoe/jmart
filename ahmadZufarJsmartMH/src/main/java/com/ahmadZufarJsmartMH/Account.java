package com.ahmadZufarJsmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a description of class Account here.
 *
 * @author Zufar
 * @version 27/09/2021
 */
public class Account extends Serializable
{
    public String name;
    public String email;
    public String password;
    public Store store;
    public double balance;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9][a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
    
    public Account(String name, String email, String password, double balance)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }
    
    public String toString() {
        return "name: " + this.name + 
                "\nemail: " + this.email +
                "\npassword: " + this.password;
    }
    
    public boolean validate(){
        Pattern REGEX_EMAIL = Pattern.compile(this.REGEX_EMAIL);
        Matcher EmailPattern = REGEX_EMAIL.matcher(this.email);
        boolean emailPattern = EmailPattern.find();
        Pattern REGEX_PASSWORD = Pattern.compile(this.REGEX_PASSWORD);
        Matcher PassPattern = REGEX_PASSWORD.matcher(this.password);
        boolean passPattern = PassPattern.find();
        
        if(emailPattern == true && passPattern == true){
            return true;
        }
        else{
            return false;
        }
    }

}

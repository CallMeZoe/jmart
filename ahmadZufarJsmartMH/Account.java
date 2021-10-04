package ahmadZufarJsmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a description of class Account here.
 *
 * @author Zufar
 * @version 27/09/2021
 */
public class Account extends Recognizable implements FileParser
{
    public String name;
    public String email;
    public String password;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9][a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
    
    public Account(int id, String name, String email, String password)
    {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    /*public String toString() {
        return "name: " + this.name + 
                "\nemail: " + this.name + 
                "\npassword: " + this.password;
    }*/
    
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
    
    @Override
    public boolean read(String content)
    {
        return false;
    }
    
    
}

package ahmadZufarJsmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a description of class Store here.
 *
 * @author Zufar
 * @version 02/10/2021
 */

public class Store extends Recognizable implements FileParser
{
    public String name;
    public String address;
    public String phoneNumber;
    public static final String REGEX_PHONE = "^\\d{9,12}$";
    public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    
    public Store(int accountId, String name, String address, String phoneNumber){
        super(accountId);
        this.name = name;
        this.address= address;
        this.phoneNumber = phoneNumber;
    }
    
    public Store(Account account, String name, String address, String phoneNumber){
        super(account.id);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
    
    @Override
    public boolean read(String content){
        return false;
    }  
}

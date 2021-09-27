package ahmadZufarJsmartMH;


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
    
    public Account(int id, String name, String email, String password)
    {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString() {
        return "name: " + this.name + 
                "\nemail: " + this.name + 
                "\npassword: " + this.password;
    }
    
    @Override
    public boolean read(String content)
    {
        return false;
    }
    
    
}

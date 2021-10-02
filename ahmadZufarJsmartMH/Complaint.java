package ahmadZufarJsmartMH;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Complaint here.
 *
 * @author Zufar
 * @version 02/10/2021
 */
public class Complaint extends Recognizable implements FileParser
{  
    public Date date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.date = new Date();
        this.desc = desc;
    }
    
    @Override
    public boolean read(String content){
        return false;
    }
       
}

package ahmadZufarJsmartMH;


/**
 * Write a description of class Complaint here.
 *
 * @author Zufar
 * @version 27/09/2021
 */
public class Complaint extends Recognizable implements FileParser
{  
    public String date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.date = "Apapun";
        this.desc = desc;
    }
    
    @Override
    public boolean read(String content){
        return false;
    }
       
}

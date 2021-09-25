package ahmadZufarJsmartMH;


/**
 * Write a description of class Recognizable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Recognizable
{
    public final int id;

    protected Recognizable(int id)
    {
        this.id = id;
    }
    
    public boolean equals(Object obj)
    {
        if(obj instanceof Recognizable)
        {
            Recognizable recog = (Recognizable) obj;
            if (this.id == recog.id){
                return true;
            }
        }
        return false;
    }
}

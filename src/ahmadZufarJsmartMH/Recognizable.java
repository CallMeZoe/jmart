package ahmadZufarJsmartMH;


/**
 * Write a description of class Recognizable here.
 *
 * @author Zufar
 * @version 27/09/2021
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

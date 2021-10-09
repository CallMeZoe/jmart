package ahmadZufarJsmartMH;


/**
 * Write a description of class Recognizable here.
 *
 * @author Zufar
 * @version 27/09/2021
 */
public class Recognizable implements Comparable<Recognizable>
{
    public final int id;

    protected Recognizable(int id)
    {
        this.id = id;
    }

    @Override
    public int compareTo(Recognizable other) {
        if(id == other.id){
            return 1;
        }
        else{
            return 0;
        }
    }

    public static <T extends Recognizable> int getClosingId (Class<T> clazz){
        return 0;
    }

    public static <T extends Recognizable> int setClosingId (Class<T> clazz,int id){
        return 0;
    }

    public boolean equals(Object other){
        if(other instanceof Recognizable)
        {
            Recognizable recog = (Recognizable) other;
            return this.id == recog.id;
        }
        return false;
    }
}

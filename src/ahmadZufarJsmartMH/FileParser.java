package ahmadZufarJsmartMH;


/**
 * Write a description of interface FileParser here.
 *
 * @author Zufar
 * @version 27/09/2021
 */
public interface FileParser
{
    public boolean read(String content);
    
    default Object write() {
        return null;
    }
    
    public static Object newInstance(String content){
        return null;
    }
}
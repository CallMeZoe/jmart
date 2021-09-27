package ahmadZufarJsmartMH;


/**
 * Abstract class Transaction - write a description of the class here
 *
 * @author Zufar
 * @version 27/09/2021
 */
public interface Transactor
{
    public boolean validate();

    public Invoice perform();
}

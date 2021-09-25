package ahmadZufarJsmartMH;


/**
 * Abstract class Transaction - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
abstract class Transaction extends Recognizable
{
    enum Rating{
        NONE, BAD, NEUTRAL, GOOD;
    }
    
    public String time = "Time";
    public int buyerId;
    public int storeId;
    public Rating rating = Rating.NONE;
    
    protected Transaction(int id, int buyerId, int storeId){
        super(id);
        this.buyerId = buyerId;
        this.storeId = storeId;
    }
    
    protected Transaction(int id, Account buyer, Store store){
        super(id);
        this.buyerId = buyer.id;
        this.storeId = store.id;
    }
    
    public abstract boolean validate();
    
    public abstract Transaction perform();
    
}

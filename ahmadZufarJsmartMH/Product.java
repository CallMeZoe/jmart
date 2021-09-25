package ahmadZufarJsmartMH;


/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Product extends Recognizable implements FileParser
{
    public int id = 0;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    public int storeId;
    public Store store;
 
    public Product(int id, int storeId, String name, int weight, boolean 
    conditionUsed, PriceTag priceTag, ProductCategory category)
    {
        super(id);
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
    }
    
    public Product(int id, Store store, String name, int weight, boolean conditionUsed, PriceTag priceTag, ProductCategory category){
        super(id);
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = rating;
        this.store = store;
    }
    
    public boolean read(String content)
    {
        return false;
    }
    
    public Object write()
    {
        return null;
    }
    
    public static Object newInstance(String content)
    {
        return null;
    }
}

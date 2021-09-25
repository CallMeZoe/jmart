package ahmadZufarJsmartMH;


/**
 * Write a description of class Payment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Payment extends Transaction implements FileParser
{
    public int productId;
    public ShipmentDuration shipmentDuration;
    
    public Payment(int id, int buyerId, Product product, ShipmentDuration shipmentDuration)
    {
        super(id, buyerId, product.storeId);
        this.shipmentDuration = shipmentDuration;
        this.productId = product.id;
    }
    
    public Payment(int id, int buyerId, int storeId, int productId, ShipmentDuration shipmentDuration)
    {
        super(id, buyerId, storeId);
        this.productId = productId;
        this.shipmentDuration = shipmentDuration;
    }
    
    public boolean validate()
    {
        return false;
    }
    
    public Transaction perform()
    {
        return null;
    }
    
    @Override
    public boolean read(String content)
    {
        return false;
    }
    
    
}

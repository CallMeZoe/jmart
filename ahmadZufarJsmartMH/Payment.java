package ahmadZufarJsmartMH;


/**
 * Write a description of class Payment here.
 *
 * @author Zufar
 * @version 27/09/2021
 */
public class Payment extends Invoice implements Transactor
{
    public Shipment shipment;
    public int productCount;

    public Payment(int id, int buyerId, int productId, int productCount, Shipment shipment){
        super(id, buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    public boolean validate()
    {
        return false;
    }

    public Invoice perform() {
        return null;
    }

    public double getTotalPay() {
        return 0;
    }
}

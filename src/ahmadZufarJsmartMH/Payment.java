package ahmadZufarJsmartMH;


/**
 * Write a description of class Payment here.
 *
 * @author Zufar
 * @version 27/09/2021
 */
public class Payment extends Invoice
{
    public Shipment shipment;
    public int productCount;

    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }
    public double getTotalPay() {
        return 0;
    }
}

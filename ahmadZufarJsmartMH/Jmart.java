package ahmadZufarJsmartMH;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write a description of class Jmart here.
 *
 * @author Zufar
 * @version 02/10/2021
 */
public class Jmart
{
    public static void main(String args[])
    {
        System.out.println("Modul 4 CS_Ahmad Zufar A_1906300662");
        System.out.println(Shipment.Duration.KARGO.getEstimatedArrival(new Date())); 
    }
    
    

    /*public static Product create(){
        PriceTag priceTag = new PriceTag(80000);
        Product product = new Product("BUMI",500,false,priceTag,
        ProductCategory.BOOK);
        return product;
    }
    
    public static Product createProduct()
    {
        PriceTag priceTag = new PriceTag(80000);
        Product product = new Product("BUMI",500,false,priceTag,
        ProductCategory.BOOK);
        return product;
    }

    public static Coupon createCoupun()
    {
       Coupon coupon = new Coupon("BackToSchool",1, Coupon.Type.DISCOUNT, 10, 1000);
       return coupon;
    }

    public static ShipmentDuration createShipmentDuration (String args[])
    {
        return new ShipmentDuration(ShipmentDuration.INSTANT, ShipmentDuration.REGULER);
    }*/
}

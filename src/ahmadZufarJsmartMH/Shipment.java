package ahmadZufarJsmartMH;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Write a description of class Shipment here.
 *
 * @author Zufar
 * @version 02/10/2021
 */
public class Shipment
{
    
    public String address;
    public int shipmentCost;
    public byte plan;
    public String receipt;
    public static final Plan INSTANT = new Plan((byte) (1 << 0));
    public static final Plan SAME_DAY = new Plan((byte) (1 << 1));
    public static final Plan NEXT_DAY = new Plan((byte) (1 << 2));
    public static final Plan REGULER = new Plan((byte) (1 << 3));
    public static final Plan KARGO = new Plan((byte) (1 << 4));
    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("EEE MMMM dd yyyy");

    static class Plan {
        public final byte bit;
        private Plan(byte bit){
            this.bit = bit;
        }
    }

    public String getEstimatedArrival(Date reference){
        Calendar kalender = Calendar.getInstance();
        kalender.setTime(reference);
        if(plan == INSTANT.bit || plan == SAME_DAY.bit){
            kalender.add(Calendar.DATE, 0);}
        else if (plan == NEXT_DAY.bit)
            kalender.add(Calendar.DATE, 1);
        else if (plan == REGULER.bit)
            kalender.add(Calendar.DATE, 2);
        else if (plan == KARGO.bit)
            kalender.add(Calendar.DATE, 5);

        return ESTIMATION_FORMAT.format(kalender.getTime());
    }

    public boolean isDuration(Plan reference)
    {
        return (this.plan & reference.bit) == reference.bit;
    }

    public static boolean isDuration(byte object, Plan reference)
    {
        return (object & reference.bit) == reference.bit;
    }
    
    public Shipment(String address, int shipmentCost, byte plan, String receipt) {
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.plan = plan;
        this.receipt = receipt;
    }
    
}

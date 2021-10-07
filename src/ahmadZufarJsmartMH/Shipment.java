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
public class Shipment implements FileParser
{
    
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    
    public static class Duration
    {
        public static final Duration INSTANT = new Duration((byte) (1 << 0));
        public static final Duration SAME_DAY = new Duration((byte) (1 << 1));
        public static final Duration NEXT_DAY = new Duration((byte) (1 << 2));
        public static final Duration REGULER = new Duration((byte) (1 << 3));
        public static final Duration KARGO = new Duration((byte) (1 << 4));
        private final byte bit;
        public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("EEE MMMM dd yyyy");

        private Duration(byte bit){
            this.bit = bit;
        }
        
        public String getEstimatedArrival(Date reference){
            Calendar kalender = Calendar.getInstance();
            kalender.setTime(reference);
            if(bit == Duration.INSTANT.bit || bit == Duration.SAME_DAY.bit){
                kalender.add(Calendar.DATE, 0);}
            else if (bit == Duration.NEXT_DAY.bit)
                kalender.add(Calendar.DATE, 1);
            else if (bit == Duration.REGULER.bit)
                kalender.add(Calendar.DATE, 2);
            else if (bit == Duration.KARGO.bit)
                kalender.add(Calendar.DATE, 5);
        
            return ESTIMATION_FORMAT.format(kalender.getTime());
        }
    }
    
    static class MultiDuration {
        public byte bit;

        public MultiDuration(Duration... args){
            byte tmp = args[0].bit;

            for(int i=1; i<args.length; i++){
                tmp = (byte) (tmp | args[i].bit);
            }

            bit = tmp;
        }

        public boolean isDuration(Duration reference){
            return (bit & reference.bit) != 0;
        }
    }
    
    public Shipment(String address, int shipmentCost, Duration duration, String receipt) {
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.duration = duration;
        this.receipt = receipt;
    }
    
    @Override
    public boolean read(String content)
    {
        return false;
    }
    
}

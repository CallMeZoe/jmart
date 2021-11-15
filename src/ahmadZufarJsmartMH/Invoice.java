package ahmadZufarJsmartMH;
import java.util.Date;
import java.util.ArrayList;


/**
 * Write a description of class Invoice here.
 *
 * @author Zufar
 * @version 02/10/2021
 */
public abstract class Invoice extends Serializable {
    public final Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
//    public Status status;
//    public ArrayList<Record> history = new ArrayList<Record>();

    enum Rating {
        NONE, BAD, NEUTRAL, GOOD
    }

    enum Status {
        WAITING_CONFIRMATION, CANCELLED,
        ON_PROGRESS, ON_DELIVERY, COMPLAINT,
        FINISHED, FAILED, DELIVERED
    }
    
    class Record{
        public Status status;
        public Date date;
        public String message;
    }

    protected Invoice(int buyerId, int productId) {
        this.date = new Date();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.complaintId = -1;
//        this.status = Status.WAITING_CONFIRMATION;
    }

    public abstract double getTotalPay(Product product);
}

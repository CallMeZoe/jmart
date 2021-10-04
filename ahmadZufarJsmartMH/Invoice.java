package ahmadZufarJsmartMH;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;


/**
 * Write a description of class Invoice here.
 *
 * @author Zufar
 * @version 02/10/2021
 */
public abstract class Invoice extends Recognizable implements FileParser {
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    public ArrayList<Record> history = new ArrayList<Record>();

    enum Rating {
        NONE, BAD, NEUTRAL, GOOD
    }

    enum Status {
        WAITING_CONFIRMATION, CANCELLED,
        ON_PROGRESS, ON_DELIVERY, COMPLAINT,
        FINISHED, FAILED
    }
    
    class Record{
        public Status status;
        public Date date;
        public String message;
    }

    protected Invoice(int id, int buyerId, int productId) {
        super(id);
        this.date = new Date();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
    }

    @Override
    public boolean read(String content) {
        return false;
    }

    public abstract double getTotalPay();
}

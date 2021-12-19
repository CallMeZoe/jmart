package com.ahmadZufarJsmartMH;
import com.ahmadZufarJsmartMH.dbjson.Serializable;

import java.util.Calendar;
import java.util.Date;


/**
 * Merupakan Class Invoice untuk mengatur akses isi dari invoice
 *
 * @author Zufar
 * @version 19/12/2021
 */
public abstract class Invoice extends Serializable {
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Date date;

    public enum Rating {
        NONE, BAD, NEUTRAL, GOOD
    }

    public enum Status {
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED, DELIVERED
    }

    protected Invoice(int buyerId, int productId) {
        this.date = Calendar.getInstance().getTime();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.complaintId = -1;
    }

    public abstract double getTotalPay(Product product);

}

package com.ahmadZufarJsmartMH;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Write a description of class Payment here.
 *
 * @author Zufar
 * @version 27/09/2021
 */
public class Payment extends Invoice
{
    public ArrayList<Record> history = new ArrayList<>();
    public int productCount;
    public Shipment shipment;

    static class Record
    {
        public Status status;
        public final Date date;
        public String message;

        public Record(Status status, String message)
        {
            this.status = status;
            this.message = message;
            this.date = Calendar.getInstance().getTime();
        }
    }

    public Payment(int buyerId, int productId, int productCount, Shipment shipment)
    {
        super(buyerId, productId);
        this.shipment = shipment;
        this.productCount = productCount;
    }

    public double getTotalPay(Product product)
    {
        return productCount * product.price;
    }
}

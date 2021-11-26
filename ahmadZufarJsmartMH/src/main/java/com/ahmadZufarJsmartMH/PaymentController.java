package com.ahmadZufarJsmartMH;

import com.ahmadZufarJsmartMH.controller.AccountController;
import com.ahmadZufarJsmartMH.controller.BasicGetController;
import com.ahmadZufarJsmartMH.controller.ProductController;
import com.ahmadZufarJsmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;

public class PaymentController implements BasicGetController<Payment> {
    public static final long DELIVERED_LIMIT_MS = 0;
    public static final long ON_DELIVERY_LIMIT_MS = 0;
    public static final long ON_PROGRESS_LIMIT_MS = 0;
    public static final long WAITING_CONF_LIMIT_MS = 0;
    public static JsonTable<Payment> paymentTable = null;
    public static ObjectPoolThread<Payment> poolThread = null;

    static {
        poolThread = new ObjectPoolThread<Payment>("Thread", PaymentController::timekeeper);
        poolThread.start();
    }

    @PostMapping("/{id}/accept")
    public boolean accept(@PathVariable int id) {
        Payment payment = null;

        for (int i = 0; i < paymentTable.size(); i++) {
            Payment paymentTemp = paymentTable.get(i);
            if (paymentTemp.id == id) {
                payment = paymentTemp;
            }
        }

        if (payment != null) {
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);

            if (lastRecord.status == Invoice.Status.WAITING_CONFIRMATION) {
                Payment.Record record = new Payment.Record(Invoice.Status.ON_PROGRESS, "Payment is accepted");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/{id}/cancel")
    public boolean cancel(@PathVariable int id) {
        Payment payment = null;

        for (int i = 0; i < paymentTable.size(); i++) {
            Payment paymentTemp = paymentTable.get(i);
            if (paymentTemp.id == id) {
                payment = paymentTemp;
            }
        }

        if (payment != null) {
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);

            if (lastRecord.status == Invoice.Status.WAITING_CONFIRMATION) {
                Payment.Record record = new Payment.Record(Invoice.Status.CANCELLED, "Payment is cancelled");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan) {
        Account account = null;
        Product product = null;

        for (int i = 0; i < AccountController.accountTable.size(); i++) {
            Account accountTemp = AccountController.accountTable.get(i);
            if (accountTemp.id == buyerId) {
                account = accountTemp;
            }
        }

        for (int i = 0; i < ProductController.productTable.size(); i++) {
            Product productTemp = ProductController.productTable.get(i);
            if (productTemp.id == productId) {
                product = productTemp;
            }
        }

        if (account != null && product != null) {
            Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);
            Payment payment = new Payment(buyerId, productId, productCount, shipment);
            double price = payment.getTotalPay(product);

            if (account.balance >= price) {
                account.balance = account.balance - price;
                payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Waiting for confirmation"));
                paymentTable.add(payment);
                poolThread.add(payment);
                return payment;
            }
        }
        return null;
    }

    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/{id}/submit")
    public boolean submit(@PathVariable int id, @RequestParam String receipt) {
        Payment payment = null;

        for (int i = 0; i < paymentTable.size(); i++) {
            Payment paymentTemp = paymentTable.get(i);
            if (paymentTemp.id == id) {
                payment = paymentTemp;
            }
        }

        if (payment != null) {
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if (lastRecord.status == Invoice.Status.ON_PROGRESS && (!receipt.isBlank())) {
                payment.shipment.receipt = receipt;
                Payment.Record record = new Payment.Record(Invoice.Status.ON_DELIVERY, "On delivery");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    private static boolean timekeeper(Payment payment) {
        Payment.Record record = payment.history.get(payment.history.size() - 1);
        long elapsed = Math.abs(record.date.getTime() - (new Date()).getTime());

        if(record.status == Invoice.Status.WAITING_CONFIRMATION && elapsed > WAITING_CONF_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Waiting"));
            return true;
        } else if(record.status == Invoice.Status.ON_PROGRESS && elapsed > ON_PROGRESS_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Progress"));
            return true;
        } else if(record.status == Invoice.Status.ON_DELIVERY && elapsed > ON_DELIVERY_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Delivery"));
            return false;
        } else if(record.status == Invoice.Status.DELIVERED && elapsed > DELIVERED_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Finish"));
            return true;
        }
        return false;
    }
}


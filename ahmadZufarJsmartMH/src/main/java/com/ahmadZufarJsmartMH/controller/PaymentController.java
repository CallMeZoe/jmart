package com.ahmadZufarJsmartMH.controller;

import com.ahmadZufarJsmartMH.Account;
import com.ahmadZufarJsmartMH.ObjectPoolThread;
import com.ahmadZufarJsmartMH.Payment;
import com.ahmadZufarJsmartMH.Shipment;
import com.ahmadZufarJsmartMH.dbjson.JsonAutowired;
import com.ahmadZufarJsmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    public static final long DELIVERED_LIMIT_MS = 1;
    public static final long ON_DELIVERY_LIMIT_MS = 1;
    public static final long ON_PROGRESS_LIMIT_MS = 1;
    public static final long WAITING_CONF_LIMIT_MS = 1;

    @JsonAutowired(value = Payment.class, filepath = "D:\\Prak OOP\\jmart\\ahmadZufarJsmartMH\\src\\main")
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    @PostMapping("/{id}/accept")
    boolean accept(@PathVariable int id) {
        return false;
    }

    @PostMapping("/{id}/cancel")
    boolean cancel(@PathVariable int id) {
        return false;
    }

    @PostMapping("/create")
    Payment create(
            @RequestParam int buyerId,
            @RequestParam int productId,
            @RequestParam int productCount,
            @RequestParam String shipmentAddress,
            @RequestParam byte shipmentPlan
    ){
        Shipment shipment = new Shipment(shipmentAddress, 1000, shipmentPlan, "");
        Payment payment = new Payment(buyerId, productId, productCount, shipment);

        paymentTable.add(payment);
        return payment;
    }

    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/{id}/submit")
    boolean submit(@PathVariable int id, @RequestParam String receipt) {
        return false;
    }

    static boolean timekeeper(Payment payment) {
        return false;
    }
}

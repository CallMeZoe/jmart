package com.ahmadZufarJsmartMH.controller;

import com.ahmadZufarJsmartMH.*;
import com.ahmadZufarJsmartMH.dbjson.JsonAutowired;
import com.ahmadZufarJsmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Class untuk mengatur perubahan pada objek Payment
 */

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    @JsonAutowired(value = Payment.class, filepath = "C:\\Users\\Zufar\\Desktop\\Prak_OOP\\jmart\\ahmadZufarJsmartMH\\src\\main\\payment.json")

    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    public static final long DELIVERED_LIMIT_MS = 10000;
    public static final long ON_DELIVERY_LIMIT_MS = 10000;
    public static final long ON_PROGRESS_LIMIT_MS = 10000;
    public static final long WAITING_CONF_LIMIT_MS = 30000;

    static {
        poolThread = new ObjectPoolThread<Payment>("Thread", PaymentController::timekeeper);
        poolThread.start();
    }

    /**
     * Method untuk mendapatkan objek Payment yang memiliki accountId sesuai dengan id pembeli yang diberikan di parameter
     * @param buyerId id pembeli
     * @return list dari seluruh objek Payment yang memiliki accountId sesuai dengan id pembeli yang diberikan di parameter
     */
    @GetMapping("/getByAccountId")
    public ArrayList<Payment> getPaymentByAccountId(@RequestParam int buyerId){
        ArrayList<Payment> paymentList = new ArrayList<>();
        for(Payment p : paymentTable){
            if(p.buyerId == buyerId){
                paymentList.add(p);
            }
        }
        return paymentList;
    }

    /**
     * Method untuk mendapatkan objek Payment yang memiliki id store sesuai dengan id store yang diberikan di parameter
     * @param storeId id toko/store
     * @return list objek Payment yang memiliki id store sesuai dengan id store yang diberikan di parameter
     */
    @GetMapping("/getByStoreId")
    public ArrayList<Payment> getPaymentByStoreId(@RequestParam int storeId){
        ArrayList<Payment> paymentList = new ArrayList<>();
        for(Payment payment : paymentTable){
            if(payment.storeId == storeId){
                paymentList.add(payment);
            }
        }
        return paymentList;
    }

    /**
     * Method untuk menerima objek Payment yang diberikan saat seorang pembeli melakukan pembelian suatu produk oleh toko
     * @param id id dari objek Payment yang ingin diterima
     * @return true jika objek Payment berhasil diterima, false jika gagal
     */
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
                Payment.Record record = new Payment.Record(Invoice.Status.ON_PROGRESS, "Payment accepted");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    /**
     * Method untuk membatalkan objek Payment yang sudah dibuat baik oleh pembeli maupun toko
     * @param id id dari objek Payment yang ingin dibatalkan atau di-cancel
     * @return true jika objek Payment berhasil dibatalkan, false jika gagal
     */
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
                Payment.Record record = new Payment.Record(Invoice.Status.CANCELLED, "Payment cancelled");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    /**
     * Method untuk membuat suatu objek Payment ketika ingin membeli suatu produk bagi pembeli
     * @param buyerId id dari akun pembeli
     * @param productId id dari produk yang ingin dibeli
     * @param productCount jumlah produk yang ingin dibeli
     * @param shipmentAddress alamat tempat produk dikirimkan
     * @param shipmentPlan jenis shipment yang akan digunakan untuk mengirimkan produk yang dibeli
     * @param storeId id dari toko yang memiliki produk
     * @return objek Payment yang berhasil dibuat, null jika tidak berhasil membuat objek Payment
     */
    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan,  @RequestParam int storeId, @RequestParam double discount) {
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
            Payment payment = new Payment(buyerId, productId, productCount, shipment, storeId);
            double price = payment.getTotalPay(product);
            price = price - (discount/100)*price;

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

    /**
     * Method untuk memberikan list yang berisikan objek Payment yang terdaftar pada file json
     * @return list yang berisikan objek Payment yang terdaftar pada file json
     */
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    /**
     * Method untuk melakukan submit objek Payment yang sudah diterima sebelumnya bagi toko
     * @param id id dari objek Payment yang ingin di-submit
     * @param receipt isi receipt atau nomor barcode
     * @return true jika objek Payment berhasil di-submit, false jika gagal
     */
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

    /**
     * Method untuk mengecek waktu yang telah terlewati setelah melakukan modifikasi pada objek Payment
     * @param payment objek Payment yang akan dicek
     * @return true jika terdapat perubahan akibat melewati limit waktu, false jika tidak terdapat perubahan pada objek Payment
     */
    private static boolean timekeeper(Payment payment) {
        Date timeNow = Calendar.getInstance().getTime();
        if (payment.history.size() != 0) {
            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
            if (lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)) {
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "FAILED"));
                return true;
            } else if ((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)) {
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "FAILED"));
                return true;
            } else if (lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS) {
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "DELIVERED"));
                return true;
            } else if (lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS) {
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "FINISHED"));
                return true;
            }
        }
        return false;
    }
}

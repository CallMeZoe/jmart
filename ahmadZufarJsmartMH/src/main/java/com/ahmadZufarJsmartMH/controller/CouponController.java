package com.ahmadZufarJsmartMH.controller;

import com.ahmadZufarJsmartMH.Algorithm;
import com.ahmadZufarJsmartMH.Coupon;
import com.ahmadZufarJsmartMH.dbjson.JsonAutowired;
import com.ahmadZufarJsmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class untuk mengatur perubahan pada objek Coupon
 */

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {
    @JsonAutowired(value = Coupon.class, filepath = "fileCoupon.json")
    public static JsonTable<Coupon> couponTable;

    /**
     * Method untuk memberikan list yang berisikan objek Coupon yang terdaftar pada file json
     * @return list yang berisikan objek Coupon yang terdaftar pada file json
     */
    public JsonTable<Coupon> getJsonTable(){
        return couponTable;
    }

    /**
     * Method untuk mendapatkan informasi dari suatu objek Coupon (berdasarkan id) apakah objek tersebut sudah digunakan atau belum
     * @param id id dari objek Coupon yang ingin diketahui apakah sudah digunakan atau belum
     * @return true jika sudah pernah digunakan, false jika belum pernah digunakan
     */
    @GetMapping("/{id}/isUsed")
    boolean isUsed(@PathVariable int id){
        for(Coupon c : couponTable){
            if(c.id == id){
                return c.isUsed();
            }
        }
        return false;
    }

    /**
     * Method untuk mengecek apakah objek Coupon yang diinginkan berdasarkan id dapat di-apply atau digunakan
     * @param id id dari objek Coupon yang ingin di-apply atau digunakan
     * @param price harga produk
     * @param discount diskon produk
     * @return true jika Coupon dapat di-apply berdasarkan syarat tertentu, false jika Coupon tidak dapat di-apply
     */
    @GetMapping("/{id}/canApply")
    boolean canApply(@PathVariable int id, @RequestParam double price, @RequestParam double discount){
        for(Coupon c : couponTable){
            if(c.id == id){
                return c.canApply(price, discount);
            }
        }
        return false;
    }

    /**
     * Method untuk mencari seluruh objek Coupon yang available / dapat digunakan dari list yang terdaftar di file json
     * lalu melakukan paginasi untuk menampilkan sebagian coupon yang tersedia berdasarkan index dan ukuran page
     * @param page index page
     * @param pageSize ukuran page
     * @return list yang berisikan objek coupon pada index halaman tertentu dengan ukuran page tertentu
     */
    @GetMapping("/getAvailable")
    List<Coupon> getAvailable(@RequestParam int page, @RequestParam int pageSize){
        ArrayList<Coupon> newList = new ArrayList<>();
        for(Coupon c : couponTable){
            if(!c.isUsed()){
                newList.add(c);
            }
        }
        return Algorithm.<Coupon>paginate(newList, page, pageSize, c -> true);
    }
}

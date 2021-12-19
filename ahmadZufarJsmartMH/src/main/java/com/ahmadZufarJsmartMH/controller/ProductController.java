package com.ahmadZufarJsmartMH.controller;

import com.ahmadZufarJsmartMH.*;
import com.ahmadZufarJsmartMH.dbjson.JsonAutowired;
import com.ahmadZufarJsmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class untuk mengatur perubahan pada objek Product
 */

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product>{

    @JsonAutowired(value = Product.class, filepath = "C:\\Users\\Zufar\\Desktop\\Prak_OOP\\jmart\\ahmadZufarJsmartMH\\src\\main\\product.json")
    public static JsonTable<Product> productTable;

    /**
     * Method untuk memberikan list yang berisikan objek Product yang terdaftar pada file json
     * @return list yang berisikan objek Product yang terdaftar pada file json
     */
    public JsonTable<Product> getJsonTable() {
        return productTable;
    }

    /**
     * Method untuk mendapatkan objek Product berdasarkan id
     * @param id id objek Product yang ingin didapatkan
     * @return objek Product yang memiliki id yang sesuai
     */
    @GetMapping("/{id}")
    Product getProductById(@PathVariable int id) { return getById(id); }

    /**
     * Method untuk mendapatkan list objek Product berdasarkan store-nya dan dilakukan paginasi
     * @param id id dari store yang ingin didapatkan objek Product-nya
     * @param page index page
     * @param pageSize ukuran page
     * @return list yang berisikan objek Product dari store yang diinginkan dan telah dipaginasi
     */
    @GetMapping("/{id}/store")
    List<Product> getProductByStore(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize) {
        return Algorithm.<Product>paginate(getJsonTable(), page, pageSize, product -> product.accountId == id);
    }

    /**
     * Method untuk membuat objek Product dan mendaftarkannya ke file json
     * @param accountId id store yang membuat objek Product
     * @param name nama dari objek Product yang akan dibuat
     * @param weight berat dari objek Product yang akan dibuat
     * @param conditionUsed kondisi penggunaan dari objek Product yang akan dibuat (Baru atau Second)
     * @param price harga dari objek Product yang akan dibuat
     * @param discount diskon dari objek Product yang akan dibuat
     * @param category kategori dari objek Product yang akan dibuat
     * @param shipmentPlans tipe shipment yang akan digunakan untuk mengirimkan produk ke pembeli
     * @return objek Product yang berhasil dibuat, null jika tidak berhasil membuat objek Product
     */
    @PostMapping("/create")
    Product create(@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed,
                   @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category, @RequestParam byte shipmentPlans) {
        for (Account account : AccountController.accountTable) {
            if (account.id == accountId && account.store != null) {
                Product product = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
                productTable.add(product);
                return product;
            }
        }
        return null;
    }

    /**
     * Method untuk mendapatkan list yang berisikan objek Product yang memenuhi kondisi filter yang diberikan
     * @param page index page
     * @param pageSize ukuran page
     * @param accountId id store
     * @param search string yang dicari oleh user
     * @param minPrice harga minimum produk
     * @param maxPrice harga maksimum produk
     * @param category kategori produk
     * @return list yang berisikan objek Product yang memenuhi kondisi filter dan sudah dipaginasi
     */
    @GetMapping("/getFiltered")
    List<Product> getProductFiltered(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int accountId, @RequestParam(defaultValue = "") String search,
                                     @RequestParam(defaultValue = "0") int minPrice, @RequestParam(defaultValue = "0") int maxPrice, @RequestParam(defaultValue = "") ProductCategory category) {

        String searchLowercase = search.toLowerCase();
        Predicate<Product> predicateSearch = product -> product.name.toLowerCase().contains(searchLowercase);
        return paginateProductListFilteredAll(page, pageSize, predicateSearch, minPrice, maxPrice, category);
    }

    public List<Product> paginateProductListFilteredAll(int page, int pageSize, Predicate<Product> predSearch, int minPrice, int maxPrice, ProductCategory category){
        List<Product> newList = new ArrayList<Product>();
        if(minPrice != 0.0 && maxPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice > minPrice && productPrice < maxPrice && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else if(minPrice == 0.0 && maxPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice < maxPrice && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else if(maxPrice == 0.0 && minPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice > minPrice && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<Product> paginatedList;
        if(endIndex > newList.size())
        {
            paginatedList = newList.subList(startIndex, newList.size());
        }
        else
        {
            paginatedList = newList.subList(startIndex, endIndex);
        }
        return paginatedList;
    }
}

package com.ahmadZufarJsmartMH.controller;

import com.ahmadZufarJsmartMH.*;
import com.ahmadZufarJsmartMH.dbjson.JsonAutowired;
import com.ahmadZufarJsmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product>{

    @JsonAutowired(value = Product.class, filepath = "ganti path lu")
    public static JsonTable<Product> productTable;

    @Override
    public JsonTable<Product> getJsonTable() {
        return productTable;
    }

    @GetMapping("/{id}/store")
    List<Product> getProductByStore
            (
                    @PathVariable int id,
                    @RequestParam int page,
                    @RequestParam int pageSize
            )
    {
        Predicate<Product> predicate = obj -> (obj.accountId == id);
        return Algorithm.<Product>paginate(productTable, page, pageSize, predicate);
    }

    @PostMapping("/create")
    Product create
            (
                    @RequestParam int accountId,
                    @RequestParam String name,
                    @RequestParam int weight,
                    @RequestParam boolean conditionUsed,
                    @RequestParam double price,
                    @RequestParam double discount,
                    @RequestParam ProductCategory category,
                    @RequestParam byte shipmentPlans
            )
    {
        Product product = Algorithm.<Product>find(productTable, obj -> obj.accountId == accountId);
        Account account = Algorithm.<Account>find(AccountController.accountTable, obj -> obj.id == accountId);
        if(product == null || account.store != null) return null;

        Product newProduct = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
        productTable.add(newProduct);
        return newProduct;
    }

    @GetMapping("/getFiltered")
    List<Product> getProductByStore
            (
                    @RequestParam int page,
                    @RequestParam int pageSize,
                    @RequestParam(defaultValue = "0") int accountId,
                    @RequestParam(defaultValue = "") String search,
                    @RequestParam(defaultValue = "0") int minPrice,
                    @RequestParam(defaultValue = "0") int maxPrice,
                    @RequestParam(required = false) ProductCategory category
            )
    {
        Predicate<Product> predicate = obj -> {
            if(accountId != 0 && obj.accountId == accountId){
                return true;
            }
            if(!search.isBlank() && obj.name.equals(search)){
                return true;
            }
            if(minPrice != 0 && obj.price > minPrice){
                return true;
            }
            if(maxPrice != 0 && obj.price < maxPrice){
                return true;
            }
            if(category != null && obj.category == category){
                return true;
            }
            return false;
        };
        return Algorithm.<Product>paginate(productTable, page, pageSize, predicate);
    }
}

package com.ahmadZufarJsmartMH.controller;

import com.ahmadZufarJsmartMH.Algorithm;
import com.ahmadZufarJsmartMH.dbjson.JsonTable;
import com.ahmadZufarJsmartMH.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface BasicGetController <T extends Serializable> {

    @GetMapping("/id")
    default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
    }

    @GetMapping("/page")
    default List<T> getPage (@PathVariable int page, @PathVariable int pageSize){
        return null;
    }

    public abstract JsonTable<T> getJsonTable();

}

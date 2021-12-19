package com.ahmadZufarJsmartMH.controller;

import com.ahmadZufarJsmartMH.Algorithm;
import com.ahmadZufarJsmartMH.dbjson.JsonTable;
import com.ahmadZufarJsmartMH.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *Interface untuk mengatur paginasi dan objek pada list
 */

@RestController
public interface BasicGetController <T extends Serializable> {

    /**
     *Method untuk mengambil suatu objek dari list berdasarkan id nya yang berhubungan dengan tipe objek
     * @param id id dari objek yang ingin dicari
     * @return objek yang ingin dicari berdasarkan id, jika tidak ada keluarkan null
     */
    @GetMapping("/id")
    default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
    }

    /**
     * Method untuk melakukan paginasi pada collection berdasarkan index page dan ukuran page
     * @param page index page
     * @param pageSize ukuran page (jumlah elemen yang dapat dikandung dalam 1 page)
     * @return list yang berisik elemen dari index page dengan ukuran pageSize
     */
    @GetMapping("/page")
    default List<T> getPage (int page, int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, e->true);
    }

    /**
     * Method untuk mengambil list berdasarkan tipe objek
     * @return list yang diambil
     */
    public abstract JsonTable<T> getJsonTable();

}

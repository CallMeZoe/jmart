package com.ahmadZufarJsmartMH;


/**
 * Merupakan Class ProductRating untuk rating product
 *
 * @author Zufar
 * @version 19/12/2021
 */
public class ProductRating
{
    private long total;
    private long count;
    
    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }
    
    public void insert(int rating){
        total += rating;
        count++;
    }
    
    public double getAverage(){
        return ((double)total/count);
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }
}

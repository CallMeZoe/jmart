package com.ahmadZufarJsmartMH;


import com.ahmadZufarJsmartMH.dbjson.Serializable;

/**
 * Merupakan Class Product yang mengatur mengenai product di Jmart
 *
 * @author Zufar
 * @version 19/12/2021
 */
public class Product extends Serializable
{
    public int accountId;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public double price;
    public double discount;
    public byte shipmentPlans;
    public ProductCategory category;

    public Product(int accountId, String name, int weight, boolean conditionUsed,
                   double price, double discount, ProductCategory category, byte shipmentPlans){
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.category = category;
        this.discount = discount;
        this.shipmentPlans = shipmentPlans;
    }


    public String toString() {
        return  "Name : " + this.name + "\nWeight : " + this.weight +
                "\nconditionUsed : " + this.conditionUsed + "\nprice : " +
                this.price + "\ncategory : " + this.category + "\ndiscount : " +
                this.discount + "\naccountId : " + this.accountId;
    }

}

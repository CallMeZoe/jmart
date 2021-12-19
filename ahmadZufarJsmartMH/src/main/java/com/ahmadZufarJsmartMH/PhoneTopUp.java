package com.ahmadZufarJsmartMH;

/**
 * Merupakan Class PhoneTopUp yang mengatur top up pada phone si account
 *
 * @author Zufar
 * @version 19/12/2021
 */
public class PhoneTopUp extends Invoice{
    String phoneNumber;
    Status status;

    public PhoneTopUp(int buyerId, int productId, String phoneNumber)
    {
        super(buyerId, productId);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public double getTotalPay(Product product) {
        return product.price;
    }
}

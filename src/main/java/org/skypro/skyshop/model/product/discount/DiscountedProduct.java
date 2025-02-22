package org.skypro.skyshop.model.product.discount;

import org.skypro.skyshop.model.product.Product;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private int price;
    private int discount;

    public DiscountedProduct(UUID id, String name, int price, int discount) {
        super(id, name);
        if (!(price > 0)) {
            throw new IllegalArgumentException("Цена товара должна быть больше 0");
        }
        if (!(discount >= 0 && discount <= 100)) {
            throw new IllegalArgumentException("Скидка должна находиться в диапозоне от 0 до 100");
        }
        this.price = price;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return (int) (price - price * ((double) discount / 100));
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "%s: %d (%d%%)",
                getName(),
                getPrice(),
                discount);
    }
}

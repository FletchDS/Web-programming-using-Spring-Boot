package org.skypro.skyshop.model.product.simple;

import org.skypro.skyshop.model.product.Product;

import java.util.UUID;

public class SimpleProduct extends Product {

    private int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name);
        if (!(price > 0)) {
            throw new IllegalArgumentException("Цена товара должна быть больше 0");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return String.format(
                "%s: %d",
                getName(),
                getPrice());
    }
}

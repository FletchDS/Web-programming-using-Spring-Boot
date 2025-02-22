package org.skypro.skyshop.model.product.fixprice;

import org.skypro.skyshop.model.product.Product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    public final int PRICE = 100;

    public FixPriceProduct(UUID id, String name) {
        super(id, name);
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "%s: Фиксированная цена %d",
                getName(),
                getPrice());
    }
}

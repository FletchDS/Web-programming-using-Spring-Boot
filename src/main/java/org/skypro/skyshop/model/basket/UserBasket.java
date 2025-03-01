package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private List<BasketItem> basketItems;
    private Integer total;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        total = basketItems.stream()
                .mapToInt(i -> i.getCount() * i.getProduct().getPrice())
                .sum();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public Integer getTotal() {
        return total;
    }
}

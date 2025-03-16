package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return Objects.equals(basketItems, that.basketItems) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketItems, total);
    }
}

package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> products;

    public ProductBasket(Map<UUID, Integer> products) {
        this.products = products;
    }

    public void addProduct(UUID productId) {
        if(!products.containsKey(productId)){
            products.putIfAbsent(productId, 1);
        } else {
            products.compute(productId, (k,v) -> v + 1);
        }
    }

    public Map<UUID, Integer> getProducts(){
        return Collections.unmodifiableMap(products);
    }
}

package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id){
        if (storageService.getProductById(id).isEmpty()){
            throw new IllegalArgumentException("Продукт не найден");
        }

        basket.addProduct(id);
    }

    public UserBasket getUserBasket(){
        return new UserBasket(basket.getProducts().entrySet().stream()
                .map(entry -> {
                    Optional<Product> product = storageService.getProductById(entry.getKey());
                    return new BasketItem(product.get(), entry.getValue());
                }).toList());
    }
}

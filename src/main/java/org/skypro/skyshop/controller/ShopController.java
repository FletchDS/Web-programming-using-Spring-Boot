package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;
    private Collection<Product> products;

    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
        products = storageService.getProducts();
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return products;
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> getSearchablesByPattern(@RequestParam("pattern") String pattern){
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id){
        basketService.addProduct(id);
        return "*Продукт успешно добавлен*";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket(){
        return basketService.getUserBasket();
    }
}

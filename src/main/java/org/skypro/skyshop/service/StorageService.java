package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.discount.DiscountedProduct;
import org.skypro.skyshop.model.product.simple.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        setDefaultMaps();
    }

    public Collection<Product> getProducts() {
        return productMap.values();
    }

    public Collection<Article> getArticles() {
        return articleMap.values();
    }

    public Collection<Searchable> getAllSearchables(){
        Collection<Searchable> collection = new ArrayList<>();
        collection.addAll(productMap.values());
        collection.addAll(articleMap.values());

        return collection;
    }

    private void setDefaultMaps(){
        Product product1 = new DiscountedProduct(UUID.randomUUID(), "Арбуз", 100, 20);
        Product product2 = new SimpleProduct(UUID.randomUUID(), "Арбузный сок", 200);
        SimpleProduct product3 = new SimpleProduct(UUID.randomUUID(), "Арбузные семена", 50);
        SimpleProduct product4 = new SimpleProduct(UUID.randomUUID(), "Арбузный сок 'Добрый Арбуз'", 50);
        Article article1 = new Article(UUID.randomUUID(), "Лучшие сорта арбузов", "Лучшие арбузы");
        Article article2 = new Article(UUID.randomUUID(), "Арбузы", "Арбузы");

        productMap.put(product1.getId(), product1);
        productMap.put(product2.getId(), product2);
        productMap.put(product3.getId(), product3);
        productMap.put(product4.getId(), product4);
        articleMap.put(article1.getId(), article1);
        articleMap.put(article2.getId(), article2);
    }

}

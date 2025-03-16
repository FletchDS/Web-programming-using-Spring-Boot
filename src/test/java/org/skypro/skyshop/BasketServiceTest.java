package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.simple.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    StorageService storageService;

    @Mock
    ProductBasket productBasket;

    @InjectMocks
    BasketService basketService;

    @Test
    public void givenInexistentProduct_whenAddProductToBasket_thenThrowNoSuchProductException(){
        UUID uuid = UUID.randomUUID();
        Mockito.doReturn(Optional.empty()).when(storageService).getProductById(uuid);
        Exception thrownException = null;

        try {
            basketService.addProduct(uuid);
        } catch (Exception e){
            thrownException = e;
        }

        Assertions.assertInstanceOf(NoSuchProductException.class, thrownException);
    }

    @Test
    public void givenExistentProduct_whenAddProductToBasket_thenCausesAddProductMethod(){
        UUID uuid = UUID.randomUUID();
        Mockito.doReturn(Optional.of(new SimpleProduct(uuid, "Арбуз", 100))).when(storageService).getProductById(uuid);

        basketService.addProduct(uuid);

        Mockito.verify(productBasket, Mockito.only()).addProduct(uuid);
    }

    @Test
    public void givenEmptyProductBasket_whenInvokeGetUserBasket_thenReturnEmptyBasket(){
        UserBasket userBasket;
        Mockito.doReturn(new HashMap<UUID, Integer>()).when(productBasket).getProducts();

        boolean isEmpty = basketService.getUserBasket().getBasketItems().isEmpty();

        Assertions.assertEquals(true, isEmpty);
    }

    @Test
    public void givenFilledProductBasket_whenInvokeGetUserBasket_thenReturnFilledBasket(){
        UUID uuid = UUID.randomUUID();
        SimpleProduct simpleProduct = new SimpleProduct(uuid, "Арбуз", 100);
        UserBasket userBasket1 = new UserBasket(
                List.of(
                        new BasketItem(simpleProduct, 2)));
        Mockito.doReturn(Optional.ofNullable(simpleProduct)).when(storageService).getProductById(uuid);
        Mockito.doReturn(new HashMap<UUID, Integer>(Map.of(uuid, 2))).when(productBasket).getProducts();

        UserBasket userBasket2 = basketService.getUserBasket();

        Assertions.assertEquals(userBasket1.getBasketItems(), userBasket2.getBasketItems());
    }


}

package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.simple.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    public void givenStringForSearch_whenSearchForSearchablesInEmptyStorage_thenEmptyMap(){
        String searchString = "Арбуз";
        Mockito.doReturn(new ArrayList<Searchable>()).when(storageService).getAllSearchables();

        int size = searchService.search(searchString).size();

        Assertions.assertEquals(0, size);
    }

    @Test
    public void givenMapWithSearchable_whenSearchForSearchableWithNotExistingSearchable_thenEmptyMap(){
        String searchString = "Арбуз";
        Mockito.doReturn(new ArrayList<Searchable>(
                        List.of(new SimpleProduct(UUID.randomUUID(), "Апельсиновый сок", 80))))
                .when(storageService)
                .getAllSearchables();

        int size = searchService.search(searchString).size();

        Assertions.assertEquals(0, size);
    }

    @Test
    public void givenMapWithSearchable_whenSearchForSearchableWithExistingSearchable_thenMapWithSearchable(){
        String searchString = "Арбуз";
        Mockito.doReturn(new ArrayList<Searchable>(
                        List.of(new SimpleProduct(UUID.randomUUID(), "Арбузный сок", 100))))
                .when(storageService)
                .getAllSearchables();

        int size = searchService.search(searchString).size();

        Assertions.assertEquals(1, size);
    }
}

package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String search){
        Collection<SearchResult> result = storageService.getAllSearchables().stream()
                .filter(i -> i.getSearchTerm().contains(search))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
}

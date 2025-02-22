package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String getSearchTerm();

    String getType();

    String getName();

    UUID getId();

    default String getStringRepresentation(){
        return "имя " + getName() + " — " + getType();
    }
}

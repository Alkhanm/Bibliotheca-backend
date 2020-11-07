package com.alkham.libros.services.interfaces;
import com.alkham.libros.domain.entities.ReadingList;

import java.util.Set;

public interface ReadingListService extends EntityService<ReadingList>{
    Set<ReadingList> findByUser();
}

package com.iiitb.spe.repo;

import com.iiitb.spe.model.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
    List<ItemEntity> findAll();

    ItemEntity findItemById(long id);
}

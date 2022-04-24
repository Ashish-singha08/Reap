package com.iiitb.spe.repo;

import com.iiitb.spe.model.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity,Long> {
    List<TagEntity> findAll();
}

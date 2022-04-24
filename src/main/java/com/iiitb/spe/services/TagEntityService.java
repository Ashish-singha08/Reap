package com.iiitb.spe.services;

import com.iiitb.spe.model.entities.TagEntity;
import com.iiitb.spe.repo.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagEntityService {
    @Autowired
    private TagRepository tagRepository;

    public List<TagEntity> getTags(){
        List<TagEntity> tags = tagRepository.findAll();
        return tags;
    }
}

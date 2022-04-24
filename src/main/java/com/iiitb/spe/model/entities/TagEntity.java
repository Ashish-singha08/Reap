package com.iiitb.spe.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Tag", schema = "Reap", catalog = "")
public class TagEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "Tag")
    private String tag;

    @Basic
    @Column(name = "imageUrl")
    private String imageUrl;
    @JsonIgnore
    @OneToMany(mappedBy = "tagByTagId")
    private Collection<EndorsementEntity> endorsementsById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity tagEntity = (TagEntity) o;
        return id == tagEntity.id && Objects.equals(tag, tagEntity.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag);
    }

    public Collection<EndorsementEntity> getEndorsementsById() {
        return endorsementsById;
    }

    public void setEndorsementsById(Collection<EndorsementEntity> endorsementsById) {
        this.endorsementsById = endorsementsById;
    }
}

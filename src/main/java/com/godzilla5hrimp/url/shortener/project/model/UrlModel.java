package com.godzilla5hrimp.url.shortener.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model of an URL represented from the database.
 */
@Getter
@Setter
@Entity
@Table(name = "url_entities")
public class UrlModel {

    @Column(name = "url_or")
    private String urlOr;

    @Id
    @Column(name = "url_hash")
    private Long urlHash;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "nmb_shrt")
    private Long nmbShrt = Long.valueOf(0);

    @Column(name = "nmb_vis")
    private Long nmbVis = Long.valueOf(0);

    @Column(name = "url_shrt")
    private String urlShrt;

    @Override
    public int hashCode() {
        if(this.userId == null)
            return Math.abs(urlOr.hashCode());
        return Math.abs(urlOr.hashCode()) + Math.abs(userId.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if(this.urlOr.equals(((UrlModel) o).urlOr) && this.userId.equals(((UrlModel) o).userId))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "UrlModel{" +
                "urlOr='" + urlOr + '\'' +
                ", urlHash=" + urlHash +
                ", userId=" + userId +
                ", nmbShrt=" + nmbShrt +
                ", nmbVis=" + nmbVis +
                ", urlShrt='" + urlShrt + '\'' +
                '}';
    }
}

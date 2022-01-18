package com.godzilla5hrimp.url.shortener.project.repository;

import com.godzilla5hrimp.url.shortener.project.model.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlModelRepository extends JpaRepository<UrlModel,Long> {

    @Query("SELECT url FROM UrlModel url WHERE url.urlHash = ?1")
    UrlModel findByUrlHash(Long hash);

    @Query("SELECT url FROM UrlModel url WHERE url.urlShrt = ?1")
    UrlModel findByUrlShrt(String urlShrt);
}

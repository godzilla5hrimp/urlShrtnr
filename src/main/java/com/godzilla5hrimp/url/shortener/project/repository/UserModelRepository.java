package com.godzilla5hrimp.url.shortener.project.repository;

import com.godzilla5hrimp.url.shortener.project.model.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//TODO: Add users
@Repository
public interface UserModelRepository extends JpaRepository<UrlModel,Long> {
}

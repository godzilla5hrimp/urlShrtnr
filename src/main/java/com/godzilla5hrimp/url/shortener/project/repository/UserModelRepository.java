package com.godzilla5hrimp.url.shortener.project.repository;

import com.godzilla5hrimp.url.shortener.project.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//TODO: Add users
@Repository
public interface UserModelRepository extends JpaRepository<UserModel,Long> {

    @Query("SELECT u FROM UserModel u WHERE u.userName = ?1")
    UserModel findByUserName(String userName);
}

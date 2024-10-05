package com.helmes.vladimirtest.repository;

import com.helmes.vladimirtest.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u.id from user u where u.userName = :userName")
    Long findUserIdByUserName(String userName);

}

package com.kamar.web_impl_full_stack.user.repository;

import com.kamar.web_impl_full_stack.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * the user repository.
 * @author kamar baraka.*/

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("select case when count(u) > 0 then true else false end from UserEntity u where u.email=?1")
    boolean existsByEmail(String email);
    UserEntity findUserEntityByEmail(String email);
}

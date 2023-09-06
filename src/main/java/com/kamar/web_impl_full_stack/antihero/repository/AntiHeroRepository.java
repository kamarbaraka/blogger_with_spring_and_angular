package com.kamar.web_impl_full_stack.antihero.repository;

import com.kamar.web_impl_full_stack.antihero.entity.AntiHeroEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * the anti-hero jpa repository.
 * @author kamar baraka*/

@Repository
public interface AntiHeroRepository extends CrudRepository<AntiHeroEntity, UUID> {
}

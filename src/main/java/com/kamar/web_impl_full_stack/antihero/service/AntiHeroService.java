package com.kamar.web_impl_full_stack.antihero.service;

import com.kamar.web_impl_full_stack.antihero.entity.AntiHeroEntity;
import com.kamar.web_impl_full_stack.antihero.exception.NotFoundException;
import com.kamar.web_impl_full_stack.antihero.repository.AntiHeroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * the anti-hero service.
 * @author kamar baraka.*/

@Service
@AllArgsConstructor
public class AntiHeroService {

    private final AntiHeroRepository antiHeroRepository;

    public AntiHeroEntity addAntiHero(AntiHeroEntity antiHero){

        return antiHeroRepository.save(antiHero);
    }

    public AntiHeroEntity findAntiHeroById(UUID id){

        /*check if the resource exists and return*/
        return findOrThrow(id);
    }

    public void updateAntiHero(UUID id, AntiHeroEntity antiHero){

        /*check if resource exists*/
        findOrThrow(id);

        antiHeroRepository.save(antiHero);
    }

    @Transactional
    public void deleteAntiHero(UUID id){

        /*check if exists*/
        findOrThrow(id);

        antiHeroRepository.deleteById(id);
    }

    public Iterable<AntiHeroEntity> getAllAntiHeroes(){

        return antiHeroRepository.findAll();
    }

    public AntiHeroEntity findOrThrow(UUID id){

        return antiHeroRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "the resource does not exist"
        ));
    }
}

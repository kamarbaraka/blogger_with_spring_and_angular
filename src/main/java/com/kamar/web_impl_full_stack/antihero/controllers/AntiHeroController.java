package com.kamar.web_impl_full_stack.antihero.controllers;

import com.kamar.web_impl_full_stack.antihero.dto.AntiHeroDTO;
import com.kamar.web_impl_full_stack.antihero.entity.AntiHeroEntity;
import com.kamar.web_impl_full_stack.antihero.exception.NotFoundException;
import com.kamar.web_impl_full_stack.antihero.service.AntiHeroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

/**
 * the anti-hero controller.
 * @author kamar baraka.*/


@RestController
@RequestMapping(value = {"/api/v1/anti_hero"})
@AllArgsConstructor
@CrossOrigin(allowedHeaders = {"Content-Type"})
public class AntiHeroController {

    private final AntiHeroService antiHeroService;
    private final ModelMapper mapper;

    /**
     * convert a dto to entity*/
    private AntiHeroEntity convertToEntity(AntiHeroDTO dto){

        return mapper.map(dto, AntiHeroEntity.class);
    }

    /**
     * convert entity to dto.*/
    private AntiHeroDTO convertToDTO(AntiHeroEntity entity){

        return mapper.map(entity, AntiHeroDTO.class);
    }

    /**
     * get anti-hero by id.*/
    @GetMapping(value = {"/{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin(methods = {RequestMethod.GET})
    public ResponseEntity<AntiHeroDTO> getAntiHero(@PathVariable("id") UUID id){

        try
        {
            /*get the anti-hero*/
            AntiHeroEntity antiHero = antiHeroService.findAntiHeroById(id);
            /*convert to DTO for response*/
            AntiHeroDTO antiHeroDTO = convertToDTO(antiHero);
            /*return the response*/
            return new ResponseEntity<>(antiHeroDTO, HttpStatus.OK);
        }
        catch (NotFoundException exception){

            /*catch and notify the user*/
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * get all anti-heroes*/
    @GetMapping
    @CrossOrigin(methods = {RequestMethod.GET})
    public ResponseEntity<List<AntiHeroDTO>> getAllAntiHeroes(Pageable pageable){

        int skip = pageable.getPageNumber() * pageable.getPageSize();

        /*get all anti-heroes from the database*/
        Iterable<AntiHeroEntity> allAntiHeroes = antiHeroService.getAllAntiHeroes();
        /*convert to a list*/
        List<AntiHeroDTO> antiHeroDTOList = StreamSupport.stream(allAntiHeroes.spliterator(), false)
                .map(this::convertToDTO).toList();

        return ResponseEntity.ok(antiHeroDTOList);
    }

    /**
     * add an anti-hero*/
    @PostMapping
    @CrossOrigin(methods = {RequestMethod.POST})
    @PreAuthorize(value = "authentication")
    public ResponseEntity<AntiHeroDTO> postAntiHero(@Valid @RequestBody AntiHeroDTO dto){

        try
        {
            /*convert dto to entity*/
            AntiHeroEntity antiHeroEntity = convertToEntity(dto);
            /*persist the entity*/
            AntiHeroEntity response = antiHeroService.addAntiHero(antiHeroEntity);

            /*return the response*/
            return new ResponseEntity<>(convertToDTO(response), HttpStatus.CREATED);
        }
        catch (NotFoundException exception){

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * update anti-hero.*/
    @PutMapping(value = {"/{id}"})
    @CrossOrigin(methods = {RequestMethod.PUT})
    public ResponseEntity<AntiHeroDTO> putAntiHero(@PathVariable("id") UUID id, @Valid @RequestBody AntiHeroDTO dto){

        try{

            /*check if id matches*/
            if (!id.equals(dto.getId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            /*convert dto to entity*/
            AntiHeroEntity antiHeroEntity = convertToEntity(dto);
            /*update the entity*/
            antiHeroService.updateAntiHero(id, antiHeroEntity);

            return  ResponseEntity.status(HttpStatus.CREATED).body(dto);

        }
        catch (NotFoundException exception){

            /*catch and notify*/
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping(value = {"/{id}"})
    @CrossOrigin(methods = {RequestMethod.DELETE})
    @PreAuthorize(value = "authentication")
    public ResponseEntity<String > deleteAntiHero(@PathVariable("id") UUID id){

        try{

            /*delete anti-hero*/
            antiHeroService.deleteAntiHero(id);

            return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
        }
        catch (NotFoundException exception){

            /*throw an exception*/
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

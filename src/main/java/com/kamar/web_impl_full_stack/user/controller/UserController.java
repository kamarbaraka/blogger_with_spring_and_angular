package com.kamar.web_impl_full_stack.user.controller;

import com.kamar.web_impl_full_stack.user.data.UserDTOImpl;
import com.kamar.web_impl_full_stack.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

/**
 * the user controller.
 * @author kamar baraka.*/

@RestController
@AllArgsConstructor
@RequestMapping(value = {"/api/v1/users"})
public class UserController {

    private UserService userService;

    /**
     * get all the users in the database.
     * */
    @GetMapping
    public ResponseEntity<List<UserDTOImpl>> getAllUsers(){

        /*get all users*/
        List<UserDTOImpl> allUsers = this.userService.getAllUsers();
        /*return the list of users*/
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping(value = {"{id}"})
    public ResponseEntity<UserDTOImpl> getUserById(@PathVariable("id") UUID uuid){

        /*get the user by id*/
        UserDTOImpl userDTO = this.userService.findByUserId(uuid);
        /*return the user DTO*/
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTOImpl> createUser(@RequestBody UserDTOImpl userDTO) throws NoSuchAlgorithmException {

        /*persist the user*/
        UserDTOImpl user = this.userService.createUser(userDTO, userDTO.getPassword());
        /*return the user DTO*/
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping(value = {"{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public void updateUser(@PathVariable("id") UUID uuid, @RequestBody UserDTOImpl userDTO) throws NoSuchAlgorithmException{

        /*update the user*/
        this.userService.updateUser(uuid, userDTO, userDTO.getPassword());
    }

    @DeleteMapping(value = {"{id}"})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void deleteUserById(@PathVariable("id") UUID uuid){

        /*delete the user*/
        this.userService.removeUserById(uuid);
    }
}

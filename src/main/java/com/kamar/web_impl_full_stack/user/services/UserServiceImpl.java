package com.kamar.web_impl_full_stack.user.services;

import com.kamar.web_impl_full_stack.antihero.exception.NotFoundException;
import com.kamar.web_impl_full_stack.user.data.UserDTOImpl;
import com.kamar.web_impl_full_stack.user.entity.UserEntity;
import com.kamar.web_impl_full_stack.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

/**
 * the user service implementation.
 * @author kamar baraka.*/

@Primary
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    private UserDTOImpl convertToDTO(UserEntity user) {

        return this.mapper.map(user, UserDTOImpl.class);
    }


    private UserEntity convertToUserEntity(UserDTOImpl userDTO) {
        /*map the user DTO to the user entity and return the value*/
        return this.mapper.map(userDTO, UserEntity.class);
    }


    private byte[] createSalt() {

        /*construct a secure random to generate random bytes*/
        SecureRandom random = new SecureRandom();
        /*create a salt*/
        byte[] salt = new byte[128];
        /*generate random bytes and fill the salt*/
        random.nextBytes(salt);
        /*return the salt*/
        return salt;
    }


    private byte[] createPasswordHash(String password, byte[] salt) throws NoSuchAlgorithmException {

        /*create the digest to use and pass the algorithm to use*/
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        /*update the digest with the salt*/
        digest.update(salt);
        /*pass the password as bytes to be digested*/
        return digest.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public UserEntity findOrThrow(UUID userId) {
        /*get the user if not exist throw an exception*/
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
    }

    @Override
    public List<UserDTOImpl> getAllUsers() {
        /*get all users*/
        List<UserEntity> allUsers = userRepository.findAll();
        /*convert them to DTOs*/
        return allUsers.stream().map(this::convertToDTO).toList();

    }

    @Override
    public UserDTOImpl findByUserId(UUID userId) {
        /*get the user by id or throw exception*/
        UserEntity user = this.findOrThrow(userId);
        /*convert the user to DTO*/
        return this.convertToDTO(user);
    }

    @Override
    public UserDTOImpl createUser(UserDTOImpl userDTO, String password) {
        return null;
    }
}

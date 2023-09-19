package com.kamar.web_impl_full_stack.user.services;

import com.kamar.web_impl_full_stack.user.data.UserDTOImpl;
import com.kamar.web_impl_full_stack.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * the user service.
 * @author kamar baraka.*/

@Service
public interface UserService {
    UserEntity findOrThrow(final UUID userId);
    List<UserDTOImpl> getAllUsers();
    UserDTOImpl findByUserId(final UUID userId);
    UserDTOImpl createUser(UserDTOImpl userDTO, String password);

}
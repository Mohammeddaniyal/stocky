/*
 * @Author: james.junior
 * @Date: 6/18/23 19:49
 *
 * @Project: stocky-api
 */

package com.jamesaworo.stocky.features.authentication.data.usecase_impl;

import com.jamesaworo.stocky.core.annotations.Usecase;
import com.jamesaworo.stocky.features.authentication.data.repository.UserRepository;
import com.jamesaworo.stocky.features.authentication.domain.entity.User;
import com.jamesaworo.stocky.features.authentication.domain.usecase.IUserUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.util.ObjectUtils.isEmpty;

@Usecase
@RequiredArgsConstructor
public class UserUsecaseImpl implements IUserUsecase {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User save(User user) {
        throwIfUsernameIsTaken(user.getUsername());
        this.encodePassword(user);
        return this.repository.save(user);
    }

    public void encodePassword(User user) {
        String encoded = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.repository.findByUsernameEqualsIgnoreCase(username);
    }

    @Override
    public Optional<User> update(User user) {
        Optional<User> optionalUser = this.findOne(user.getId());
        return optionalUser.map(this::save);
    }

    @Override
    public Optional<Boolean> toggleActiveStatus(Long id) {
        Optional<User> optionalUser = this.findOne(id);
        return optionalUser.map(this::updateActiveStatus);
    }

    @Override
    public List<User> findMany() {
        return this.repository.findAll();
    }

    @Override
    public void checkDuplicateUsername(String username) {
        this.throwIfUsernameIsTaken(username);
    }

    @Override
    public List<String> findUserPermissionsNameOnly(User user) {
        Set<String> permissions = new HashSet<>();
        if (!isEmpty(user) && !isEmpty(user.getRoles())) {
            user.getRoles().forEach(role ->
                    role.getPermissions().forEach(
                            permission -> permissions.add(permission.getName()))
            );
        }
        return new ArrayList<>(permissions);
    }

    private Boolean updateActiveStatus(User user) {
        int count = this.repository.updateIsActiveStatus(!user.getIsActiveStatus(), user.getId());
        return count == 1;
    }

    public void throwIfUsernameIsTaken(String username) {
        Optional<User> optionalUser = this.repository.findByUsernameEqualsIgnoreCase(username);
        if (optionalUser.isPresent()) {
            throw new ResponseStatusException(BAD_REQUEST, String.format("USERNAME %s IS ALREADY TAKEN", username));
        }
    }
}

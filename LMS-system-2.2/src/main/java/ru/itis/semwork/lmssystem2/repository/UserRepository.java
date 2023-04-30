package ru.itis.semwork.lmssystem2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.semwork.lmssystem2.model.User;
import ru.itis.semwork.lmssystem2.model.enums.UserState;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByState(UserState userState);

    Optional<List<User>> findAllByState(UserState userState);

    Optional<User> findByEmail(String email);
}

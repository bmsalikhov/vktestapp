package ru.bmsalikhov.vktestapp.repos;

import org.springframework.data.repository.CrudRepository;
import ru.bmsalikhov.vktestapp.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsernameEquals(String username);
}

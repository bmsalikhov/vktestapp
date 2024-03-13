package ru.bmsalikhov.vktestapp.repos;

import org.springframework.data.repository.CrudRepository;
import ru.bmsalikhov.vktestapp.models.Log;

public interface LogsRepository extends CrudRepository<Log, Long> {
}

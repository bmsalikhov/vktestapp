package ru.bmsalikhov.vktestapp.services;

import org.springframework.stereotype.Service;
import ru.bmsalikhov.vktestapp.models.Log;
import ru.bmsalikhov.vktestapp.repos.LogsRepository;

@Service
public class LogsService {

    private LogsRepository logsRepository;

    public LogsService(LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }

    public void saveLog(Log log) {
        logsRepository.save(log);
    }

    public Iterable<Log> findAllLogs() {
        return logsRepository.findAll();
    }
}

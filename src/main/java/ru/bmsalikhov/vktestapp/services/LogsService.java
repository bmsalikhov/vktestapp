package ru.bmsalikhov.vktestapp.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
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
    public void saveLog(UserDetails userDetails, HttpServletRequest request, String result) {
        saveLog(new Log(
                userDetails.getUsername(),
                request.getRequestURI(),
                result
        ));
    }

    public Iterable<Log> findAllLogs() {
        return logsRepository.findAll();
    }
}

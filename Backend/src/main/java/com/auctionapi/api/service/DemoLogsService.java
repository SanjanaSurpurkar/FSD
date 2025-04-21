package com.auctionapi.api.service;

import com.auctionapi.api.entity.DemoLogsEntity;
import com.auctionapi.api.repositories.DemoLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoLogsService {

    @Autowired
    private DemoLogsRepository repository;

    public DemoLogsEntity createLog(DemoLogsEntity log) {

        if (log.getLogMessage() == null || log.getLogMessage().trim().isEmpty()) {
            throw new IllegalArgumentException("logMessage is required");
        }

        if (log.getLogLevel() == null || log.getLogLevel().trim().isEmpty()) {
            log.setLogLevel("INFO"); // Default if not provided
        }

        if (log.getLogTimestamp() == null) {
            log.setLogTimestamp(new java.util.Date());
        }

        return repository.save(log);
    }

    public DemoLogsEntity updateLog(DemoLogsEntity log) {
        return repository.save(log);
    }

    public List<DemoLogsEntity> getAllLogs(int pageNumber, int size) {
        Page<DemoLogsEntity> logsPage = repository.listAllLogsFromDB(PageRequest.of(pageNumber, size));
        return logsPage.getContent();
    }

    public Optional<DemoLogsEntity> getLogById(Long id) {
        return repository.findById(id);
    }

    public void deleteLog(Long id) {
        repository.deleteById(id);
    }

    public long countLogs() {
        return Long.parseLong(repository.countNumberOfLogs());
    }
}

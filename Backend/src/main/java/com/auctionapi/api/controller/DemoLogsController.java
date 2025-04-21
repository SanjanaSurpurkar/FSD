package com.auctionapi.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auctionapi.api.entity.DemoLogsEntity;
import com.auctionapi.api.service.DemoLogsService;
@RestController
@CrossOrigin
public class DemoLogsController {
    
    @Autowired
    private DemoLogsService demoLogsService;

    @RequestMapping(value = "/createLog", method = RequestMethod.POST)
    public ResponseEntity<?> createLog(@RequestBody DemoLogsEntity log) {
        try {
            return ResponseEntity.ok(demoLogsService.createLog(log));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/updateLog", method = RequestMethod.PUT)
    public ResponseEntity<?> updateLog(@RequestBody DemoLogsEntity log) {
        return ResponseEntity.ok(demoLogsService.updateLog(log));
    }

    @RequestMapping(value = "/listAllLogs", method = RequestMethod.GET)
    public ResponseEntity<?> listAllLogs(@RequestParam(defaultValue = "0") final Integer pageNumber,
                                          @RequestParam(defaultValue = "10") final Integer size) {
        return ResponseEntity.ok(demoLogsService.getAllLogs(pageNumber, size));
    }

    @RequestMapping(value = "/getLogById", method = RequestMethod.GET)
    public ResponseEntity<?> getLogById(@RequestParam Long id) {
        Optional<DemoLogsEntity> log = demoLogsService.getLogById(id);
        return log.isPresent() ? ResponseEntity.ok(log) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/deleteLog", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteLog(@RequestParam Long id) {
        demoLogsService.deleteLog(id);
        return ResponseEntity.ok("Log deleted successfully");
    }

    @RequestMapping(value = "/countLogs", method = RequestMethod.GET)
    public ResponseEntity<?> countLogs() {
        return ResponseEntity.ok(demoLogsService.countLogs());
    }
}

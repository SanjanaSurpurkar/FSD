package com.auctionapi.api.model;

import java.time.LocalDateTime;

public class DemoLogsRequestBody {

    private Long logId;
    private String logMessage;
    private String logLevel;
    private LocalDateTime logTimestamp;
    private Long userId;

    // Getter and Setter for logId
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    // Getter and Setter for logMessage
    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    // Getter and Setter for logLevel
    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    // Getter and Setter for logTimestamp
    public LocalDateTime getLogTimestamp() {
        return logTimestamp;
    }

    public void setLogTimestamp(LocalDateTime logTimestamp) {
        this.logTimestamp = logTimestamp;
    }

    // Getter and Setter for userId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

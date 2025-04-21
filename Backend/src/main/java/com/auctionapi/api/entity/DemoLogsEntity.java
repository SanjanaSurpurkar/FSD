package com.auctionapi.api.entity;

import java.util.Date;
import javax.persistence.*;
//import jakarta.persistence.*;

@Entity
@Table(name = "demo_logs")
public class DemoLogsEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false)
    private Long logId;

    @Column(name = "log_message", nullable = false, length = 255)
    private String logMessage;

    @Column(name = "log_level", nullable = false, length = 10)
    private String logLevel;

    @Column(name = "log_timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date logTimestamp;

    @Column(name = "user_id")
    private Long userId;

    // Getters and Setters
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Date getLogTimestamp() {
        return logTimestamp;
    }

    public void setLogTimestamp(Date logTimestamp) {
        this.logTimestamp = logTimestamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

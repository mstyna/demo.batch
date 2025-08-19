package com.tyna.demo.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component("DateTimeBatch")
public class DataTimeBatch implements RunnableJob {

    private static final Logger log = LoggerFactory.getLogger(DataTimeBatch.class);

    @Override
    public void executeBatch() {
        log.info("🚀 Starting DataTime Batch  - Getting Current DateTime Processing");
        log.info("📅 Current Date Time is " + LocalDateTime.now());
        log.info("✅ DataTime Batch completed successfully!");
    }

    @Override
    public String getJobName() {
        return "DataTime Batch";
    }
}

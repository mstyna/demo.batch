package com.tyna.demo.batch;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("DateTimeBatch")
public class DataTimeBatch implements RunnableJob{
    @Override
    public void executeBatch() {
        System.out.println("🚀 Starting DataTime Batch  - Getting Current DateTime Processing");
        System.out.println("📅 Current Date Time is " + LocalDateTime.now());
        System.out.println("✅ DataTime Batch completed successfully!");

    }

    @Override
    public String getJobName() {
        return "DataTime Batch";
    }
}

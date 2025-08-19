package com.tyna.demo.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class MainBatchLauncher {

    private final Map<String, RunnableJob> jobs;
    private static final Logger log = LoggerFactory.getLogger(MainBatchLauncher.class);

    @Autowired
    public MainBatchLauncher(Map<String, RunnableJob> runnableJobMap) {
        this.jobs = runnableJobMap;
    }

    @Bean
    public CommandLineRunner batchRunner () {
        return args -> {
            LocalDateTime startTime = LocalDateTime.now();
            log.info("==================================================");
            log.info("🚀 BATCH LAUNCHER STARTED");
            log.info("📅 Started at: " + startTime);
            log.info("==================================================");

            if (args.length == 0) {
                log.error("🚀 No Batch");
                return;
            }

            String jobName = args[0];
            RunnableJob job = jobs.get(jobName);

            if (job == null) {
                log.error("❌ Job '" + jobName + "' not found!");
                showAvailableJobs();
                System.exit(1);
            } else {
                try {
                    job.executeBatch();
                } catch (Exception e) {
                    log.info("💥 Job '" + jobName + "' failed: " + e.getMessage());
                    e.printStackTrace();
                    System.exit(1);
                }
            }

            LocalDateTime endTime = LocalDateTime.now();
            long executionTime = Duration.between(startTime, endTime).toMillis();
            log.info("⏱️  Total execution time: " + executionTime + "ms");
            log.info("==================================================");
        };
    }

    private void showAvailableJobs() {
        log.info("\n📋 Available Jobs:");
        jobs.forEach((name, job) -> {
            log.info("  • " + name + " - " + job.getJobName());
        });
    }

}

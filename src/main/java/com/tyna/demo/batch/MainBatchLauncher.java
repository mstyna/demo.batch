package com.tyna.demo.batch;

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

    @Autowired
    public MainBatchLauncher(Map<String, RunnableJob> runnableJobMap) {
        this.jobs = runnableJobMap;
    }

    @Bean
    public CommandLineRunner batchRunner () {
        return args -> {
            LocalDateTime startTime = LocalDateTime.now();
            System.out.println("==================================================");
            System.out.println("🚀 BATCH LAUNCHER STARTED");
            System.out.println("📅 Started at: " + startTime);
            System.out.println("==================================================");

            if (args.length == 0) {
                System.out.println("🚀 No Batch");
                return;
            }

            String jobName = args[0];
            RunnableJob job = jobs.get(jobName);

            if (job == null) {
                System.out.println("❌ Job '" + jobName + "' not found!");
                showAvailableJobs();
                System.exit(1);
            } else {
                try {
                    job.executeBatch();
                } catch (Exception e) {
                    System.out.println("💥 Job '" + jobName + "' failed: " + e.getMessage());
                    e.printStackTrace();
                    System.exit(1);
                }
            }

            LocalDateTime endTime = LocalDateTime.now();
            long executionTime = Duration.between(startTime, endTime).toMillis();
            System.out.println("⏱️  Total execution time: " + executionTime + "ms");
            System.out.println("==================================================");
        };
    }

    private void showAvailableJobs() {
        System.out.println("\n📋 Available Jobs:");
        jobs.forEach((name, job) -> {
            System.out.println("  • " + name + " - " + job.getJobName());
        });
    }

}

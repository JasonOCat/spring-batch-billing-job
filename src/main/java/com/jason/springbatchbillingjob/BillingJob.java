package com.jason.springbatchbillingjob;

import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BillingJob implements Job {

    private JobRepository jobRepository;

    public BillingJob(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public String getName() {
        return "Billing Job";
    }

    @Override
    public void execute(JobExecution execution) {
        //using path of parameters directly
//        String filePath = "./src/main/resources/billing-2025-01.csv";
//        Path path = Paths.get(filePath);
//        if (!Files.exists(path)) {
//            throw new IllegalArgumentException("Le fichier " + filePath + " n'existe pas ou n'est pas lisible.");
//        }
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("input.file", "/src/to/file.csv")
//                .toJobParameters();

        //get job parameters from command line java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar input.file=src/main/resources/billing-2025-01.csv
        JobParameters jobParameters = execution.getJobParameters();
        String inputFile = jobParameters.getString("input.file");
        System.out.println("processing billing information from file " + inputFile);
        execution.setStatus(BatchStatus.COMPLETED);
        execution.setExitStatus(ExitStatus.COMPLETED);
        this.jobRepository.update(execution);

    }

}

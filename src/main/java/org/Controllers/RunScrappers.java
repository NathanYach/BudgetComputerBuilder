package org.Controllers;

import org.Models.HardwareList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunScrappers {
    public HardwareList run(int budget, double gpuWeight, double cpuWeight, int tax) {

        // Display menu to get user's budget

        // Create a fixed-size thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Initialize GPU and CPU scrapers
        GPUScrapper GPU = new GPUScrapper();
        CPUScrapper CPU = new CPUScrapper();

        // Submit tasks to the thread pool for concurrent execution
        executor.submit(() -> GPU.run());
        executor.submit(() -> CPU.searchCPUS());

        // Shutdown the executor to prevent new tasks from being submitted
        executor.shutdown();

        try {
            // Wait for all tasks to complete or for a timeout
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Thread pool was interrupted: " + e.getMessage());
        }

        // Initialize the PC builder with GPU and CPU catalogs, budget, and weights
        BuildPC builder = new BuildPC(GPU.getGpuCatalog(), CPU.getCpuCatalog(), budget, gpuWeight, cpuWeight);
        // Build the PC based on the specified parameters
        return builder.build();
    }
}

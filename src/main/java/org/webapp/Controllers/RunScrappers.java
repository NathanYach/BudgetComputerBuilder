package org.webapp.Controllers;

import org.webapp.Services.HardwareService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunScrappers {
    public void run(HardwareService service) {

        // Display menu to get user's budge

        // Create a fixed-size thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Initialize GPU and CPU scrapers
        GPUScrapper GPU = new GPUScrapper(service);
        CPUScrapper CPU = new CPUScrapper(service);

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

    }
}

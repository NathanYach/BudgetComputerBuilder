/**
 * The Main class is the entry point of the PC building application.
 * It initializes the GPU and CPU scrapers, retrieves user input for budget, and initiates the PC building process.
 * The application uses multithreading to scrape GPU and CPU information concurrently.
 *
 * @author Nathan Yach
 */
import org.Controllers.CPUScrapper;
import org.Controllers.GPUScrapper;
import org.Controllers.BuildPC;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    /** The weight assigned to CPUs in the PC building algorithm. */
    private static final double cpuWeight = 0.4;

    /** The weight assigned to GPUs in the PC building algorithm. */
    private static final double gpuWeight = 0.6;

    /** The user's budget for building a PC. */
    static int budget;

    /**
     * The main method of the application. It initializes GPU and CPU scrapers,
     * retrieves the user's budget, and initiates the PC building process.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {

        // Display menu to get user's budget
        menu();

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
        builder.build();
    }

    /**
     * Displays a menu to get the user's budget for building a PC.
     */
    private static void menu() {

        // Loop until valid budget input is provided by the user
        while (true) {
            Scanner userInput = new Scanner(System.in);
            System.out.print("Please Enter Your Budget: $");
            if (userInput.hasNextInt()) {
                budget = userInput.nextInt();
                userInput.close();
                return;
            } else {
                System.out.println("Please Enter An Integer");
            }
        }
    }
}

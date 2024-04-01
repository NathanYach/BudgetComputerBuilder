# Budget PC Builder

This program is designed to scrape hardware information from various websites and assist in building a custom PC based on user-defined preferences and budget.

## Classes

### GPUScrapper (package: org.Controllers)

This class is responsible for scraping GPU (Graphics Processing Unit) information from a specific website. It utilizes Selenium WebDriver to navigate the website, extract relevant data, and store it in a catalog.

- search(): Scrapes GPU information from the website and adds it to the catalog.
- testEnd(WebElement row): Tests if the WebElement representing the row attribute DATA-LAST is true and checks if the next button is enabled.
- getGpuCatalog(): Retrieves the GPU catalog.

### CPUScrapper (package: org.Controllers)

Similar to GPUScrapper, this class is responsible for scraping CPU (Central Processing Unit) information from a website. It also utilizes Selenium WebDriver for web scraping.

- searchCPUS(): Scrapes CPU information from the website and adds it to the catalog.
- addCPU(CPU cpu): Adds a CPU to the catalog.
- testEnd(WebElement row): Tests if the WebElement representing the row attribute DATA-LAST is true and checks if the next button is enabled.

### ScrapperHelper (package: org.Controllers)

A helper class containing utility methods used by the scrapping classes.

- addComponent(T component, Map<Integer, List<T>> catalog): Adds a hardware component to the catalog.
- Other internal methods for comparison and retrieval of best hardware components.

### BuildPC (package: org.Controllers)

This class assists in building a custom PC based on scraped hardware information. It takes GPU and CPU catalogs, budget, and weights for GPU and CPU as parameters.

- BuildPC(Map<Integer, List<GPU>> gpuCatalog, Map<Integer, List<CPU>> cpuCatalog, int budget, double gpuWeight, double cpuWeight): Constructor for initializing PC builder with provided parameters.
- compareBenchmark(List<T> hardwareList): Compares benchmark values of hardware components and returns the best one.
- getBestComponent(int componentPrice, Map<Integer, List<T>> catalog): Retrieves the best component within a given price range from the catalog.
- build(): Builds a PC based on the provided budget, GPU and CPU weights, and available hardware options.
- findBetterComponent(int price, T bestComponent): Finds a better hardware component within a given price range.

### Hardware (package: org.Models)

This interface defines the structure for hardware components.

- Defines methods to retrieve and set price, name, power, benchmark, and rating for hardware components.

## Contributors

- Nathan Yach

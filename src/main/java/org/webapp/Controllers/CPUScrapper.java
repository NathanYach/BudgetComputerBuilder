/**
 * The CPUScrapper class is responsible for scraping CPU information from a web page.
 * It utilizes Selenium WebDriver to navigate the webpage and extract relevant CPU data.
 * The scraped CPU information is stored in a catalog, organized based on CPU price.
 * It works in conjunction with the ScrapperHelper class to add scraped CPUs to the catalog.
 *
 * @author Nathan Yach
 */

package org.webapp.Controllers;
import org.webapp.Models.CPU;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.webapp.Services.HardwareService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CPUScrapper {


    //Initialize selenium Chrome Driver
    private WebDriver chromeDriver;

    //Map to store CPUs Based on price
    private final Map<Integer, List<CPU>> cpuCatalog = new LinkedHashMap<>();

    HardwareService service;

    public CPUScrapper(HardwareService service) {this.service = service;
    }


    /**
     * Searches for CPUs on a web page, extracts relevant information, and adds them to the collection.
     *  Creates A headless instance of Chrome
     */
    public void searchCPUS(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        chromeDriver = new ChromeDriver(options);

        //Uses chromeDriver to navigate to webpage
        chromeDriver.get("https://www.cpubenchmark.net/cpu_value_available.html#single-value");
        //Find the CPU information element
        WebElement table = chromeDriver.findElement(By.xpath("//*[@id='single']/div/div[3]/ul"));
        //Find all row elements within the table
        List<WebElement> rows = table.findElements(By.tagName("li"));

        //Find all elements inside each row
        for(WebElement row: rows){

            WebElement linkElement = row.findElement(By.xpath(".//a"));

            CPU cpu = new CPU();

            //Extract text from each element within the row
            cpu.setName(linkElement.findElement(By.className("prdname")).getText());
            int rating =(int) (Double.parseDouble(linkElement.findElement(By.className("count")).getText().replaceAll("[^\\d.]", "")));
            cpu.setRating(rating);
            cpu.setBenchmark(Integer.parseInt(linkElement.findElement(By.className("mark-neww")).getText().replaceAll("[^\\d.]", "")));
            int price  = (int) Math.round(Double.parseDouble((linkElement.findElement(By.className("price-neww")).getText().replaceAll("[^\\d.]", ""))));
            cpu.setPrice(price);
            cpu.setCpuLink(linkElement.getAttribute("href"));
            service.saveCPU(cpu);

        }
        chromeDriver.quit();
    }
    /**
     *
     * @return Map containing CPU's
     */
    public Map<Integer, List<CPU>> getCpuCatalog() {
        return cpuCatalog;
    }
}

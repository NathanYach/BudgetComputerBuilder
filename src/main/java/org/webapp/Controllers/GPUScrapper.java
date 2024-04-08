/**
 * The GPUScrapper class is responsible for scraping GPU information from a web page.
 * It utilizes Selenium WebDriver to navigate the webpage and extract relevant GPU data.
 * The scraped GPU information is stored in a catalog, organized based on GPU price.
 * It works in conjunction with the ScrapperHelper class to add scraped GPUs to the catalog.
 *
 * @author Nathan Yach
 */

package org.webapp.Controllers;
import org.webapp.Models.GPU;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.webapp.Services.HardwareService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GPUScrapper {



    //Map to store GPUs Based on price
    private final Map<Integer, List<GPU>> gpuCatalog = new LinkedHashMap<>();

    //Initialize selenium Chrome Driver
    private WebDriver chromeDriver;

    HardwareService service;

    public GPUScrapper(HardwareService service) {this.service = service;
    }


    /**
     * Searches for GPUs on a web page, extracts relevant information, and adds them to the collection.
     */
    public void search(){


        List<WebElement> gpuRow;
        //Find the GPU information element
        WebElement gpuTableOut = chromeDriver.findElement(By.cssSelector("table[aria-label*='gpu comparison table']"));
        WebElement gpuTable = gpuTableOut.findElement(By.xpath(".//tbody"));

        //Find all row elements within the table
        gpuRow = gpuTable.findElements(By.tagName("tr"));

        //Find all elements inside each row
        for (WebElement row : gpuRow) {

            GPU gpu = new GPU();

            // Find all cells (columns) in the row
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<String> stringList = new ArrayList<>();

            //Get Text from cells within table
            //If I=4 get value of attribute "href"
            int i=0;
            for (WebElement cell : cells) {
                stringList.add(cell.getText());
                if(i==4){
                    stringList.add(cell.findElement(By.xpath(".//div/a")).getAttribute("href"));
                }
                i++;

            }
            //Set data retrieved into GPU object if string size is not 0

            if(stringList.size() != 0){
                gpu.setName(stringList.get(0));
                gpu.setPower(stringList.get(1));
                gpu.setVram(stringList.get(2));
                gpu.setLink(stringList.get(5).replaceAll("\\n", ""));
                gpu.setRating(Integer.parseInt(stringList.get(6)));
                gpu.setBenchmark(Integer.parseInt(stringList.get(3)));
                gpu.setPrice(Integer.parseInt(stringList.get(4).replaceAll("[^\\d.]", "")));
            }
            //Adds GPU to MAP and checks if end of row
            service.saveGPU(gpu);
            testEnd(row);
        }
    }

    /**
     * Tests if WebElement ROW attribute DATA-LAST is true
     * Checks if next Button is enabled, otherwise quites chromeDriver
     * @param row row The WebElement representing the row to test.
     */
    private void testEnd(WebElement row){
        String value;
            value = row.getAttribute("data-last");
            //Checks if row is last
            if(value != null){
                WebElement nextButton = chromeDriver.findElement(By.cssSelector("li[aria-label*='next page button']"));

                //Check if button is not disabled
                if(nextButton.getAttribute("data-disabled")== null){
                    nextButton.click();
                    search();
                }
                else {
                    chromeDriver.quit();
                }

            }
    }
    public Map<Integer, List<GPU>> getGpuCatalog() {
        return gpuCatalog;
    }


    /**
     * Creates a headless instance of Chrome
     *
     **/
    public void run() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        chromeDriver = new ChromeDriver(options);
        //Uses chromeDriver to navigate to webpage
        chromeDriver.get("https://bestvaluegpu.com");
        search();
    }
}

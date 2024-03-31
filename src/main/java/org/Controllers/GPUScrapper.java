package org.Controllers;

import org.Models.GPU;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class GPUScrapper {


    public Map<Integer, List<GPU>> getGpuCatalog() {
        return gpuCatalog;
    }

    private final Map<Integer, List<GPU>> gpuCatalog = new LinkedHashMap<>();
    private final WebDriver chromeDriver = new ChromeDriver();
    private List<WebElement> gpuRow= new ArrayList<>();
    private final List<WebElement> cells =new ArrayList<>();
    public void searchGPUS(){

        WebElement gpuTableOut = chromeDriver.findElement(By.cssSelector("table[aria-label*='gpu comparison table']"));
        WebElement gpuTable = gpuTableOut.findElement(By.xpath(".//tbody"));
        gpuRow = gpuTable.findElements(By.tagName("tr"));
        for (WebElement row : gpuRow) {

            GPU gpu = new GPU();

            // Find all cells (columns) in the row
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<String> stringList = new ArrayList<>();
            int i=0;
            for (WebElement cell : cells) {
                stringList.add(cell.getText());
                if(i==4){
                    stringList.add(cell.findElement(By.xpath(".//div/a")).getAttribute("href"));
                }
                i++;

            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(stringList.size() != 0){
                gpu.setName(stringList.get(0));
                gpu.setPower(stringList.get(1));
                gpu.setVram(stringList.get(2));
                gpu.setLink(stringList.get(5));
                gpu.setRating(Integer.parseInt(stringList.get(6)));
                gpu.setBenchmark(Integer.parseInt(stringList.get(3)));
                gpu.setPrice(Integer.parseInt(stringList.get(4).replaceAll("[^\\d.]", "")));
            }
            addGPU(gpu);
            testEnd(row);
        }
    }

    private int getRandomNumber(){

        Random rand = new Random();

        return rand.nextInt(15001) + 1000;
    }

    private void testEnd(WebElement row){
        String value ="hello";
        try {
            value = row.getAttribute("data-last");
            if(value != null){
                WebElement nextButton = chromeDriver.findElement(By.cssSelector("li[aria-label*='next page button']"));
                nextButton.click();
                searchGPUS();
            }
            else {
            }
        }catch (Exception e){
            chromeDriver.quit();
        }




    }

    private void addGPU(GPU gpu){
        if(gpuCatalog.containsKey(gpu.getPrice())){
            gpuCatalog.get(gpu.getPrice()).add(gpu);
        }
        else {
            List<GPU> gpuList = new ArrayList<>();
            gpuList.add(gpu);
            gpuCatalog.put(gpu.getPrice(), gpuList);
        }
    }
    public void initializeSearch(){
        chromeDriver.get("https://bestvaluegpu.com");
    }

    public List<GPU> getLowMap(int price){
        List<GPU> viableGPU = null;
        if(gpuCatalog.containsKey(price)){
            viableGPU = gpuCatalog.get(price);
        }
        return viableGPU;
    }

    public Runnable run(){
        searchGPUS();
        return null;
    }

}

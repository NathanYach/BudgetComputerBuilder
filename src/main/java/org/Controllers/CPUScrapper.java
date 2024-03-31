package org.Controllers;

import org.Models.CPU;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CPUScrapper {

    private final WebDriver chromeDriver = new ChromeDriver();

    private final Map<Double, List<CPU>> cpuCatalog = new LinkedHashMap<>();
    public void initializeSearch(){
        chromeDriver.get("https://www.cpubenchmark.net/cpu_value_available.html#single-value");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchCPUS(){


        WebElement table = chromeDriver.findElement(By.xpath("//*[@id='single']/div/div[3]/ul"));
        List<WebElement> rows = table.findElements(By.tagName("li"));

        for(WebElement row: rows){

            WebElement linkElement = row.findElement(By.xpath(".//a"));

            CPU cpu = new CPU();

            cpu.setName(linkElement.findElement(By.className("prdname")).getText());
            cpu.setRating(Double.parseDouble(linkElement.findElement(By.className("count")).getText().replaceAll("[^\\d.]", "")));
            cpu.setBenchmark(Integer.parseInt(linkElement.findElement(By.className("mark-neww")).getText().replaceAll("[^\\d.]", "")));
            cpu.setPrice(Double.parseDouble(linkElement.findElement(By.className("price-neww")).getText().replaceAll("[^\\d.]", "")));
            cpu.setCpuLink(linkElement.getAttribute("href"));
            addCPU(cpu);
        }
        chromeDriver.quit();
    }

    private void addCPU(CPU cpu){
        if(cpuCatalog.containsKey(cpu.getPrice())){
            cpuCatalog.get(cpu.getPrice()).add(cpu);
        }
        else {
            List<CPU> cpuList = new ArrayList<>();
            cpuList.add(cpu);
            cpuCatalog.put(cpu.getPrice(), cpuList);
        }
    }

    public Map<Double, List<CPU>> getCpuCatalog() {
        return cpuCatalog;
    }
}

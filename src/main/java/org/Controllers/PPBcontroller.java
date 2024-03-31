package org.Controllers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class PPBcontroller {

    WebDriver chromeDriver = new ChromeDriver();


    public void initalizeSearch(){

        chromeDriver.get("https://ca.pcpartpicker.com");


        WebElement search = chromeDriver.findElement(By.cssSelector("a[aria-label*='Search Products']"));
        search.click();
        search = chromeDriver.findElement(By.cssSelector("input[aria-label*='Search Products']"));
        search.sendKeys("RTX 3060");
        search.sendKeys(Keys.RETURN);

    }

    public int randomTime(){

        Random rand = new Random();
        return 0;
    }
}

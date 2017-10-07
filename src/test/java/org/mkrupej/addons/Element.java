package org.mkrupej.addons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class Element {

    public static void clickByXpath (WebDriver driver, String xpath) {

        Actions act = new Actions(driver);
        act.click(driver.findElement(By.xpath(xpath))).build().perform();
    }

    public static void findByXpath (WebDriver driver, String xpath) {

        driver.findElement(By.xpath(xpath));
    }

    public static void inputTextByXpath(WebDriver driver, String xpath, String input){
        driver.findElement(By.xpath(xpath)).sendKeys(input);
    }

    public static void clearTextFromXpath(WebDriver driver, String xpath){
        driver.findElement(By.xpath(xpath)).clear();
    }

}

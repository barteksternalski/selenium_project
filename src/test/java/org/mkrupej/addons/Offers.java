package org.mkrupej.addons;

import org.mkrupej.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class Offers {
    private final static Logger LOGGER = Logger.getLogger(Test.class.getName());

    public static int getOpenPositionsInCity(WebDriver driver, String xpath){

        int openPositions = 0;

        String result = driver.findElement(By.xpath("//div/span/span[contains(., 'results')]")).getText();
        LOGGER.info("Numbers of available jobs: " + result);

        String numberOfPositions = result.substring(0,1);
        openPositions = Integer.parseInt(numberOfPositions);

        return openPositions;
    }


}

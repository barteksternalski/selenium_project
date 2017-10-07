package org.mkrupej;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.mkrupej.addons.Element;
import org.mkrupej.addons.Offers;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test
{
    private static WebDriver driver = null;

    @BeforeClass
    public static void oneTimeSetUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before
    public void setup(){
        driver.get("http://www.infusion.com");
        Element.clickByXpath(driver, "//nav/em/span");
        Element.clickByXpath(driver, "//li/a[text()='Join']");
        Element.clickByXpath(driver, "//a[text()='Explore careers']");
        Element.clickByXpath(driver, "//div/span[text()= 'Explore Avanade Careers']");
        Element.clickByXpath(driver, "//a[contains(@id,'jobsearchclick')]");
    }

    @AfterClass
    public static void tearDown() throws Throwable{
        driver.close();
        driver.quit();
    }

    @org.junit.Test
    /**
     There is a total of 5 results for Location: “Krakow”
     */
    public void testKrakow() throws  Throwable{

        Element.inputTextByXpath(driver, "//input[contains(@class,'form-control')]", "krakow");
        Thread.sleep(1000);
        int openPositionsInKrakow = Offers.getOpenPositionsInCity(driver, "//span/span[contains(@class, 'meta-result-count')]");
        Assert.assertEquals(4, openPositionsInKrakow);
        Element.clearTextFromXpath(driver, "//input[contains(@class,'form-control')]");
    }

    @org.junit.Test
    /**
     There is at least 1 result for Location “Warsaw”
     */
    public void testWarsaw() throws Throwable{

        Element.inputTextByXpath(driver, "//input[contains(@class,'form-control')]", "warsaw");
        Thread.sleep(1000);
        int openPositionsInWarsaw = Offers.getOpenPositionsInCity(driver, "//span/span[contains(@class, 'meta-result-count')]");
        Assert.assertTrue(openPositionsInWarsaw > 1);
    }

    @org.junit.Test
    /**
      One of the qualifications for job offer: Location: “Wroclaw”/  QA Analyst is:
     “Developing automated integration and smoke tests using modern tools including Selenium, SoapUI (web front-end and services)”
     */
    public void testWroclaw() throws Throwable{

        String qualification = "Developing automated integration and smoke tests using modern tools including Selenium, SoapUI (web front-end and services)";

        Element.inputTextByXpath(driver, "//input[contains(@class,'form-control')]", "wroclaw software qa");
        Thread.sleep(1000);
        //find result - QA analysts in Wroclaw
        Element.clickByXpath(driver, "//td[contains(., 'Wrocław')]/preceding-sibling::td[contains(.,'QA Analyst')]/a[2]");
        Element.findByXpath(driver,"//h4[contains(.,'Qualifications')]/following-sibling::ul/li[contains(.,'"+ qualification +"')]");
    }


}

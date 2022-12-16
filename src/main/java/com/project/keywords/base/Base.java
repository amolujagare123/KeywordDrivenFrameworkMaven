package com.project.keywords.base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//responsible for driver excution
public class Base {

  public static WebDriver driver;

    public static WebDriver init_driver(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();

        return driver;

    }

 }

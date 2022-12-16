package com.project.keywords.keywordEngine;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;

import static com.project.keywords.base.Base.driver;
import static com.project.keywords.base.Base.init_driver;

public class KeyWordEngine {
    String sheetPath = "KeywordExcel/Keywords1.xlsx";
    public void startEngine(String sheetName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(sheetPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();

        for (int i=1 ; i<rowCount; i++)
        {

            XSSFRow row = sheet.getRow(i);
            String locatorColVal = row.getCell(1).toString().trim();
            String locatorName = "";
            String locatorValue = "";

            if(!locatorColVal.equalsIgnoreCase("NA"))// is not equal to NA
            {
                locatorName  = locatorColVal.split("=")[0]; // "id=login-username"
                locatorValue = locatorColVal.split("=")[1];
            }

            String action = row.getCell(2).toString().trim();
            String value  = row.getCell(3).toString().trim();

            switch (action)
            {
                case "open browser" : init_driver(value);
                                            break;
                case "enter url" : driver.get(value);  break;
                case "quit" : driver.close();  break;
                case "assertWithUrl" :
                    Assert.assertEquals(driver.getCurrentUrl(),value
                    ,"This is not a required page");
                    break;
                case "assertWithPageTitle" :
                    Assert.assertEquals(driver.getTitle(),value
                            ,"This is not a required page");
                    break;
            }

            switch (locatorName)
            {
                case "id" : if(action.equalsIgnoreCase("click"))
                                driver.findElement(By.id(locatorValue)).click();
                            else if(action.equalsIgnoreCase("sendkeys"))
                                driver.findElement(By.id(locatorValue)).sendKeys(value);
                            break;
                case "name" : if(action.equalsIgnoreCase("click"))
                                    driver.findElement(By.name(locatorValue)).click();
                                else if(action.equalsIgnoreCase("sendkeys"))
                                    driver.findElement(By.name(locatorValue)).sendKeys(value);
                                    break;

                case "linkText" :
                    driver.findElement(By.linkText(locatorValue)).click();
                    break;
            }

        }


    }
}

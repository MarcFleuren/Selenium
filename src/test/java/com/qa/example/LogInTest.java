package com.qa.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.io.*;
import java.util.Iterator;



public class LogInTest{

private static WebDriver driver;
private static final String FILE_NAME = System.getProperty("user.dir") + "\\ExcelExample.xlsx";

@Before
public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
    }


    @Test
public void logIn()throws InterruptedException,IOException{
        try{
            String parts[] = readFromExcel();
            System.out.print(parts[2]);
        driver.get("https://flipboard.com/");
        driver.manage().window().fullscreen();

        Thread.sleep(5000); // Let the user actually see something!
            WebElement signin = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[1]/span/div"));
            signin.click();

        WebElement username=driver.findElement(By.xpath("//*[@id=\'main\']/div/span[2]/div/div[2]/div[2]/div/div[1]/div/div[3]/input"));
        username.sendKeys(parts[0]);
        WebElement name=driver.findElement(By.xpath("//*[@id=\'main\']/div/span[2]/div/div[2]/div[2]/div/div[1]/div/div[4]/input"));
        name.sendKeys(parts[2]);


        Thread.sleep(5000);

        WebElement login = driver.findElement(By.xpath("//*[@id=\'main\']/div/span[2]/div/div[2]/div[2]/div/div[1]/div/button/span"));
        login.click();

        Thread.sleep(5000); // Let the user actually see something!

        //Create screenshot
        File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File("C:\\Users\\admin\\Desktop\\img2.jpg"));

        //write username etc. to excel


        }finally{
        }
        };
public String[] readFromExcel(){
        try{

        FileInputStream excelFile=new FileInputStream(new File(FILE_NAME));
        Workbook workbook=new XSSFWorkbook(excelFile);
        Sheet datatypeSheet=workbook.getSheetAt(0);
        Iterator<Row> iterator=datatypeSheet.iterator();
        String[] parts2;
        while(iterator.hasNext()){
        String row = "";
        Row currentRow=iterator.next();
        Iterator<Cell> cellIterator=currentRow.iterator();

        while(cellIterator.hasNext()){

        Cell currentCell=cellIterator.next();
        //getCellTypeEnum shown as deprecated for version 3.15
        //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
        if(currentCell.getCellTypeEnum()==CellType.STRING){
        row = row + currentCell.getStringCellValue()+"-";
        }

        }
    System.out.println(row);
        System.out.println();
            String[] parts = row.split("-");
            return parts;
//            if(parts[0].equals(" marc@marc.me.uk")) {
//
//                return parts;
//            } else{
//                continue label149;
//            }


        }

        }catch(FileNotFoundException e){
        e.printStackTrace();
        }catch(IOException e){
        e.printStackTrace();
        }
    return null;
        };
    @After
    public void tearDown() {
        driver.quit();


}
        }
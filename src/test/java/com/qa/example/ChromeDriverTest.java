package com.qa.example;

import org.apache.poi.ss.usermodel.*;
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

import java.io.*;
import java.util.Iterator;

public class ChromeDriverTest {

	private static WebDriver driver;
	private static final String FILE_NAME = System.getProperty("user.dir") + "\\ExcelExample.xlsx";

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void testGoogleSearch() throws InterruptedException , IOException {
		// Optional, if not specified, WebDriver will search your path for chromedriver.
		try {
			driver.get("https://flipboard.com/");
			driver.manage().window().fullscreen();

			Thread.sleep(5000); // Let the user actually see something!

			WebElement signup = driver.findElement(By.xpath("//*[@id='front-door-banner']/button"));
			signup.click();
			WebElement name = driver.findElement(By.xpath("//*[@id=\"main\"]/div/span[2]/div/div[2]/div[2]/div/div/form/div[2]/input"));
			name.sendKeys("Marc");


			Thread.sleep(5000);

			WebElement email = driver.findElement(By.xpath("//*[@id=\"main\"]/div/span[2]/div/div[2]/div[2]/div/div/form/div[3]/input"));
			email.sendKeys("marc@marc.me.uk");

			Thread.sleep(5000); // Let the user actually see something!

			//Create screenshot
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("C:\\Users\\admin\\Desktop\\img.jpg"));

			//write username etc. to excel
			writeToExcel();


		} finally {
		}
	};

public void writeToExcel() {

			System.out.println("Creating excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("logIn");
			Object[][] datatypes = {

					{"marc@marc.me.uk", "Marc", "password1"},

			};
			int rowNum = 0;

			for (Object[] value : datatypes) {
				Row row = sheet.createRow(rowNum++);
				int colNum = 0;
				for (Object field : value) {
					Cell cell = row.createCell(colNum++);
					if (field instanceof String) {
						cell.setCellValue((String) field);
					} else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					}
				}
			}

			try {
				FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
				workbook.write(outputStream);
				workbook.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Done");
		};




	@After
	public void tearDown() {
		driver.quit();
	}

}

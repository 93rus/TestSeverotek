package ru.igorAkintev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class WebTestSetting {
    //Apply driver
    WebDriver driver;
    String linkTest = "https://igorakintev.ru/admin/";
    String linkBlog = "https://igorakintev.ru/blog/";
    String linkAdminBlog = "https://igorakintev.ru/admin/blog/entry/";

    //Test link variables
    String title = "Войти | Панель управления";
    String userName = "selenium";
    String userPassword = "super_password";
    String controlPanel = "Панель управления";
    int timeWait = 10;
    StringBuilder randString;
    //Build logging
    static Logger log;

    static {
        try {
            FileInputStream fis = new FileInputStream(
                    "C:\\ProjectByIdea\\GitHub\\TestTask\\src\\" +
                            "test\\java\\ru\\igorAkintev\\logging.properties");
            LogManager.getLogManager().readConfiguration(fis);
            log = Logger.getLogger(WebTest.class.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Check start driver
    @BeforeTest
    public void startTest() {
        driver = new ChromeDriver();
        try {
            driver.get(linkTest);
            log.info("Driver OK\n Test start");
        } catch (Exception e) {
            log.severe("Driver error");
        }

        assertEquals(title, driver.getTitle());
        log.info("URL successfully open");

        //Find and enter by username
        try{
            WebElement user = driver.findElement(By.cssSelector("#id_username"));
            user.sendKeys(userName);
            log.info("User successfully added");
        }catch (Exception e){
            log.severe("Element USER not found");
        }

        //Find and enter by password
        try {
            WebElement password = driver.findElement(By.cssSelector("#id_password"));
            password.sendKeys(userPassword);
            log.info("Password successfully added");
        }catch (Exception e){
            log.severe("Element PASSWORD not found");
        }

        //Find and click button Enter
        try{
            WebElement buttonEnter = driver.findElement(By.cssSelector(".submit-row"));
            buttonEnter.click();
            log.info("Button click successfully");
        }catch (Exception e){
            log.severe("Button not found");
        }
        //Wait download page
        WebDriverWait wait = new WebDriverWait(driver, timeWait);

        //get random string
        String symbols = "qwerty123321233211";
        randString = new StringBuilder();
        int count = (int)(Math.random()*30);
        for(int i=0;i<count;i++)
            randString.append(symbols.charAt((int)(Math.random()*symbols.length())));

    }
    //Check quit driver
   @AfterTest
    public void endTest() {
        try {
            driver.quit();
            log.info("Successfully close");
        } catch (Exception e) {
            log.severe("Driver error");
        }

    }
}

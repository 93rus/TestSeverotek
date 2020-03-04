package ru.igorAkintev;


import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;



public class WebTest extends WebTestSetting {


    @Test
    public void findControlPanel(){
        // Find and equals control panel

        driver.findElement(By.xpath("//*[contains(text(), controlPanel)]"));
        log.info("Control Panel Found");
    }

    @Test
    public void testingAddBlog() {

        WebElement addEntries = driver.findElement(By.id("module_2"));
        addEntries.findElement(By.cssSelector("[href=\"/admin/blog/entry/add/\"]")).click();

        driver.findElement(By.xpath("//*[contains(text(), 'Добавить entry')]"));
        log.info("Добавить entry found");

        //find and add title
        WebElement addTitle = driver.findElement(By.cssSelector("#id_title"));

        addTitle.sendKeys(randString);
        log.info("Title successfully add " + randString);

        //find and add slug
        WebElement addSlug = driver.findElement(By.cssSelector("#id_slug"));
        addSlug.sendKeys(randString);
        log.info("Slug successfully add " + randString);

        //find and add Text Markdown
        WebElement addTextMarkdown = driver.findElement(By.cssSelector("#id_text_markdown"));
        addTextMarkdown.sendKeys(randString);
        log.info("Text Markdown successfully add " + randString);

        //find and add Text
        WebElement addText = driver.findElement(By.cssSelector("#id_text"));
        addText.sendKeys(randString);
        log.info("Text successfully add " + randString);

        //find and click button Save
        WebElement buttonSave = driver.findElement(By.cssSelector(".default"));
        buttonSave.click();

        //get page
        driver.get(linkBlog);
        WebDriverWait waitBlog = new WebDriverWait(driver, timeWait);
        driver.findElement(By.xpath("//*[contains(text(), randString)]"));
        log.info("Post successfully found");

        //delete my post
        driver.get(linkAdminBlog);
        driver.findElement(By.xpath("//tr/th/a[contains(text(), randString)]")).click();

        WebDriverWait waitAdminBlog = new WebDriverWait(driver, timeWait);
        driver.findElement(By.cssSelector(".deletelink")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"")).click();
        log.info("Delete post successfully");

    }
}




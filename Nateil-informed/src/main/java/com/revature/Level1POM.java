package com.revature;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class Level1POM {

    private WebDriver driver;

    @FindBy(id = "randomString")
    private WebElement randomName;
    @FindBy(id = "nameInput")
    private WebElement nameInput;
    @FindBy( tagName = "button")
    private WebElement submitButton;

    public Level1POM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void openLevel1(){
        driver.get("C:\\Users\\EricSuminski\\Desktop\\informed\\Important Resources\\InFormed\\level-1.html");
    }

    public void submitRandomName(){
        String name = randomName.getText();
        nameInput.sendKeys(name);
        submitButton.click();
    }

    public void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/main/resources/screenshots/level-1.png"));
    }

    public static void main(String[] args) {
        WebDriver driver = null;
        try{
            driver = new ChromeDriver();
            Level1POM pom = new Level1POM(driver);
            pom.openLevel1();
            pom.submitRandomName();
            driver.switchTo().alert().accept();
            pom.takeScreenshot();
        } catch (IOException e){
            System.out.println(e.getMessage());
        } finally {
            if(driver != null) driver.quit();
        }
    }

}

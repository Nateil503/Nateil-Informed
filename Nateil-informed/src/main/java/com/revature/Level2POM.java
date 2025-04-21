package com.revature;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v133.io.IO;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Level2POM {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(tagName = "dd")
    private WebElement answers;
    @FindBy(name = "input1")
    private WebElement dropValue;
    @FindBy(tagName = "button")
    private WebElement submitButton;
    @FindBy(tagName = "radio")
    private WebElement radioValue;
    @FindBy(name = "dateInput")
    private WebElement dateInput;

    @FindBy(name = "checkbox1")
    private WebElement checkbox1;
    @FindBy(name = "checkbox2")
    private WebElement checkbox2;
    @FindBy(name = "checkbox3")
    private WebElement checkbox3;


    public Level2POM(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openLevel2() {
        driver.get("C:\\Users\\Tremayne Miller\\Desktop\\Nateil-Informed\\Nateil-informed\\Important Resources\\InFormed\\level-2.html");
    }

    public void submitForm(){

        wait.until(ExpectedConditions.visibilityOfAllElements(answers));

        String name = dropValue.getText();

        Select select = new Select(dropValue);
        select.selectByValue(String.valueOf(dropValue));

        dropValue.sendKeys(name);
        submitButton.click();

        String radio = radioValue.getText();
        radioValue.sendKeys(radio);

        if (!checkbox1.isSelected()) checkbox1.click();
        if (!checkbox2.isSelected()) checkbox2.click();
        if (!checkbox3.isSelected()) checkbox3.click();

        String date = dateInput.getText();
        dateInput.sendKeys(date);
    }

    public void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/main/resources/screenshots/level-2.png"));

    }

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            Level2POM pom = new Level2POM(driver);
            pom.openLevel2();
            pom.submitForm();
            driver.switchTo().alert().accept();
            pom.takeScreenshot();
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        } finally {
            if (driver != null) driver.quit();
        }
    }
}



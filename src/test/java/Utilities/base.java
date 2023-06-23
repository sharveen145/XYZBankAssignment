package Utilities;

import Managers.AllDriverManager;
import org.apache.hc.core5.util.Asserts;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;


public class base extends AllDriverManager {

    public WebElement getElement(By by){
        WebElement ele = getDriver().findElement(by);
        return ele;
    }

    public WebElement getElements(By by){
        WebElement ele = (WebElement) getDriver().findElements(by);
        return ele;
    }

    protected static final Logger logger = Logger.getLogger(base.class.getName());

    public void fluentwait(By ele){
        Wait<WebDriver>fw = new FluentWait<WebDriver>(getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        fw.until(ExpectedConditions.visibilityOfElementLocated((ele)));
    }

    public void launchApplication(){
        getDriver().get(getUrl());
    }

    public void elementScreenShot(WebElement ele){
        File src = ele.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir")+"resources/screenshots/"+ele+".png");
    }

    public void scrollToBottom(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollTillElement(WebElement ele){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", ele);
    }

    public void scrollUp(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight), 0");
    }

    public void dragAndDrop(WebElement element, WebElement target){
        (new Actions(getDriver())).dragAndDrop(element, target).perform();
    }


}
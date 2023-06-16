package Utilities;

import Managers.AllDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

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

    private static java.lang.String String;
//    public static Logger logger = Logger.getLogger(String);
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

}
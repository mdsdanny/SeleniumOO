package com.seleniumOO.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.seleniumOO.SOOFramework;
import com.seleniumOO.util.SOOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class represents a HTML page.
 * Classes that extends this one, like your own page, could have specific functionality for the HTML elements on it. Element instances of SOOUIElement class.
 *
 * Created by Daniela Sánchez on 10/02/2017
 */
public abstract class SOOPage implements WebDriver{

    private final WebDriver webDriver;

    protected SOOPage(SOOFramework fw){
        this.webDriver = fw.getWebDriver();
    }

    @Override
    public void get(String s) {
        this.webDriver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return this.webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return this.webDriver.getTitle();
    }

    public List<SOOUIElement> findElementsSOO(By byOO) {
        List<SOOUIElement> sooElements = new ArrayList<SOOUIElement>();
        List<WebElement> elements = findElements(byOO);
        for (WebElement we : elements){
            sooElements.add(new SOOUIElement(we));
        }
        return sooElements;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.webDriver.findElements(by);
    }

    @Override
    public SOOUIElement findElement(By by) {
        return new SOOUIElement(webDriver.findElement(by));
    }

    @Override
    public String getPageSource() {
        return this.webDriver.getPageSource();
    }

    @Override
    public void close() {
        this.webDriver.close();
    }

    @Override
    public void quit() {
        this.webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return this.webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return this.webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return this.webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return this.webDriver.navigate();
    }

    @Override
    public Options manage() {
        return this.webDriver.manage();
    }

    public abstract void validateElements() throws SOOException;

    protected WebDriver getWebDriver(){
        return webDriver;
    }
}

package com.seleniumOO.ui;

import com.seleniumOO.util.SSOElementType;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a UI element of the BROWSER page. A HTML element.
 * Manages a WebDriver instance and a WebElement instance.
 *
 * Created by Daniela Sánchez on 09/02/2017
 */
public class SOOUIElement implements WebElement{

    private WebDriver webDriver;
    WebElement webElement;

    public SOOUIElement(WebElement we) {
        this.webElement = we;
    }

    /**
     * A new object of SOOUIElement class. That represents a HTML element.
     * @param page the SOOPage of the element that belongs to.
     * @param elementType SSOElementType property type. Like 'name', 'id', 'class'...
     * @param value the property value. Like 'oneId' or 'oneClass'.
     */
    public SOOUIElement(SOOPage page, SSOElementType elementType, String value){
        this.webDriver = page.getWebDriver();
        switch (elementType){
            case ID :
                webElement = webDriver.findElement(By.id(value));
                break;
            case NAME :
                webElement = webDriver.findElement(By.name(value));
                break;
            case CLASS :
                webElement = webDriver.findElement(By.className(value));
                break;
            case LABEL :
                webElement = webDriver.findElement(By.cssSelector("[label='"+value+"']"));
                break;
            case TYPE :
                webElement = webDriver.findElement(By.cssSelector("[type='"+value+"']"));
                break;
            case XPATH :
                webElement = webDriver.findElement(By.xpath(value));
                break;
            case LINKTEXT :
                webElement = webDriver.findElement(By.linkText(value));
                break;
            default:

        }
    }

    @Override
    public void click() {
        webElement.click();
    }

    @Override
    public void submit() {
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        webElement.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        webElement.clear();
    }

    @Override
    public String getTagName() {
        return webElement.getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return webElement.getAttribute(s);
    }

    @Override
    public boolean isSelected() {
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webElement.findElements(by);
    }

    @Override
    public SOOUIElement findElement(By by) {
        return new SOOUIElement(webElement.findElement(by));
    }

    /**
     * You can search this element for others SOOUIElement's inside of this one. By a standard search with
     * org.openqa.selenium.By definition.
     *
     * @param byOO org.openqa.selenium.By definition.
     * @return a List<SOOUIElement>
     */
    public List<SOOUIElement> findElementsSOO(By byOO) {
        List<SOOUIElement> sooElements = new ArrayList<SOOUIElement>();
        List<WebElement> elements = findElements(byOO);
        for (WebElement we : elements){
            sooElements.add(new SOOUIElement(we));
        }
        return sooElements;
    }

    @Override
    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return webElement.getRect();
    }

    @Override
    public String getCssValue(String s) {
        return webElement.getCssValue(s);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return webElement.getScreenshotAs(outputType);
    }
}

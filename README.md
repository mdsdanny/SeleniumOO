# SeleniumOO
Java library that extends Selenium core into a OO design project. This is done by not calling the driver directly each time you want to access an HTML element, but instead, creating an element that represents the HTML element of a specific page. This library also wants to encourage to use the page object concept. All into OO design patterns. Patterns used: Adapter, Factory, etc.

## Requirements
* Java SDK >= 1.8
* selenium-java >= 3.3.1

## Import SeleniumOO

You can import by download this library from github or you can add a maven dependency on your project pom.xml:

###
```xml
<dependency>
    <groupId>com.github.mdsdanny</groupId>
    <artifactId>seleniumOO</artifactId>
    <version>1.0.0</version>
    <scope>test</scope>
</dependency>
```

## Initialize SelenniumOO

You will need to keep in your tests context, a reference to the SeleniumOO library:

* **On tests started, initialize the SeleniumOO library** 

### The following example extends the RunListener class of junit library:

If I named our listener **SOOFrameworkHelper**:

```java
class SOOFrameworkHelper extends RunListener{

    private static SOOFramework framework;
    public SOOFrameworkHelper() {

    }
    public void testRunStarted(Description description) throws Exception {
       framework = SOOFactory.initializeFW("config.properties", "browserType", "driverLocation");
    }

    public static SOOFramework getFramework() {
        return framework;
    }
}
```
The SOOFactory.initializeFW() takes three paramenters:
1.  **resourceName** - That is the name of the properties file you wish to include on your tests. As example. I wrote config.properties under the test/resources folder.
2.  **browserTypeKey** - Is the key of the properties file were you specified the browser type. In this case. browserType=firefox or browserType=chrome or browserType=ie
3.  **driverKey** - Is the key of the properties file were you specified the path of the driver to use. In this case. driverLocation = /home/daniela/Desktop/geckodriver

You must implement the call to **SOOFactory.initializeFW()** at the start of your suite. This way I just mentioned, is my suggestion. You can call this method with no params(if you dont want to used a properties file), so no property file will we set and firefox as default browser.

You can also pass the values directly with no properties file:
**SOOFactory.initializeFW(String browser, String driverPath, String gridHubAddress)**
```java
SOOFactory.initializeFW("firefox","/home/daniela/Desktop/geckodriver", null)
```

## Creating a new page

* **Create the page you wish to test** 

### The following example extends the SOOPage class of SeleniumOO library. Lets test google search page as example:

```java
class GooglePage extends SOOPage {
    SOOUIElement searchField;

    public GooglePage(SOOFramework fw){
        super(fw);
    }

    @Override
    public void validateElements() throws SOOException {
        searchField = SOOFactory.createElement(this, SSOElementType.NAME, "q");
        if(!searchField.isEnabled() || !searchField.isDisplayed()){
            throw new SOOException("The search button is not on the page");
        }
    }

    public void search(String query){
        searchField.sendKeys(query);
        searchField.sendKeys(Keys.ENTER);
    }

    public SOOUIElement getSearchField() {
        return searchField;
    }
}
```
The GooglePage class is now our page object. This is the place to list all of the UI elements in the page, and their behaviour.

1.  **Constructor** - The constructor takes an instance of the current SeleniumOO framework object. In this example is SOOFramework instance, that belongs to SeleniumOO library.
2.  **validateElements()** - This method is implemented in each of our pages. Is were we make sure of the existence of our elements. In this case, instance of SOOUIElement class. SOOUIElement is a child of seleniumhq WebElement object. 
4.  **SOOFactory.createElement()** - The fastest way of creating SOOUIElement objects. In this example, we initialize the element with the current page, a SSOElementType the type is NAME and the value "q". It maps the HTML element: 

```html
<input name="q"/>
```
3.  **search()** - We want to search for a word. So we call selenium sendKeys() and sendKeys() method's to perform the search user's action.

Also to keep the object oriented paradigm, I've created a getSearchField() method. 
All of the UI elements and actions of our UI page should be defined in our object. So it will be reusable and independent of another one.


## Finnally: The Test

* **Our main test** 
* The following example extends the SOOTest class of SeleniumOO library. It's a child of junit TestCase library. You can implement another testing utility. Your free to choose.

```java
class GoogleTest extends SOOTest{
    GooglePage googlePage = null;
    SOOConfig config = null;
    
    public GoogleTest(){
        SOOFramework fw = SOOFrameworkHelper.getFramework();
        googlePage = new GooglePage(fw);
        config = fw.getConfig();
    }

    @Test
    public void testGoogle() {
        try {
            String url = config.getProperty("appUrl");
            this.googlePage.navigate().to(url);
            this.googlePage.validateElements();
            this.googlePage.search("SeleniumOO");

        } catch (SOOException e) {
            e.printStackTrace();
            GoogleTest.fail();
        }
    }

    public void tearDown() {
        googlePage.quit();
    }
```


1.  **Constructor** - The constructor gets the SOOFramework object already instantiated. And creates the GooglePage to be tested. Also the config file is keeped in context(if we decide to implement a config file).
2.  **testGoogle()** - Here we navigate to the the page and we make sure to validate the elements on our GooglePage. Finally we perform the search action. 
4.  **tearDown()** - This is just for closing the browser instance. Of course will depend if you want to implemented here or any other place of your suite. 


## SOO Classes

![Screenshot](soo.png)


## Detail Information

* com.seleniumOO.SOOFramework: 
This class is the main loaded of the Selenium OO library. Takes care of the initialization of the browser and a possible .properties file. Manages a WebDriver instance and a SOOConfig instance.

* com.seleniumOO.ui.SOOPage: 
This class represents a HTML page. Classes that extends this one, like your own page, could have specific functionality for the HTML elements on it. Element's instances of SOOUIElement class. Manages a WebDriver instance.

* com.seleniumOO.ui.SOOUIElement: 
This class represents an UI element of the BROWSER page. A HTML element. Manages a WebDriver instance and a WebElement instance.

* com.seleniumOO.util.SOOFactory: 
SOOFactory allows to, easily initialize the SOO library and also create SOOUIElement's.

* com.seleniumOO.util.SSOElementType: 
Enum used for specified an attribute of a SOOUIElement(browser element) object.

* com.seleniumOO.util.SOOException: 
This is the SOO library exception object. All exceptions that happens on the library, will thrown a SOOException object.

* com.seleniumOO.util.SOOConfig: 
This SOOConfig class represents a .properties file with some configuration of your project. You could extend this class for your own .properties configuration file behavior. Manages a Properties instance.

* com.seleniumOO.test.SOOTest: 
This is the main test class of this Selenium OO library. Extends from junit.framework.TestCase class. Classes that extends this one, should navigate to the initial url of your choice by calling yourPage.navigate().to("url"). yourPage variable must be a child class of SOOPage Class. Classes that extends this one, should implement the tearDown() Core Selenium method to close the BROWSER. To close the BROWSER just call the close() Core Selenium method.


## Authors
* **Daniela Sánchez** - *Java Developer* - https://github.com/mdsdanny - 
See also the list of [contributors](https://github.com/mdsdanny/seleniumOO/graphs/contributors) who participated in this project.

Looking forward any feedback on this project. Thanks for reading !

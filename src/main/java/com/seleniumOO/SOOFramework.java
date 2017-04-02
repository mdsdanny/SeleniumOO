package com.seleniumOO;

import com.seleniumOO.util.SOOConfig;
import com.seleniumOO.util.SOOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * This class is the main loaded of the Selenium OO library.
 * Takes care of the initialization of the browser and a possible .properties file.
 * Manages a WebDriver instance and a SOOConfig instance.
 *
 * Created by Daniela Sánchez on 10/02/2017
 */
public class SOOFramework{

    protected WebDriver webDriver = null;
    protected SOOConfig config = null;

    static final Logger logger = Logger.getLogger(SOOFramework.class);

    /**
     * Constructor for an initialization with no property file.
     * @param browser you specified the browser type. Are 'firefox' or 'chrome' or 'ie'
     * @param driverPath you specified the path of the driver to use. For example '/home/daniela/Desktop/geckodriver'
     * @param gridHubAddress you specified the grid hub address. You can set null if you don't have one.
     * @throws SOOException
     */
    public SOOFramework(String browser, String driverPath, String gridHubAddress) throws SOOException {
        try {
            init(browser, driverPath, gridHubAddress);
        }catch (Throwable e){
            throw new SOOException(e);
        }
    }

    /**
     * Constructor for an initialization with a property file.
     * @param resourceName the name of the properties file you wish to include on your tests. As example, config.properties under the test/resources folder.
     * @param browserTypeKey Is the key of the properties file were you specified the browser type. Are browserType=firefox or browserType=chrome or browserType=ie
     * @param driverKey Is the key of the properties file were you specified the path of the driver to use. For example driverLocation = /home/daniela/Desktop/geckodriver
     * @param gridHubAddresskey Is the key of the properties file were you specified the grid hub address. You can set null if you don't have one.
     * @throws SOOException
     */
    public SOOFramework(String resourceName, String browserTypeKey, String driverKey, String gridHubAddresskey) throws SOOException {
        browserTypeKey = browserTypeKey != null ? browserTypeKey : "browserType";
        driverKey = driverKey != null ? driverKey : "driverLocation";
        gridHubAddresskey = gridHubAddresskey != null ? gridHubAddresskey : "gridHubAddress";

        try {
           if(resourceName == null) {
                if(getConfig().getProperty(driverKey) != null){
                    System.setProperty("webdriver.gecko.driver", getConfig().getProperty(driverKey));
                }
                webDriver = new FirefoxDriver();
                webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                webDriver.manage().window().maximize();
                return;
            }
            config = new SOOConfig(resourceName);

            init(config.getProperty(browserTypeKey), getConfig().getProperty(driverKey), config.getProperty(gridHubAddresskey));

        }catch (Throwable e){
            throw new SOOException(e);
        }
   }

    void init(String browser, String driverPath, String gridHubAddress) throws Exception {
        if(browser == null){
            throw new Exception("No browser type defined. Examples: 'firefox', 'chrome' or 'ie'");
        }
        browser = browser.toLowerCase();
        Boolean gridExecution = gridHubAddress != null ? true : false;
        switch (browser) {
            case "firefox":
                if (gridExecution) {
                    DesiredCapabilities capability = DesiredCapabilities.firefox();
                    webDriver = new RemoteWebDriver(new URL(gridHubAddress), capability);
                } else {
                    if(driverPath != null){
                        System.setProperty("webdriver.gecko.driver", driverPath);
                    }
                    webDriver = new FirefoxDriver();
                }
                break;
            case "ie":
                if (gridExecution) {
                    DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
                    webDriver = new RemoteWebDriver(new URL(gridHubAddress), capability);
                } else {
                    if(driverPath != null){
                        System.setProperty("webdriver.ie.driver", driverPath);
                    }
                    webDriver = new InternetExplorerDriver();
                }
                break;
            case "chrome":
                if (gridExecution) {
                    DesiredCapabilities capability = DesiredCapabilities.chrome();
                    webDriver = new RemoteWebDriver(new URL(gridHubAddress), capability);
                } else {
                    if(driverPath != null){
                        System.setProperty("webdriver.chrome.driver", driverPath);
                    }
                    webDriver = new ChromeDriver();
                }
                break;
            default:
                if(driverPath != null){
                    System.setProperty("webdriver.gecko.driver", driverPath);
                }
                webDriver = new FirefoxDriver();
        }
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    public WebDriver getWebDriver(){
        return webDriver;
    }
    public SOOConfig getConfig(){
        return config;
    }


}

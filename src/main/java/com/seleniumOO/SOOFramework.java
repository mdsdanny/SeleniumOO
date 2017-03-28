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
 * This class is the main loaded of the Selenium OO plugin.
 * Takes care of the initialization of the browser and the .properties file.
 *
 * Created by Daniela Sánchez on 10/02/2017
 */
public class SOOFramework{

    protected WebDriver webDriver = null;
    protected SOOConfig config = null;

    static final Logger logger = Logger.getLogger(SOOFramework.class);

    public SOOFramework(String browser, String driverPath, String gridHubAddress) throws SOOException {
        try {
            init(browser, driverPath, gridHubAddress);
        }catch (Throwable e){
            throw new SOOException(e);
        }
    }

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
                        System.setProperty("webdriver.gecko.driver", getConfig().getProperty(driverPath));
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

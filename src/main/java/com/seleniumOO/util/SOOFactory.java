package com.seleniumOO.util;

import com.seleniumOO.SOOFramework;
import com.seleniumOO.ui.SOOPage;
import com.seleniumOO.ui.SOOUIElement;

/**
 * SOOFactory allows to, easily initialize the SOO library and also create SOOUIElement's.
 *
 * Created by daniela on 13/03/17.
 */
public class SOOFactory {

    /**
     * Allows to initialize a SOOFramework object. With a given .property file name, and some keys were you set your browser type, driver path and grid hub address.
     * @param resourceName the name of the properties file you wish to include on your tests. As example, config.properties under the test/resources folder.
     * @param browserTypeKey Is the key of the properties file were you specified the browser type. Are browserType=firefox or browserType=chrome or browserType=ie
     * @param driverKey Is the key of the properties file were you specified the path of the driver to use. For example driverLocation = /home/daniela/Desktop/geckodriver
     * @param gridHubAddresskey Is the key of the properties file were you specified the grid hub address. You can set null if you don't have one.
     * @return SOOFramework the new initialization object of the SOO library core.
     * @throws SOOException
     */
    public static SOOFramework initializeFW(String resourceName, String browserTypeKey, String driverKey, String gridHubAddresskey) throws SOOException {
        return new SOOFramework(resourceName, browserTypeKey, driverKey, gridHubAddresskey);
    }

    /**
     * Allows to initialize a SOOFramework object. With a given browser type, a driverPath and a gridHubAddress.
     * @param browser The browser you wish to use for your tests. Could be 'firefox' or 'chrome' or 'ie'
     * @param driverPath The driver path were your driver file is. As example '/home/daniela/Desktop/geckodriver'
     * @param gridHubAddress The grid hub address, if you want to use this feature. You can set null if you don't have one.
     * @return SOOFramework the new initialization object of the SOO library core.
     * @throws SOOException
     */
    public static SOOFramework initializeFW(String browser, String driverPath, String gridHubAddress) throws SOOException {
        return new SOOFramework(browser, driverPath, gridHubAddress);
    }

    /**
     * Allows to create a SOOUIElement object. Of a given page, of a element type and a given value.
     * @param page the SOOPage instance were the element belongs to.
     * @param elementType the SSOElementType type. Could be 'class', 'id', 'name'...
     * @param value the value of that type.
     * @return SOOUIElement object that represents an HTML object of a given page.
     */
    public static SOOUIElement createElement(SOOPage page, SSOElementType elementType, String value){
        return new SOOUIElement(page, elementType, value);
    }
}

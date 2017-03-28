package com.seleniumOO.util;

import com.seleniumOO.SOOFramework;
import com.seleniumOO.ui.SOOPage;
import com.seleniumOO.ui.SOOUIElement;

/**
 * Created by daniela on 13/03/17.
 */
public class SOOFactory {

    public static SOOFramework initializeFW(String resourceName, String browserTypeKey, String driverKey, String gridHubAddresskey) throws SOOException {
        return new SOOFramework(resourceName, browserTypeKey, driverKey, gridHubAddresskey);
    }
    public static SOOFramework initializeFW(String browser, String driverPath, String gridHubAddress) throws SOOException {
        return new SOOFramework(browser, driverPath, gridHubAddress);
    }

    public static SOOUIElement createElement(SOOPage page, SSOElementType elementType, String value){
        return new SOOUIElement(page, elementType, value);
    }
}

package com.github.mdsdanny.seleniumOO.util;

import com.github.mdsdanny.seleniumOO.SOOFramework;
import com.github.mdsdanny.seleniumOO.ui.SOOPage;
import com.github.mdsdanny.seleniumOO.ui.SOOUIElement;

/**
 * Created by daniela on 13/03/17.
 */
public class SOOFactory {

    public static SOOFramework initializeFW(String resourceName, String browserTypeKey, String driverKey) throws SOOException {
        return new SOOFramework(resourceName, browserTypeKey, driverKey);
    }
    public static SOOFramework initializeFW(String browser, String driverPath) throws SOOException {
        return new SOOFramework(browser, driverPath);
    }

    public static SOOUIElement createElement(SOOPage page, SSOElementType elementType, String value){
        return new SOOUIElement(page, elementType, value);
    }
}

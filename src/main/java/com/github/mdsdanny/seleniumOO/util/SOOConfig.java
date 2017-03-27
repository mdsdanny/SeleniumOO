package com.github.mdsdanny.seleniumOO.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This SOOConfig class represents a .properties file with some configuration for each project.
 * You should extend this class for your own .properties configuration file.
 *
 * Created by Daniela Sánchez on 10/02/2017
 */
public class SOOConfig {

    private Properties config = new Properties();
    static final Logger logger = Logger.getLogger(SOOConfig.class);

    public SOOConfig(String resourceName) throws SOOException {
        try {
            loadConfig(resourceName);
        } catch (Exception e) {
            throw new SOOException(e);
        }
    }
    
    private void loadConfig(String file) throws IOException {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
            this.config.load(is);
            logger.info("Configuration loaded from  " + file);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Unable to find default configuration file in path: " + file);
            throw ex;
        }
    }
    
    //@ Getters & Setters
    public String getProperty(String property) {
        return this.config.getProperty(property);
    }


}

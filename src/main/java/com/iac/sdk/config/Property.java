package com.iac.sdk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Class responsible to load and manage application wide properties
 */
public class Property {

    private static Properties prop;
    private static String propertyFileName = "application.properties";
    private static final String EVENT_TRACKER_API_URL = "event.tracker.api.url";
    private static final String RETRY_NUMBER = "retry.attempt.number";
    private static final String RETRY_INTERVAL = "retry.time.interval";

    private static Logger LOGGER = LoggerFactory.getLogger(Property.class);

    static {
        try {
            prop = new Properties();
            InputStream in = ClassLoader.getSystemResourceAsStream(propertyFileName);

            if(Objects.nonNull(in)) {
                prop.load(in);
            }
            else {
                String errorMsg = "property file " + propertyFileName + "not find in classpath";
                LOGGER.error(errorMsg);
                throw new FileNotFoundException(errorMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getApiUrl() {
        return prop.getProperty(EVENT_TRACKER_API_URL);
    }

    public static int getRetryNumber() {
        return Integer.parseInt(prop.getProperty(RETRY_NUMBER));
    }

    public static long getRetryInteral() { return Long.parseLong(prop.getProperty(RETRY_INTERVAL)); }
}

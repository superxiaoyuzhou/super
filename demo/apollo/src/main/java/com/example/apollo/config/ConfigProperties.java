package com.example.apollo.config;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author piter
 */
public class ConfigProperties {

    private static Properties prop = null;

    static{
        prop=new Properties();
        String profiles = System.getProperty("spring.profiles.active","dev");
        String path = "config/config-insurance-" + profiles + ".properties";
        try {
            prop.load(new InputStreamReader(ConfigProperties.class
                    .getClassLoader().getResourceAsStream(path), "UTF-8"));
        } catch (Exception e) {
        }
    }

    public static String get(String key){
        return prop.getProperty(key);
    }

}

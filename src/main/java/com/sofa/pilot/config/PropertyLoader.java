package com.sofa.pilot.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component()
public class PropertyLoader {

	private static final String SLASH = "/";

	private static final String EXTERNAL_CONFIG = "/tmp/local/config";
	private static final Logger logger = Logger.getLogger(PropertyLoader.class.getName());
	private static final String PROPERTIES = ".properties";

	private final Map<Object, Object> propertyMap;

	public PropertyLoader() {
		this.propertyMap = createProperties("config");
	}

	public String getValueOf(String key) {
		Object value = propertyMap.get(key);
		return value == null ? "" : value.toString();
	}

	public static Properties createProperties(String fileName) {
		String extFileName = fileName + PROPERTIES;
		Properties properties = null;
		try {
			File file = new File(EXTERNAL_CONFIG, extFileName);
			try (InputStream fis = new FileInputStream(file)) {
				properties = new Properties();
				properties.load(fis);
			}
		} catch (IOException e) {
			logger.log(Level.INFO, "External properties not found trying to load internal properties " + fileName);
			String inFileName = SLASH + fileName + PROPERTIES;
			try (InputStream in = PropertyLoader.class.getResourceAsStream(inFileName)) {
				properties = new Properties();
				properties.load(in);
			} catch (IOException ex) {
				logger.log(Level.SEVERE, "Error getting properties file", e);
			}
		}

		return properties;
	}
}

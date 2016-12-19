package org.icube.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.icube.helper.UtilHelper;

public class UtilHelper {
	
	public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss"; // 2016-01-01 00:00:00
	public static final String dateFormat = "yyyy-MM-dd"; // 2016-01-01;
	
	public static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Timestamp convertJavaDateToSqlTimestamp(java.util.Date date) {
		return new java.sql.Timestamp(date.getTime());
	}
	
	public static String getConfigProperty(String propertyName) {
		String propertyValue = "";

		Properties prop = new Properties();
		String propFileName = "config.properties";
		InputStream inputStream = UtilHelper.class.getClassLoader().getResourceAsStream(propFileName);
		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				org.apache.log4j.Logger.getLogger(UtilHelper.class).error("property file '" + propFileName + "' not found in classpath");
			}
			propertyValue = prop.getProperty(propertyName);
		} else {
			org.apache.log4j.Logger.getLogger(UtilHelper.class).error("property file '" + propFileName + "' not found in classpath");
		}

		try {
			inputStream.close();
		} catch (IOException e) {
			org.apache.log4j.Logger.getLogger(UtilHelper.class).error("Error while closing the inputStream");
		}
		return propertyValue;
	}

}

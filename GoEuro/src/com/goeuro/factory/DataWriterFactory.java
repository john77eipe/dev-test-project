package com.goeuro.factory;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.goeuro.constants.ApplicationConstants;
import com.goeuro.exceptions.GoEuroSystemException;
import com.goeuro.inf.DataWriter;

public class DataWriterFactory {

	private static final String CLASSNAME = DataWriterFactory.class.getName();
	private static final Logger LOGGER = Logger.getLogger(CLASSNAME);
	
	public static DataWriter createDataWriter(String clazz) throws GoEuroSystemException{
		final String METHOD_NAME = "createDataWriter";
		
		DataWriter writer = null;
		try {
			writer = (DataWriter) Class.forName(clazz).newInstance();
		} catch (InstantiationException e) {
			String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-005E"), clazz, METHOD_NAME);
			LOGGER.log(Level.SEVERE, errorMessage);
			throw new GoEuroSystemException(errorMessage, e);
		} catch (IllegalAccessException e) {
			String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-005E"), clazz, METHOD_NAME);
			LOGGER.log(Level.SEVERE, errorMessage);
			throw new GoEuroSystemException(errorMessage, e);
		} catch (ClassNotFoundException e) {
			String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-005E"), clazz, METHOD_NAME);
			LOGGER.log(Level.SEVERE, errorMessage);
			throw new GoEuroSystemException(errorMessage, e);
		}
		return writer;
	}
}

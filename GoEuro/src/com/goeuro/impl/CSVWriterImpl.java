package com.goeuro.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.goeuro.beans.BaseDetailsBean;
import com.goeuro.beans.RuntimeContext;
import com.goeuro.constants.ApplicationConstants;
import com.goeuro.exceptions.GoEuroSystemException;
import com.goeuro.inf.DataWriter;

public class CSVWriterImpl implements DataWriter {

	private static final String CLASS_NAME = CSVWriterImpl.class.getName();
	private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
	private static final CharSequence COMMA_DELIMITER = ", ";
	private static final CharSequence NEW_LINE_SEPARATOR = "\n";
	
	@Override
	public <T extends BaseDetailsBean>  void writeData(List<T> beans, RuntimeContext context, Class<T> klazz) throws GoEuroSystemException {
		final String METHOD_NAME = "writeData";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(context.getFileLoc());
			
			//Write object list to the CSV file
			for (T bean : beans) {
				Field[] fields = klazz.getDeclaredFields();
		        int noOfFeilds = fields.length;
		        for (int i=0; i<noOfFeilds; i++) {
		        	fields[i].setAccessible(true);
		        	fileWriter.append((String)fields[i].get(bean));
					if(i<noOfFeilds-1)
						fileWriter.append(COMMA_DELIMITER);
		        }
		        fileWriter.append(NEW_LINE_SEPARATOR);
			}
			
			LOGGER.log(Level.INFO, MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-002D"), METHOD_NAME));
			
		} catch (Exception e) {
			String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-003E"), e, METHOD_NAME);
			LOGGER.log(Level.SEVERE, errorMessage);
			throw new GoEuroSystemException(errorMessage, e);
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-004E"), e, METHOD_NAME);
				LOGGER.log(Level.SEVERE, errorMessage);
				throw new GoEuroSystemException(errorMessage, e);
			}
			
		}
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
	}
}

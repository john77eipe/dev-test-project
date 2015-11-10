package com.goeuro.util;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import com.goeuro.beans.RuntimeContext;
import com.goeuro.constants.ApplicationConstants;
import com.goeuro.constants.ApplicationConstants.ConfigParameter;
import com.goeuro.exceptions.GoEuroBussinessException;
import com.goeuro.exceptions.GoEuroSystemException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ApplicationUtil {
	
	final static String DEFAULT_LOCATION = System.getProperty("user.home");
	public final static String DEFAULT_TYPE = "CSV";
	public final static String REST_URL = "http://api.goeuro.com/api/v2/position/suggest/en";
	private static final String CLASS_NAME = ApplicationUtil.class.getName();
	private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
	
	/**
	 * Method to build the RuntimeContext object that manages the context of the application
	 * @return
	 * @throws GoEuroSystemException
	 * @throws GoEuroBussinessException
	 */
	public static RuntimeContext buildRuntimeContext() throws GoEuroSystemException, GoEuroBussinessException {
		final String METHOD_NAME = "buildRuntimeContext";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		RuntimeContext context = new RuntimeContext();

		
		String fileType = LauncherUtil.getArgValue(ConfigParameter.FILE_TYPE);
		if (fileType == null || fileType.trim().length()==0) {
			context.setFileType(DEFAULT_TYPE);
		} else {
			context.setFileType(fileType);
		}
		
		String city = LauncherUtil.getArgValue(ConfigParameter.CITY_NAME);
		if (city == null || city.trim().length() == 0) {
			if (LauncherUtil.city == null) {
				String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-005E"), METHOD_NAME);
				LOGGER.log(Level.SEVERE, errorMessage);
				throw new GoEuroBussinessException(errorMessage);
			} else {
				context.setCity(LauncherUtil.city);
			}
		} else {
			context.setCity(city);
		}
		
		String fileLocation = LauncherUtil.getArgValue(ConfigParameter.FILE_LOCATION);
		if (fileLocation == null) {
			context.setFileLoc(DEFAULT_LOCATION + "/"+context.getCity() + "." + context.getFileType().toLowerCase());
		} else {
			context.setFileLoc(fileLocation + "/" + context.getCity() + "." + context.getFileType().toLowerCase());
		}
		
		String writerClazz = LauncherUtil.getArgValue(ConfigParameter.DATA_WRITER_CLASS);
		if(writerClazz==null){
			context.setWriterClass("com.goeuro.impl.CSVWriterImpl");
		} else {
			context.setWriterClass(writerClazz);
		}
		
		LOGGER.log(Level.INFO, MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-005D"), context, METHOD_NAME));
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return context;
	}

	/**
	 * Method that makes a REST call to the service URL and returns JSON response
	 * @param ctx
	 * @return
	 */
	public static String readDataFromService(RuntimeContext ctx) {
		final String METHOD_NAME = "readDataFromService";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		Client client = Client.create();
		WebResource webResource = client.resource(REST_URL);
		ClientResponse response = webResource.path("/" + ctx.getCity()).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		LOGGER.log(Level.INFO,
				MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-003D"), response.getStatus(), METHOD_NAME));
		String jsonStr = response.getEntity(String.class);
		LOGGER.log(Level.INFO,
				MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-004D"), jsonStr, METHOD_NAME));
		
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return jsonStr;
	}
}

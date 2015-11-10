package com.goeuro.util;

import java.util.List;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.goeuro.constants.ApplicationConstants;
import com.goeuro.constants.ApplicationConstants.ConfigParameter;
import com.goeuro.exceptions.GoEuroBussinessException;
import com.goeuro.exceptions.GoEuroSystemException;

public class LauncherUtil {
	
	private static final String CLASS_NAME = LauncherUtil.class.getName();
	private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
	public static CommandLine cmd = null;
	public static String city = null;
	
	public static void parseCommandLineArgs(String[] args) throws GoEuroSystemException, GoEuroBussinessException {
		final String METHOD_NAME = "parseCommandLineArgs";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		LOGGER.log(Level.INFO, MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-001D"), Arrays.asList(args), METHOD_NAME));

		if (args != null) {
			Options cmdLineOptions = new Options();

			ConfigParameter[] arrayOfConfigParameters = ConfigParameter.values();
			for (ConfigParameter configParameter : arrayOfConfigParameters) {
				cmdLineOptions.addOption(configParameter.shortOpt, configParameter.name(), configParameter.hasArg, configParameter.desc);
			}

			CommandLineParser parser = new DefaultParser();

			try {
				cmd = parser.parse(cmdLineOptions, args);
				List<String> leftOverArgs = cmd.getArgList();
				if(leftOverArgs!=null && leftOverArgs.size()>0 && leftOverArgs.get(0)!=null){
					city = leftOverArgs.get(0);
				}
			} catch (ParseException e) {
				String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-001E"), e, METHOD_NAME);
				LOGGER.log(Level.SEVERE, errorMessage);
				throw new GoEuroSystemException(errorMessage, e);
			}
		} else {
			String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-002E"), METHOD_NAME);
			LOGGER.log(Level.SEVERE, errorMessage);
			throw new GoEuroBussinessException(errorMessage);
		}

		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
	}
	
	public static String getArgValue(ConfigParameter configParameter) throws GoEuroSystemException, GoEuroBussinessException {
		final String METHOD_NAME = "getArgValue";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		String value = null;
		if (configParameter != null && configParameter.shortOpt != null) {
			try {
				value = cmd.getOptionValue(configParameter.shortOpt);
				if (value == null || value.trim().length() == 0) {
					if (configParameter.name() != null) {
						value = cmd.getOptionValue(configParameter.name());
					} else {
						String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-002E"), METHOD_NAME);
						LOGGER.log(Level.SEVERE, errorMessage);
						throw new GoEuroBussinessException(errorMessage);
					}
				}
			} catch (Exception e) {
				String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-001E"), e, METHOD_NAME);
				LOGGER.log(Level.SEVERE, errorMessage);
				throw new GoEuroSystemException(errorMessage, e);
			}
		} else {
			String errorMessage = MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-002E"), METHOD_NAME);
			LOGGER.log(Level.SEVERE, errorMessage);
			throw new GoEuroBussinessException(errorMessage);
		}

		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return value;
	}
}

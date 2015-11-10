package com.goeuro.constants;

import java.util.ResourceBundle;

public class ApplicationConstants {
	
	private static final String BUNDLE_NAME = "com.goeuro.resources.loggerCodes";
	
	public static final ResourceBundle APP_CONSTANTS_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	
	public enum ConfigParameter {
		
		CITY_NAME("C", true, "city", "Name of the city; This option is mandatory. "),
		FILE_LOCATION("L", true, "fileLoc", "Location and name of the CSV file; Default: ~/<city name>.csv"),
		FILE_TYPE("T", true, "fileType", "Type of the file; Default: CSV"),
		DATA_WRITER_CLASS("W", true, "writer", "Writer implementation class to be used, Default: CSVWriterImpl.class");
		
		
		//Short single-character name of the option.
		public String shortOpt;
		//Flag signally if an argument is required after this option
		public boolean hasArg;
		//Bean attribute mapping name
		public String attributeName;
		//Self-documenting description
		public String desc;
		
		ConfigParameter(String attributeId, boolean attributeReq,String attributeName, String attributeDesc){
			this.shortOpt = attributeId;
			this.hasArg = attributeReq;
			this.desc = attributeDesc;
			this.attributeName=attributeName;
		}
	}

	
}

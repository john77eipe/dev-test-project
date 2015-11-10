package com.goeuro.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.goeuro.beans.CityDetailsBean;
import com.goeuro.constants.ApplicationConstants;
import com.goeuro.inf.DataFilter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DataFilterImpl implements DataFilter {
	
	private static final String CLASS_NAME = DataFilterImpl.class.getName();
	private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
	
	public List<CityDetailsBean> getFilteredDataFromJSON(String jsonStr){
		final String METHOD_NAME = "getFilteredDataFromJSON";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		JsonArray data = new Gson().fromJson(jsonStr, JsonArray.class);
		Iterator<JsonElement> iterator = data.iterator();
		List<CityDetailsBean> beans = new ArrayList<CityDetailsBean>();
		while(iterator.hasNext()){
			CityDetailsBean bean = new CityDetailsBean();
			JsonObject jsonObject = iterator.next().getAsJsonObject();
			bean.set_id(jsonObject.get("_id").getAsString());
			bean.setName(jsonObject.get("name").getAsString());
			bean.setType(jsonObject.get("type").getAsString());
			bean.setLatitude(jsonObject.get("geo_position").getAsJsonObject().get("latitude").getAsString());
			bean.setLongitude(jsonObject.get("geo_position").getAsJsonObject().get("longitude").getAsString());
			beans.add(bean);
		}
		LOGGER.log(Level.INFO, MessageFormat.format(ApplicationConstants.APP_CONSTANTS_BUNDLE.getString("GOEURO-006D"), beans, METHOD_NAME));
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return beans;
	}
}

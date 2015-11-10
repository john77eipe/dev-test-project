package com.goeuro.inf;

import java.util.List;

import com.goeuro.beans.BaseDetailsBean;


public interface DataFilter {
	/**
	 * A generic method to filter json reponse
	 * @param jsonStr
	 * @return
	 */
	public <T extends BaseDetailsBean> List<T> getFilteredDataFromJSON(String jsonStr);
}

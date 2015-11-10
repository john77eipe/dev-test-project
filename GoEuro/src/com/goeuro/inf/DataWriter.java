package com.goeuro.inf;

import java.util.List;

import com.goeuro.beans.BaseDetailsBean;
import com.goeuro.beans.RuntimeContext;
import com.goeuro.exceptions.GoEuroSystemException;

public interface DataWriter {
	/**
	 * A generic write method that returns writes a list of beans of type klazz
	 * @param beans
	 * @param context
	 * @param clazz
	 * @throws GoEuroSystemException
	 */
	public <T extends BaseDetailsBean> void writeData(List<T> beans, RuntimeContext context, Class<T> clazz) throws GoEuroSystemException;
}

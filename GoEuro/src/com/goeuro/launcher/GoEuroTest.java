package com.goeuro.launcher;

import java.util.List;

import com.goeuro.beans.CityDetailsBean;
import com.goeuro.beans.RuntimeContext;
import com.goeuro.exceptions.GoEuroBussinessException;
import com.goeuro.exceptions.GoEuroSystemException;
import com.goeuro.factory.DataWriterFactory;
import com.goeuro.impl.DataFilterImpl;
import com.goeuro.inf.DataFilter;
import com.goeuro.inf.DataWriter;
import com.goeuro.util.ApplicationUtil;
import com.goeuro.util.LauncherUtil;

public class GoEuroTest {

	public static void main(String[] args) throws GoEuroSystemException, GoEuroBussinessException {
		try {
			LauncherUtil.parseCommandLineArgs(args);

			RuntimeContext context = ApplicationUtil.buildRuntimeContext();
			String jsonStr = ApplicationUtil.readDataFromService(context);

			DataFilter dataFilter = new DataFilterImpl();
			List<CityDetailsBean> beans = dataFilter.getFilteredDataFromJSON(jsonStr);
		
			DataWriter writer = DataWriterFactory.createDataWriter(context.getWriterClass());
			writer.writeData(beans, context, CityDetailsBean.class);
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

}

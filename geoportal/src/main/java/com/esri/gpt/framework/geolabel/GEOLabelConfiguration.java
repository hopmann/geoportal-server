package com.esri.gpt.framework.geolabel;

import com.esri.gpt.framework.context.Configuration;
import com.esri.gpt.framework.util.Val;

public class GEOLabelConfiguration extends Configuration {

	private String _serviceEndpoint = "";

	public String getServiceEndpoint() {
		return _serviceEndpoint;
	}

	public void setServiceEndpoint(String serviceEndpoint) {
		this._serviceEndpoint = Val.chkStr(serviceEndpoint);
	}
}

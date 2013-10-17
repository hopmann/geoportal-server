package com.esri.gpt.framework.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import com.esri.gpt.catalog.search.ASearchEngine;
import com.esri.gpt.catalog.search.SearchCriteria;
import com.esri.gpt.catalog.search.SearchEngineFactory;
import com.esri.gpt.catalog.search.SearchException;
import com.esri.gpt.catalog.search.SearchResult;
import com.esri.gpt.catalog.search.SearchResultRecord;
import com.esri.gpt.framework.context.ApplicationContext;
import com.esri.gpt.framework.util.Val;

@ManagedBean
public class GeoLabelHelper extends BaseActionListener {

	public String getFullMetadataUrl(SearchResultRecord record) {
		if (record == null) {
			return null;
		}
		ASearchEngine engine;
		try {
			engine = SearchEngineFactory.createSearchEngine(new SearchCriteria(), new SearchResult(), this
					.extractRequestContext(), record.getExternalId(), this.getContextBroker().extractMessageBroker());
			return Val.chkStr(engine.getMetadataUrl(record.getUuid()));
		} catch (SearchException e) {
			MessageBroker broker = this.extractMessageBroker();
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary(e.getMessage());
			message.setDetail(e.getMessage());
			broker.addMessage(message);
			broker.addErrorMessage(new SearchException(""));
			return null;
		}

	}

	public String getServiceEndpoint() {
		return ApplicationContext.getInstance().getConfiguration().getGEOLabelConfiguration().getServiceEndpoint();
	}
}

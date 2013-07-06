package eu.fiveminutes.cipele46.api;

import java.util.List;

import eu.fiveminutes.cipele46.model.District;

public interface DistrictWithCitiesListener {
	
	void onSuccess(List<District> districts);
	
	void onFailure(Throwable t);

}

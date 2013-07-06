package eu.fiveminutes.cipele46.api;

import java.util.List;

import eu.fiveminutes.cipele46.model.Ad;

public interface AdsListener {

	void onSuccess(List<Ad> ad);
	
	void onFailure(Throwable t);
}

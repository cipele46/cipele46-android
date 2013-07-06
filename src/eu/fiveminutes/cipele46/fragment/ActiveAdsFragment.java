package eu.fiveminutes.cipele46.fragment;

import eu.fiveminutes.cipele46.api.CipeleAPI.UserAdSection;

public class ActiveAdsFragment extends UserAdsFragment{

	@Override
	public UserAdSection getSection() {
		return UserAdSection.ACTIVE_ADS;
	}

}

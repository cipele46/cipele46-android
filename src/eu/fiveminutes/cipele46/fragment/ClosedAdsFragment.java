package eu.fiveminutes.cipele46.fragment;

import eu.fiveminutes.cipele46.api.CipeleAPI.UserAdSection;

public class ClosedAdsFragment extends UserAdsFragment{

	@Override
	public UserAdSection getSection() {
		return UserAdSection.CLOSED_ADS;
	}

}

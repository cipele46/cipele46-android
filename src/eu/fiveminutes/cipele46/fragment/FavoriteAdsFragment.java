package eu.fiveminutes.cipele46.fragment;

import eu.fiveminutes.cipele46.api.CipeleAPI.UserAdSection;

public class FavoriteAdsFragment extends UserAdsFragment {

	@Override
	public UserAdSection getSection() {
		// TODO Auto-generated method stub
		return UserAdSection.FAVORITE_ADS;
	}

}

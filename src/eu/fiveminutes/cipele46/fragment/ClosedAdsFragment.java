package eu.fiveminutes.cipele46.fragment;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.api.CipeleAPI.UserAdSection;

public class ClosedAdsFragment extends UserAdsFragment{

	@Override
	public UserAdSection getSection() {
		return UserAdSection.CLOSED_ADS;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (!isAdMine()){
			menu.findItem(R.id.details_favorites).setVisible(false);
			menu.findItem(R.id.details_close).setVisible(false);
			menu.findItem(R.id.details_delete).setVisible(false);
			menu.findItem(R.id.details_edit).setVisible(false);
		}
	}
	

}

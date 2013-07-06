package eu.fiveminutes.cipele46.activity;

import android.os.Bundle;
import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.fragment.ClosedAdsFragment;

public class ClosedAdsActivity extends MenuActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ClosedAdsFragment caf = new ClosedAdsFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.main_container, caf).commit();
	}

}

package eu.fiveminutes.cipele46.activity;

import android.os.Bundle;
import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.fragment.ActiveAdsFragment;

public class ActiveAdsActivity extends MenuActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActiveAdsFragment adf = new ActiveAdsFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.main_container, adf).commit();
	}

}

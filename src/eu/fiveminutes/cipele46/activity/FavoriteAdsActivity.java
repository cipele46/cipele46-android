package eu.fiveminutes.cipele46.activity;

import android.os.Bundle;
import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.fragment.FavoriteAdsFragment;

public class FavoriteAdsActivity extends MenuActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FavoriteAdsFragment faf = new FavoriteAdsFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.main_container, faf).commit();
	}

}

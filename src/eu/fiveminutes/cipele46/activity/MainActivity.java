package eu.fiveminutes.cipele46.activity;

import android.os.Bundle;
import eu.fiveminutes.cipele46.fragment.MainFragment;

public class MainActivity extends MenuActivity {

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainFragment mf = new MainFragment();
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, mf).commit();
	}

}

package eu.fiveminutes.cipele46.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.fragment.AdDetailsFragment;

public class AdDetailsActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		AdDetailsFragment adf = new AdDetailsFragment();
		adf.setArguments(getIntent().getExtras());
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, adf).commit();

		ActionBar ab = getSupportActionBar();
		if (ab != null) {
			ab.setDisplayHomeAsUpEnabled(true);
			ab.setTitle(R.string.ad_details);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

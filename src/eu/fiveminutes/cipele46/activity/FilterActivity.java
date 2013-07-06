package eu.fiveminutes.cipele46.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.fragment.FilterFragment;

public class FilterActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		FilterFragment ff = new FilterFragment();
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, ff).commit();

		ActionBar ab = getSupportActionBar();
		if (ab != null) {
			ab.setTitle(R.string.filter_ads);
			ab.setDisplayHomeAsUpEnabled(true);
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

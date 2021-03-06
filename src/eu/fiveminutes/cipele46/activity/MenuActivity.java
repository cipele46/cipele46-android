package eu.fiveminutes.cipele46.activity;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.fragment.MenuFragment.OnMenuItemSelectedListener;

public abstract class MenuActivity extends SherlockFragmentActivity implements OnMenuItemSelectedListener {

	public static final int MENU_ADS = 0;
	public static final int MENU_ACTIVE_ADS = 1;
	public static final int MENU_FAVORITE_ADS = 2;
	public static final int MENU_CLOSED_ADS = 3;
	public static final int MENU_SETTINGS = 4;

	DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.home_drawer);


		ActionBar supportActionBar = getSupportActionBar();
		if (supportActionBar == null) {
			Log.d("FM_TEMPLATE", "No Action Bar Present");
		} else {
			supportActionBar.setHomeButtonEnabled(true);
			supportActionBar.setDisplayHomeAsUpEnabled(true);
			supportActionBar.setIcon(R.drawable.action_bar_icon);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (!mDrawerLayout.isDrawerOpen(Gravity.START)) {
				mDrawerLayout.openDrawer(Gravity.START);
			} else {
				mDrawerLayout.closeDrawer(Gravity.START);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onMenuItemSelected(int itemId) {
		mDrawerLayout.closeDrawer(Gravity.START);
	}
}

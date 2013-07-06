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

public abstract class MenuActivity extends SherlockFragmentActivity {

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

		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
		R.string.app_name, /* "open drawer" description */
		R.string.app_name /* "close drawer" description */
		) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				// getActionBar().setTitle(mTitle);
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				// getActionBar().setTitle(mDrawerTitle);
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		ActionBar supportActionBar = getSupportActionBar();
		if (supportActionBar == null) {
			Log.d("FM_TEMPLATE", "No Action Bar Present");
		} else {
			supportActionBar.setHomeButtonEnabled(true);
			supportActionBar.setDisplayHomeAsUpEnabled(true);
			// supportActionBar.setIcon(R.drawable.action_bar_icon);
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

}

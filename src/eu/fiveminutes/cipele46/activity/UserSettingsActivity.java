package eu.fiveminutes.cipele46.activity;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.fragment.UserSettingsFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class UserSettingsActivity extends SherlockFragmentActivity {
	
	public static final String SCREEN_ID_EXTRA = "screen_id_extra";
	
	public enum UserSettingsScreen {
		UNKNOWN(0),
		LOGIN(1),
		USER_DETAILS(2),
		EDIT_USER(3),
		CHANGE_PASSWORD(4),
		REGISTER(5),
		RESET_PASSWORD(6);
		
		private int mScreenId;
		
		UserSettingsScreen(int screenId) {
			this.mScreenId = screenId;
		}
		
		public int getScreenId() {
			return mScreenId;
		}
		
		public static UserSettingsScreen valueOf(int id) {
			if(id == 1) {
				return LOGIN;
			} else if(id == 2) {
				return USER_DETAILS;
			} else if(id == 3) {
				return EDIT_USER;
			} else if(id == 4) {
				return CHANGE_PASSWORD;
			} else if(id == 5) {
				return REGISTER;
			} else if(id == 6) {
				return RESET_PASSWORD;
			}
			
			return UNKNOWN;
		}
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		Intent intent = getIntent();
		if(intent.hasExtra(SCREEN_ID_EXTRA) == false) {
			throw new RuntimeException("Screen id must be provided");
		}
		
		UserSettingsScreen screen = UserSettingsScreen.valueOf(intent.getIntExtra(SCREEN_ID_EXTRA, 
				UserSettingsScreen.UNKNOWN.getScreenId()));
		if(screen == UserSettingsScreen.UNKNOWN) {
			throw new RuntimeException("Screen not defined");
		}
		
		UserSettingsFragment userSettingsFragment = new UserSettingsFragment();
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, userSettingsFragment).commit();
		ActionBar ab = getSupportActionBar();
		if (ab != null) {
			ab.setTitle(R.string.settings);
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
	
	public static Intent buildIntent(Context context, UserSettingsScreen screen) {
		Intent intent = new Intent(context, UserSettingsActivity.class);
		intent.putExtra(SCREEN_ID_EXTRA, screen.getScreenId());
		return intent;
	}
}

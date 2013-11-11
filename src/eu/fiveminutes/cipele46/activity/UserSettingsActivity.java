package eu.fiveminutes.cipele46.activity;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.fragment.ChangePasswordFragment;
import eu.fiveminutes.cipele46.fragment.EditUserFragment;
import eu.fiveminutes.cipele46.fragment.LoginFragment;
import eu.fiveminutes.cipele46.fragment.RegisterFragment;
import eu.fiveminutes.cipele46.fragment.ResetPasswordFragment;
import eu.fiveminutes.cipele46.fragment.UserDetailsFragment;
import eu.fiveminutes.cipele46.model.User;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class UserSettingsActivity extends SherlockFragmentActivity {
	
	public static final String SCREEN_ID_EXTRA = "screen_id_extra";
	
	public static final int REQUEST_LOGIN = 1;
	
	private UserSettingsScreen mCurrentScreen;
	
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
			throw new RuntimeException(getString(R.string.error_screen_id_not_provided));
		}
				
		mCurrentScreen = UserSettingsScreen.valueOf(intent.getIntExtra(SCREEN_ID_EXTRA, 
				UserSettingsScreen.UNKNOWN.getScreenId()));
		if(mCurrentScreen == UserSettingsScreen.UNKNOWN) {
			throw new RuntimeException(getString(R.string.error_screen_not_defined));
		}
		
		SherlockFragment fragment = getScreenFragment(mCurrentScreen); 
		int activityTitleResource = getActivityTitleResource(mCurrentScreen);
		
		if(fragment != null) {
			getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();	
		}
		
		ActionBar ab = getSupportActionBar();
		if (ab != null) {
			ab.setTitle(activityTitleResource);
			ab.setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if(mCurrentScreen == UserSettingsScreen.USER_DETAILS) {
			MenuInflater inflater = getSupportMenuInflater();
		    inflater.inflate(R.menu.user_details, menu);
		}
		
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		if(item != null && item.getItemId() == R.id.action_logout) {
			User.deactivateUser(this);
			//startActivity(UserSettingsActivity.buildIntent(this, UserSettingsScreen.LOGIN));
			finish();
		}else if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		
		return true;
	};

	private int getActivityTitleResource(UserSettingsScreen screen) {
		
		if(screen == UserSettingsScreen.LOGIN) {
			return R.string.title_activity_user_settings;
		} else if(screen == UserSettingsScreen.USER_DETAILS) {
			return R.string.title_activity_user_settings;
		} else if(screen == UserSettingsScreen.EDIT_USER) {
			return R.string.title_activity_edit_data;
		} else if(screen == UserSettingsScreen.CHANGE_PASSWORD) {
			return R.string.title_activity_change_password;
		} else if(screen == UserSettingsScreen.REGISTER) {
			return R.string.title_activity_registration;
		} else if(screen == UserSettingsScreen.RESET_PASSWORD) {
			return R.string.title_activity_password_forgoten;
		}
		
		return R.string.settings;
	}

	private SherlockFragment getScreenFragment(UserSettingsScreen screen) {
		
		if(screen == UserSettingsScreen.LOGIN) {
			return new LoginFragment();
		} else if(screen == UserSettingsScreen.USER_DETAILS) {
			User currentUser = User.getActiveUser(this);
			if(currentUser != null) {
				return new UserDetailsFragment();
			} else {
				return new LoginFragment();
			}
			
		} else if(screen == UserSettingsScreen.EDIT_USER) {
			return new EditUserFragment();
		} else if(screen == UserSettingsScreen.CHANGE_PASSWORD) {
			return new ChangePasswordFragment();
		} else if(screen == UserSettingsScreen.REGISTER) {
			return new RegisterFragment();
		} else if(screen == UserSettingsScreen.RESET_PASSWORD) {
			return new ResetPasswordFragment();
		}
		
		return null;
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
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return intent;
	}
}

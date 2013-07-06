package eu.fiveminutes.cipele46.activity;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.R.id;
import eu.fiveminutes.cipele46.R.layout;
import eu.fiveminutes.cipele46.R.menu;
import eu.fiveminutes.cipele46.R.string;
import eu.fiveminutes.cipele46.fragment.NewAdFragment;
import eu.fiveminutes.cipele46.fragment.UserSettingsFragment;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class UserSettingsActivity extends SherlockFragmentActivity {
	
	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
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
}

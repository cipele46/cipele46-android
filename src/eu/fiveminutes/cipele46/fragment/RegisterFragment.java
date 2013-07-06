package eu.fiveminutes.cipele46.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.activity.UserSettingsActivity;
import eu.fiveminutes.cipele46.activity.UserSettingsActivity.UserSettingsScreen;

public class RegisterFragment extends SherlockFragment{

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.register, container, false);
		return v;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		

		view.findViewById(R.id.btn_register).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					//Pošalji podatke na server;
					startActivity(UserSettingsActivity.buildIntent(getActivity(), UserSettingsScreen.USER_DETAILS));
				}
			});
	}
}

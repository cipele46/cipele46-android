package eu.fiveminutes.cipele46.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.activity.UserSettingsActivity;
import eu.fiveminutes.cipele46.activity.UserSettingsActivity.UserSettingsScreen;
import eu.fiveminutes.cipele46.api.CipeleAPI;
import eu.fiveminutes.cipele46.api.UserLoginListener;
import eu.fiveminutes.cipele46.model.User;

public class LoginFragment extends SherlockFragment {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mTxtPassword;
	private EditText mTxtEmail;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.login, container, false);
		return v;
	}
	
	

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mTxtEmail = (EditText) view.findViewById(R.id.txtEmail);
		mTxtPassword = (EditText) view.findViewById(R.id.txtPassword);

		mLoginFormView = view.findViewById(R.id.login_form);
		mLoginStatusView = view.findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) view.findViewById(R.id.login_status_message);

		view.findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				CipeleAPI.get().loginUser(mTxtEmail.getText().toString(), mTxtPassword.getText().toString(), new UserLoginListener() {

					@Override
					public void onSuccess(User user) {
						user.setBasicAuth(CipeleAPI.basicAuthHeaderValue(mTxtEmail.getText().toString(), mTxtPassword.getText().toString()));
						User.setUserAsActive(getActivity(), user);

						if (getActivity().getCallingActivity() != null) {
							// Called for result
							Activity activity = getActivity();
							activity.setResult(Activity.RESULT_OK);
							activity.finish();

						} else {
							startActivity(UserSettingsActivity.buildIntent(getActivity(), UserSettingsScreen.USER_DETAILS));
						}

					}

					@Override
					public void onFailure(Throwable t) {
						Toast.makeText(getActivity(), getText(R.string.error_login_failed), Toast.LENGTH_LONG).show();
					}
				});

			}
		});

		view.findViewById(R.id.btn_fb_login).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getActivity(), "Logiram se na fejs", Toast.LENGTH_LONG).show();
			}
		});

		view.findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(UserSettingsActivity.buildIntent(getActivity(), UserSettingsScreen.REGISTER));
			}
		});

		view.findViewById(R.id.btn_forgot_password).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(UserSettingsActivity.buildIntent(getActivity(), UserSettingsScreen.RESET_PASSWORD));
			}
		});
		setDummyData();
	}
	
	private void setDummyData(){
		mTxtEmail.setText("pero@cipele46.org");
		mTxtPassword.setText("secret");
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mTxtEmail.setError(null);
		mTxtPassword.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mTxtEmail.getText().toString();
		mPassword = mTxtPassword.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mTxtPassword.setError(getString(R.string.error_field_required));
			focusView = mTxtPassword;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mTxtPassword.setError(getString(R.string.error_invalid_password));
			focusView = mTxtPassword;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mTxtEmail.setError(getString(R.string.error_field_required));
			focusView = mTxtEmail;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mTxtEmail.setError(getString(R.string.error_invalid_email));
			focusView = mTxtEmail;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
				}
			});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
				}
			});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}

			/*
			 * for (String credential : DUMMY_CREDENTIALS) { String[] pieces =
			 * credential.split(":"); if (pieces[0].equals(mEmail)) { // Account
			 * exists, return true if the password matches. return
			 * pieces[1].equals(mPassword); } }
			 */

			// TODO: register the new account here.
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				getActivity().finish();
			} else {
				mTxtPassword.setError(getString(R.string.error_incorrect_password));
				mTxtPassword.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}

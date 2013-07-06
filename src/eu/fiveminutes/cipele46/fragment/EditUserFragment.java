package eu.fiveminutes.cipele46.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.actionbarsherlock.app.SherlockFragment;

import eu.fiveminutes.cipele46.R;

public class EditUserFragment extends SherlockFragment{


	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.edit_user, container, false);
		return v;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		view.findViewById(R.id.btn_edit_data).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						//Save data
						getSherlockActivity().finish();
					}
				});
	}	
}

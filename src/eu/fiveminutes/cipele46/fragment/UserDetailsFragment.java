package eu.fiveminutes.cipele46.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.activity.UserSettingsActivity;
import eu.fiveminutes.cipele46.activity.UserSettingsActivity.UserSettingsScreen;
import eu.fiveminutes.cipele46.model.User;

public class UserDetailsFragment extends SherlockFragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.user_details, container, false);
		return v;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		User user = User.getActiveUser(getActivity());
		if(user != null) {
			((TextView)view.findViewById(R.id.txtName)).setText(user.getName());
			((TextView)view.findViewById(R.id.txtEmail)).setText(user.getEmail());
			((TextView)view.findViewById(R.id.txtPhone)).setText(user.getPhone());
		}
		
		view.findViewById(R.id.btn_edit_data).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						startActivity(UserSettingsActivity.buildIntent(getActivity(), UserSettingsScreen.EDIT_USER));
					}
				});
		
		view.findViewById(R.id.btn_change_password).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						startActivity(UserSettingsActivity.buildIntent(getActivity(), UserSettingsScreen.CHANGE_PASSWORD));
					}
				});
	}
}

package eu.fiveminutes.cipele46.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.fragment.SendEnquiryFragment;

public class SendEnquiryActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		SendEnquiryFragment sef = new SendEnquiryFragment();
		sef.setArguments(getIntent().getExtras());
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, sef).commit();
		ActionBar ab = getSupportActionBar();
		if (ab != null){
			ab.setTitle(R.string.enquiry);
		}
	}

}

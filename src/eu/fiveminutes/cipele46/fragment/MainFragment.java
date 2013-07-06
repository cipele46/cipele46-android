package eu.fiveminutes.cipele46.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.activity.FilterActivity;

public class MainFragment extends SherlockFragment implements OnClickListener {
	private TextView filterTxt;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.ads, container, false);
		return v;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		filterTxt = (TextView) view.findViewById(R.id.ads_filter_txt);
		filterTxt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == filterTxt) {
			Intent i = new Intent(getActivity(),FilterActivity.class);
			startActivity(i);
		}
	}

}

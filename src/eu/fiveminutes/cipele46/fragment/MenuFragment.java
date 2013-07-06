package eu.fiveminutes.cipele46.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

import eu.fiveminutes.cipele46.R;

public class MenuFragment extends SherlockFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.menu, container, false);
		v.bringToFront();
		return v;
	}

}

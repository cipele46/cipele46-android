package eu.fiveminutes.cipele46.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.activity.FilterActivity;
import eu.fiveminutes.cipele46.activity.NewAdActivity;

public class MainFragment extends SherlockFragment implements OnClickListener {
	private TextView filterTxt;
	private ListView list;
	private Adapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

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
			Intent i = new Intent(getActivity(), FilterActivity.class);
			startActivity(i);
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.main, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_new_add){
			Intent i = new Intent(getActivity(), NewAdActivity.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}
	
	

}

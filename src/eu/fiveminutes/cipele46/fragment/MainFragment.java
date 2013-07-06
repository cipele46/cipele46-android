package eu.fiveminutes.cipele46.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.activity.FilterActivity;
import eu.fiveminutes.cipele46.activity.NewAdActivity;
import eu.fiveminutes.cipele46.adapter.AdsAdapter;
import eu.fiveminutes.cipele46.api.AdsListener;
import eu.fiveminutes.cipele46.api.CipeleAPI;
import eu.fiveminutes.cipele46.model.Ad;
import eu.fiveminutes.cipele46.model.AdType;

public class MainFragment extends SherlockFragment implements OnClickListener, OnItemClickListener {
	private TextView filterTxt;
	private ListView list;
	private AdsAdapter adapter;

	private AdsListener adsListener = new AdsListener() {

		@Override
		public void onSuccess(List<Ad> ad) {
			adapter = new AdsAdapter(getActivity(), ad);
			list.setAdapter(adapter);
		}

		@Override
		public void onFailure(Throwable t) {
			Toast.makeText(getActivity(), R.string.error_get_ads, Toast.LENGTH_LONG).show();

		}
	};

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
		list = (ListView)view.findViewById(R.id.ads_list);
		list.setOnItemClickListener(this);
		getData();
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
		if (item.getItemId() == R.id.action_new_add) {
			Intent i = new Intent(getActivity(), NewAdActivity.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

	private void getData() {
		CipeleAPI.get().getAds(AdType.DEMAND, null, null, adsListener);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}

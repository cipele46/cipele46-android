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
import eu.fiveminutes.cipele46.activity.MainActivity;

public class MenuFragment extends SherlockFragment implements OnClickListener {

	public interface OnMenuItemSelectedListener {
		void onMenuItemSelected(int itemId);
	}

	private TextView ads;
	private TextView active_ads;
	private TextView favorite_ads;
	private TextView closed_ads;
	private TextView settings;

	private OnMenuItemSelectedListener menuListener;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getActivity() instanceof OnMenuItemSelectedListener) {
			this.menuListener = (OnMenuItemSelectedListener) getActivity();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.menu, container, false);
		return v;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		ads = (TextView) view.findViewById(R.id.menu_item_ads);
		active_ads = (TextView) view.findViewById(R.id.menu_item_ads_active);
		favorite_ads = (TextView) view.findViewById(R.id.menu_item_ads_favorites);
		closed_ads = (TextView) view.findViewById(R.id.menu_item_ads_closed);
		settings = (TextView) view.findViewById(R.id.menu_item_settings);

		ads.setOnClickListener(this);
		active_ads.setOnClickListener(this);
		favorite_ads.setOnClickListener(this);
		closed_ads.setOnClickListener(this);
		settings.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == ads && !(getActivity() instanceof MainActivity)) {
			startActivity(new Intent(getActivity(), MainActivity.class));
		} else if (v == active_ads) {

		} else if (v == favorite_ads) {

		} else if (v == closed_ads) {

		} else if (v == settings) {

		}

	}

}

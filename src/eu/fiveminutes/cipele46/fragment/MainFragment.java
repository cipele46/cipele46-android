package eu.fiveminutes.cipele46.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
import eu.fiveminutes.cipele46.activity.AdDetailsActivity;
import eu.fiveminutes.cipele46.activity.FilterActivity;
import eu.fiveminutes.cipele46.activity.NewAdActivity;
import eu.fiveminutes.cipele46.adapter.AdsAdapter;
import eu.fiveminutes.cipele46.adapter.CategoryAdapter;
import eu.fiveminutes.cipele46.adapter.DistrictAdapter;
import eu.fiveminutes.cipele46.api.AdsListener;
import eu.fiveminutes.cipele46.api.CategoriesListener;
import eu.fiveminutes.cipele46.api.CipeleAPI;
import eu.fiveminutes.cipele46.api.DistrictWithCitiesListener;
import eu.fiveminutes.cipele46.app.Filters;
import eu.fiveminutes.cipele46.model.Ad;
import eu.fiveminutes.cipele46.model.AdType;
import eu.fiveminutes.cipele46.model.Category;
import eu.fiveminutes.cipele46.model.District;
import eu.fiveminutes.cipele46.utils.Util;

public class MainFragment extends SherlockFragment implements OnClickListener,
		OnItemClickListener {
	private TextView filterTxt;
	private ListView list;
	private AdsAdapter adapter;

	private AdType activeAdType;
	private Long activeDistrict;
	private Long activeCategory;

	private AdsListener adsListener = new AdsListener() {

		@Override
		public void onSuccess(List<Ad> ad) {
			adapter = new AdsAdapter(getActivity(), ad);
			list.setAdapter(adapter);
			hideDialog();
		}

		@Override
		public void onFailure(Throwable t) {
			hideDialog();
			Toast.makeText(getActivity(), R.string.error_get_ads,
					Toast.LENGTH_LONG).show();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.ads, container, false);
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		AdType newAdType = Filters.getAdTypeFilter(getActivity());
		Long newCategory = Filters.getCategoryFilter(getActivity());
		Long newDistrict = Filters.getDistrictFilter(getActivity());

		if (!newAdType.equals(activeAdType)
				|| !newCategory.equals(activeCategory)
				|| !newDistrict.equals(activeDistrict)) {

			activeAdType = newAdType;
			activeCategory = newCategory;
			activeDistrict = newDistrict;
			getData();
		}
		generateFilterText();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		filterTxt = (TextView) view.findViewById(R.id.ads_filter_txt);
		filterTxt.setOnClickListener(this);
		list = (ListView) view.findViewById(R.id.ads_list);
		list.setOnItemClickListener(this);
		// getData();
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
		if (Util.isOnline(getActivity())) {
			showDialog();

			CipeleAPI.get().getCategories(new CategoriesListener() {

				@Override
				public void onSuccess(List<Category> categories) {

					CipeleAPI.get().getDistrictWithCities(
							new DistrictWithCitiesListener() {

								@Override
								public void onSuccess(List<District> districts) {
									CipeleAPI.get().getAds(activeAdType,
											activeCategory, activeDistrict,
											adsListener);
								}

								@Override
								public void onFailure(Throwable t) {
									Toast.makeText(getActivity(),
											R.string.error_get_dist,
											Toast.LENGTH_LONG).show();
								}
							});
				}

				@Override
				public void onFailure(Throwable t) {
					Toast.makeText(getActivity(), R.string.error_get_cat,
							Toast.LENGTH_LONG).show();
				}
			});

		} else {
			Toast.makeText(getActivity(),
					R.string.error_no_internet_connection, Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		Intent i = new Intent(getActivity(), AdDetailsActivity.class);
		i.putExtra("adItem", (Ad) adapter.getItem(position));
		startActivity(i);
	}

	private void showDialog() {
		ProgressDialogFragment pdf = new ProgressDialogFragment();
		pdf.show(getFragmentManager(), "pdf");
	}

	private void hideDialog() {
		ProgressDialogFragment pdf = (ProgressDialogFragment) getFragmentManager()
				.findFragmentByTag("pdf");
		if (pdf != null) {
			pdf.dismiss();
		}
	}

	private void generateFilterText() {
		String title = "";
		if (activeAdType == AdType.DEMAND) {
			title += getString(R.string.filter_demand);
		} else {
			title += getString(R.string.filter_supply);
		}

		title += ", " + Filters.getCategoryFilterName(this.getActivity())
				+ ", " + Filters.getDistrictFilterName(this.getActivity());

		filterTxt.setText(title);
	}

}

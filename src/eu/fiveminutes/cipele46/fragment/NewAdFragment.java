package eu.fiveminutes.cipele46.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.actionbarsherlock.app.SherlockFragment;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.adapter.CategoryAdapter;
import eu.fiveminutes.cipele46.adapter.CityAdapter;
import eu.fiveminutes.cipele46.adapter.DistrictAdapter;
import eu.fiveminutes.cipele46.api.CategoriesListener;
import eu.fiveminutes.cipele46.api.CipeleAPI;
import eu.fiveminutes.cipele46.api.DistrictWithCitiesListener;
import eu.fiveminutes.cipele46.model.Category;
import eu.fiveminutes.cipele46.model.City;
import eu.fiveminutes.cipele46.model.District;

public class NewAdFragment extends SherlockFragment implements OnItemSelectedListener {

	private static final int CAMERA_REQUEST = 1888;
	private static final int MAX_WIDTH = 800;
	private ImageView imageView;
	private Button publishButton;
	private TextView emailError;
	private TextView phoneError;
	private EditText email;
	private EditText phone;

	private Spinner citySpinner;
	private Spinner categorySpinner;
	private Spinner districtSpinner;

	private City activeCity;
	private District activeDistrict;
	private Category activeCategory;

	private CityAdapter cityAdapter;
	private DistrictAdapter districtAdapter;
	private CategoryAdapter categoryAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.new_ad, container, false);
		return v;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		imageView = (ImageView) view.findViewById(R.id.new_image);

		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, CAMERA_REQUEST);
			}
		});

		emailError = (TextView) view.findViewById(R.id.email_error);
		phoneError = (TextView) view.findViewById(R.id.phone_error);
		phone = (EditText) view.findViewById(R.id.phone);
		email = (EditText) view.findViewById(R.id.email);
		citySpinner = (Spinner) view.findViewById(R.id.filter_city);
		districtSpinner = (Spinner) view.findViewById(R.id.filter_county);
		categorySpinner = (Spinner) view.findViewById(R.id.filter_category);

		categorySpinner.setOnItemSelectedListener(this);
		districtSpinner.setOnItemSelectedListener(this);
		citySpinner.setOnItemSelectedListener(this);

		publishButton = (Button) view.findViewById(R.id.publish_ad);
		publishButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isEmailValid(email.getText()) && isPhoneValid(phone.getText())) {
					// try to create object
				}
			}

			private boolean isEmailValid(CharSequence target) {
				if ((target == null) || !(android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches())) {
					emailError.setVisibility(View.VISIBLE);
					return false;
				} else {
					emailError.setVisibility(View.INVISIBLE);
					return true;
				}

			}

			private boolean isPhoneValid(CharSequence target) {
				if ((target == null) || !(android.util.Patterns.PHONE.matcher(target).matches())) {
					phoneError.setVisibility(View.VISIBLE);
					return false;
				} else {
					phoneError.setVisibility(View.INVISIBLE);
					return true;
				}
			}
		});
		getData();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_REQUEST && resultCode == getActivity().RESULT_OK) {
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			imageView.setImageBitmap(getResizedBitmap(photo, MAX_WIDTH));
		}
	}

	public Bitmap getResizedBitmap(Bitmap bm, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scale = ((float) newWidth) / width;

		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);

		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		return resizedBitmap;
	}

	private void getData() {
		CipeleAPI.get().getCategories(new CategoriesListener() {

			@Override
			public void onSuccess(List<Category> categories) {

				categoryAdapter = new CategoryAdapter(getActivity(), categories);
				categorySpinner.setAdapter(categoryAdapter);
			}

			@Override
			public void onFailure(Throwable t) {
				Toast.makeText(getActivity(), R.string.error_get_cat, Toast.LENGTH_LONG).show();
			}
		});

		CipeleAPI.get().getDistrictWithCities(new DistrictWithCitiesListener() {

			@Override
			public void onSuccess(List<District> districts) {
				districtAdapter = new DistrictAdapter(getActivity(), districts);
				districtSpinner.setAdapter(districtAdapter);

				List<City> cities = new ArrayList<City>();
				for (District d : districts) {
					if (d.getCities() != null) {
						for (City city : d.getCities()) {
							cities.add(city);
						}
					}
				}

				cityAdapter = new CityAdapter(getActivity(), cities);
				citySpinner.setAdapter(cityAdapter);
			}

			@Override
			public void onFailure(Throwable t) {
				Toast.makeText(getActivity(), R.string.error_get_dist, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View v, int position, long id) {
		if (adapterView == citySpinner) {
			activeCity = (City) cityAdapter.getItem(position);
		} else if (adapterView == categorySpinner) {
			activeCategory = (Category) categoryAdapter.getItem(position);
		} else if (adapterView == districtSpinner) {
			activeDistrict = (District) districtAdapter.getItem(position);
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}

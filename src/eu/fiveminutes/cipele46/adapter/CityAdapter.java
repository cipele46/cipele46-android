package eu.fiveminutes.cipele46.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import eu.fiveminutes.cipele46.model.City;
import eu.fiveminutes.cipele46.model.District;

public class CityAdapter extends BaseAdapter implements SpinnerAdapter{
	
	private List<City> cities;
	private LayoutInflater inflater;
	
	public CityAdapter(Context context, List<City> cities) {
		this.cities = cities;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cities.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cities.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null){
			v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
		}
		TextView t = (TextView)v.findViewById(android.R.id.text1);
		t.setText(cities.get(position).getName());
		return v;
	}

}

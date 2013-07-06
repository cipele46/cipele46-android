package eu.fiveminutes.cipele46.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import eu.fiveminutes.cipele46.model.District;

public class DistrictAdapter extends BaseAdapter implements SpinnerAdapter {

	private List<District> districts;
	private LayoutInflater inflater;
	
	public DistrictAdapter(Context context, List<District> districts) {
		this.districts = districts;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return districts != null ? districts.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return districts != null ? districts.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return districts != null ? Long.parseLong(districts.get(position).getId()) : 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View spinView;
	    if( convertView == null ) {
	        spinView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
	    } else {
	         spinView = convertView;
	    }
	    
	    TextView t1 = (TextView) spinView.findViewById(android.R.id.text1);
	    t1.setText(districts.get(position).getName());
	    return spinView;
	}

}

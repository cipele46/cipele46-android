package eu.fiveminutes.cipele46.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class AdTypeAdapter extends BaseAdapter implements SpinnerAdapter {

	private List<String> adTypes;
	private LayoutInflater inflater;
	
	public AdTypeAdapter(Context context, List<String> adTypes) {
		this.adTypes = adTypes;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return adTypes != null ? adTypes.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return adTypes != null ? adTypes.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return position;
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
	    t1.setText(adTypes.get(position));
	    return spinView;
	}

}

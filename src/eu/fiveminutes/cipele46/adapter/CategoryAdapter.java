package eu.fiveminutes.cipele46.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import eu.fiveminutes.cipele46.model.Category;

public class CategoryAdapter extends BaseAdapter implements SpinnerAdapter {

	private List<Category> categories;
	private LayoutInflater inflater;
	
	public CategoryAdapter(Context context, List<Category> categories) {
		this.categories = categories;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return categories != null ? categories.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return categories != null ? categories.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return categories != null ? Long.parseLong(categories.get(position).getId()) : 0;
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
	    t1.setText(categories.get(position).getName());
	    return spinView;
	}

}

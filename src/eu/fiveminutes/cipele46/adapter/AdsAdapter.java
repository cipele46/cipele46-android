package eu.fiveminutes.cipele46.adapter;

import java.util.List;

import com.android.volley.toolbox.NetworkImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.model.Ad;
import eu.fiveminutes.cipele46.utils.ImageCacheManager;

public class AdsAdapter extends BaseAdapter {

	private List<Ad> items;
	private Context mContext;
	private LayoutInflater inflater;

	public AdsAdapter(Context context, List<Ad> items) {
		this.mContext = context;
		this.items = items;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {

		return items != null ? items.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return items != null ? items.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Ad item = items.get(position);
		View v = convertView;
		ViewHolder holder;
		if (v == null) {
			holder = new ViewHolder();
			v = inflater.inflate(R.layout.ad, parent, false);
			holder.image = (NetworkImageView) v.findViewById(R.id.ad_image);
			holder.title = (TextView) v.findViewById(R.id.ad_title);
			holder.county = (TextView) v.findViewById(R.id.ad_county_txt);
			holder.category = (TextView) v.findViewById(R.id.ad_category_txt);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.image.setDefaultImageResId(R.drawable.ic_launcher);
		holder.image.setImageUrl(item.getImageURLString(), ImageCacheManager.getInstance().getImageLoader());
		holder.title.setText(item.getTitle());
		holder.category.setText("" + item.getCategoryID());
		holder.county.setText("" + item.getCityID());
		return v;
	}

	class ViewHolder {
		NetworkImageView image;
		TextView title;
		TextView county;
		TextView category;
	}

}

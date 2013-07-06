package eu.fiveminutes.cipele46.adapter;

import java.util.List;

import com.android.volley.toolbox.NetworkImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.model.Ad;
import eu.fiveminutes.cipele46.model.AdType;
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
			holder.categoryImage = (ImageView) v.findViewById(R.id.ad_category_image);
			holder.countyImage = (ImageView) v.findViewById(R.id.ad_county_image);
			holder.timeImage = (ImageView) v.findViewById(R.id.ad_time_image);
			holder.timeText = (TextView) v.findViewById(R.id.ad_time_txt);
			holder.topBorder = v.findViewById(R.id.ad_top_border);
			holder.middleBorder = v.findViewById(R.id.ad_middle_border);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.image.setDefaultImageResId(R.drawable.ic_launcher);
		holder.image.setImageUrl(item.getImageURLString(), ImageCacheManager.getInstance().getImageLoader());
		holder.title.setText(item.getTitle());
		holder.category.setText("" + item.getCategoryID());
		holder.county.setText("" + item.getCityID());
		holder.timeText.setText("21 dan");
		if (item.getType() == AdType.SUPPLY) {
			holder.categoryImage.setImageResource(R.drawable.category_icon_blue);
			holder.countyImage.setImageResource(R.drawable.place_icon_blue);
			holder.timeImage.setImageResource(R.drawable.time_icon_blue);
			holder.topBorder.setBackgroundColor(mContext.getResources().getColor(R.color.blue_divider));
			holder.middleBorder.setBackgroundColor(mContext.getResources().getColor(R.color.blue_divider));
		} else {
			holder.categoryImage.setImageResource(R.drawable.category_icon_red);
			holder.countyImage.setImageResource(R.drawable.place_icon_red);
			holder.timeImage.setImageResource(R.drawable.time_icon_red);
			holder.topBorder.setBackgroundColor(mContext.getResources().getColor(R.color.red_divider));
			holder.middleBorder.setBackgroundColor(mContext.getResources().getColor(R.color.red_divider));

		}
		return v;
	}

	class ViewHolder {
		NetworkImageView image;
		TextView title;
		TextView county;
		TextView category;
		ImageView countyImage;
		ImageView categoryImage;
		ImageView timeImage;
		TextView timeText;
		View topBorder;
		View middleBorder;
	}

}

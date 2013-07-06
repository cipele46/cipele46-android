package eu.fiveminutes.cipele46.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.android.volley.toolbox.NetworkImageView;

import eu.fiveminutes.cipele46.R;
import eu.fiveminutes.cipele46.model.Ad;
import eu.fiveminutes.cipele46.model.AdStatus;
import eu.fiveminutes.cipele46.utils.ImageCacheManager;

public class AdDetailsFragment extends SherlockFragment implements OnClickListener{

	private Ad item;

	private TextView awaitingApproval;
	private TextView title;
	private TextView description;
	private NetworkImageView image;
	private TextView category;
	private TextView county;
	private Button call;
	private Button sendMail;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.item = getArguments().getParcelable("adItem");
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.ad_details, container, false);
		return v;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		awaitingApproval= (TextView) view.findViewById(R.id.ad_details_awaining_approval);
		title = (TextView) view.findViewById(R.id.ad_details_title);
		description = (TextView) view.findViewById(R.id.ad_details_description);
		category = (TextView) view.findViewById(R.id.ad_details_category);
		county = (TextView) view.findViewById(R.id.ad_details_county);
		image = (NetworkImageView) view.findViewById(R.id.ad_details_image);
		call = (Button)view.findViewById(R.id.ad_details_call);
		sendMail = (Button)view.findViewById(R.id.ad_details_send_mail);

		if (item.getStatus() == AdStatus.PENDING){
			awaitingApproval.setVisibility(View.VISIBLE);
		}else{
			awaitingApproval.setVisibility(View.GONE);
		}
		
		image.setDefaultImageResId(R.drawable.ic_launcher);
		image.setImageUrl(item.getImageURLString(), ImageCacheManager.getInstance().getImageLoader());
		title.setText(item.getTitle());
		description.setText(item.getDescription());
		category.setText(item.getCategoryID() + "");
		county.setText(item.getCityID() + "");
		call.setText(getString(R.string.call) + " (" + item.getPhone() + ")");
		
		call.setOnClickListener(this);
		sendMail.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == call){
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.getPhone()));
			startActivity(intent);
		}else if (v == sendMail){
			//start new activity to send request on server
		}
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.details, menu);
	}
}

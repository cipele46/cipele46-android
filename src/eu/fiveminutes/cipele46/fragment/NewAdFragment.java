package eu.fiveminutes.cipele46.fragment;

import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import eu.fiveminutes.cipele46.R;

public class NewAdFragment extends SherlockFragment{
	
	private static final int CAMERA_REQUEST = 1888;
	private static final int MAX_WIDTH = 800; 
    private ImageView imageView;
    private Button publishButton;
    private TextView emailError;
    private TextView phoneError;
    private EditText email;
    private EditText phone;
    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.new_ad, container, false);
		return v;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		imageView = (ImageView)view.findViewById(R.id.new_image);
		
		imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
                startActivityForResult(cameraIntent, CAMERA_REQUEST); 
            }
        });
		
		
		emailError = (TextView)view.findViewById(R.id.email_error);
		phoneError = (TextView)view.findViewById(R.id.phone_error);
		phone = (EditText)view.findViewById(R.id.phone);
		email = (EditText)view.findViewById(R.id.email);
		
		publishButton = (Button)view.findViewById(R.id.publish_ad);
		publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if (isEmailValid(email.getText()) && isPhoneValid(phone.getText())){
            		//try to create object
            	}
            }
			private boolean isEmailValid(CharSequence target) {
				if ((target == null) || 
						!(android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches())){
					emailError.setVisibility(View.VISIBLE);
					return false;
			    } else {
			    	emailError.setVisibility(View.INVISIBLE);
			        return true;
			    }

			}
			private boolean isPhoneValid(CharSequence target) {
				if ((target == null) || 
						!(android.util.Patterns.PHONE.matcher(target).matches())){
					phoneError.setVisibility(View.VISIBLE);
					return false;
			    } else {
			    	phoneError.setVisibility(View.INVISIBLE);
			        return true;
			    }
			}
		});
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

}

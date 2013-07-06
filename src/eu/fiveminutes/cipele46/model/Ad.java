package eu.fiveminutes.cipele46.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ad implements Parcelable {

	private Long id;
	private Long districtID;
	private Long cityID;
	private Long categoryID;
	
	private String title;
	private String description;
	private String imageURLString;
	private String email;
	private String phone;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURLString() {
		return imageURLString;
	}

	public void setImageURLString(String imageURLString) {
		this.imageURLString = imageURLString;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getDistrictID() {
		return districtID;
	}

	public void setDistrictID(Long districtID) {
		this.districtID = districtID;
	}

	public Long getCityID() {
		return cityID;
	}

	public void setCityID(Long cityID) {
		this.cityID = cityID;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	/* Parcelable stuff starts here */

	public Ad() {
	}

	public Ad(Parcel in) {
		String[] data = new String[9];
		in.readStringArray(data);
		this.id = Long.parseLong(data[0]);
		this.districtID = Long.parseLong(data[1]);
		this.cityID = Long.parseLong(data[2]);
		this.categoryID = Long.parseLong(data[3]);
		this.title = data[4];
		this.description = data[5];
		this.imageURLString = data[6];
		this.email = data[7];	
		this.phone = data[8];
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] { 
				String.valueOf(id), 
				String.valueOf(districtID),
				String.valueOf(cityID),
				String.valueOf(categoryID),
				title,
				description,
				imageURLString,
				email,
				phone});

	}
	
	public static final Parcelable.Creator<Ad> CREATOR= new Parcelable.Creator<Ad>() {

		@Override
		public Ad createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Ad(source);
		}

		@Override
		public Ad[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Ad[size];
		}
		
	};

}

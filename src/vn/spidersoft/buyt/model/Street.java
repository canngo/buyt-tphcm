package vn.spidersoft.buyt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Street implements Parcelable {
	public final static String COL_ID 			= "id";
	public final static String COL_NAME 		= "name";
	public final static String COL_AREA 		= "area";
	
	int mId = 0;
	String mName = "";
	String mArea = "";
	
	public Street(String name, String area) {
		super();
		this.mName = name;
		this.mArea = area;
	}
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		this.mId = id;
	}
	public String getName() {
		return mName;
	}
	public void setName(String name) {
		this.mName = name;
	}
	public String getArea() {
		return mArea;
	}
	public void setArea(String area) {
		this.mArea = area;
	}
	public int describeContents() {
		return 0;
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mId);
		dest.writeString(mName);
		dest.writeString(mArea);
	}
	
	public static final Parcelable.Creator<Street> CREATOR
		= new Parcelable.Creator<Street>() {
		public Street createFromParcel(Parcel in) {
			return new Street(in);
	}
	
	public Street[] newArray(int size) {
		return new Street[size];
	}
	};
	public Street(Parcel in) {
		mId = in.readInt();
		mName = in.readString();
		mArea = in.readString();
	}
}

package vn.spidersoft.buyt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Buyt implements Parcelable {
	public final static String COL_ID 			= "id";
	public final static String COL_CODE 		= "code";
	public final static String COL_NAME 		= "name";
	
	int mId = 0;
	String mCode = "";
	String mName = "";
	
	public Buyt(String code, String name) {
		this.mCode = code;
		this.mName = name;
	}
	
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		this.mId = id;
	}
	public String getCode() {
		return mCode;
	}
	public void setCode(String code) {
		this.mCode = code;
	}
	public String getName() {
		return mName;
	}
	public void setName(String name) {
		this.mName = name;
	}

	public static final Parcelable.Creator<Buyt> CREATOR
		= new Parcelable.Creator<Buyt>() {
		public Buyt createFromParcel(Parcel in) {
			return new Buyt(in);
	}
	
	public Buyt[] newArray(int size) {
		return new Buyt[size];
	}
	};
	public Buyt(Parcel in) {
		mId = in.readInt();
		mCode = in.readString();
		mName = in.readString();
	}
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mId);
		dest.writeString(mCode);
		dest.writeString(mName);
	}
	
}

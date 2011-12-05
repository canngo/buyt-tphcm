package vn.spidersoft.buyt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BusStop implements Parcelable {
	public final static String COL_ID 			= "id";
	public final static String COL_BUYT_CODE 	= "buyt_code";
	public final static String COL_BUYT_GOING 	= "buyt_going";
	public final static String COL_STREET_NAME 	= "street_name";
	public final static String COL_HOUSE_NUM 	= "house_num";
	public final static String COL_AREA	 		= "area";
	
	int mId;
	String mBuytCode;
	String mBuytGoing;
	String mStreetName;
	String mHouseNum;
	String mArea;
	
	
	public BusStop(String buytCode, String buytGoing,String streetName,
			String houseNum, String area) {
		this.mBuytCode = buytCode;
		this.mBuytGoing = buytGoing;
		this.mStreetName = streetName;
		this.mHouseNum = houseNum;
		this.mArea = area;
	}
	
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		this.mId = id;
	}
	public String getBuytCode() {
		return mBuytCode;
	}
	public void setBuytCode(String buytCode) {
		this.mBuytCode = buytCode;
	}
	
	public String getBuytGoing() {
		return mBuytGoing;
	}

	public void setmBuytGoing(String mBuytGoing) {
		this.mBuytGoing = mBuytGoing;
	}

	public String getStreetName() {
		return mStreetName;
	}
	public void setStreetName(String streetName) {
		this.mStreetName = streetName;
	}
	public String getHouseNum() {
		return mHouseNum;
	}
	public void setHouseNum(String houseNum) {
		this.mHouseNum = houseNum;
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

	public static final Parcelable.Creator<BusStop> CREATOR
		= new Parcelable.Creator<BusStop>() {
		public BusStop createFromParcel(Parcel in) {
			return new BusStop(in);
	}
	
	public BusStop[] newArray(int size) {
		return new BusStop[size];
	}
	};
	public BusStop(Parcel in) {
		mId = in.readInt();
		mBuytCode = in.readString();
		mStreetName = in.readString();
		mHouseNum = in.readString();
		mArea = in.readString();
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mId);
		dest.writeString(mBuytCode);
		dest.writeString(mStreetName);
		dest.writeString(mHouseNum);
		dest.writeString(mArea);
	}
	
	
}

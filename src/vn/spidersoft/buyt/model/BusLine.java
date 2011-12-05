package vn.spidersoft.buyt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BusLine implements Parcelable {
	public final static String COL_ID 			= "id";
	public final static String COL_BUYT_CODE 	= "buyt_code";
	public final static String COL_BUYT_GOING 	= "buyt_going";
	public final static String COL_BUYT_CODE_EX = "buyt_code_ex";
	
	int mId;
	String mBuytCode;
	String mBuytGoing;
	String mBuytCodeEx;
	
	
	public BusLine(String buytCode, String buytName, String buytCodeEx) {
		this.mBuytCode = buytCode;
		this.mBuytGoing = buytName;
		this.mBuytCodeEx = buytCodeEx;
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
	public void setBuytGoing(String buytGoing) {
		this.mBuytGoing = buytGoing;
	}
	public String getBuytCodeEx() {
		return mBuytCodeEx;
	}
	public void setBuytCodeEx(String buytCodeEx) {
		this.mBuytCodeEx = buytCodeEx;
	}
	
	public static final Parcelable.Creator<BusLine> CREATOR
		= new Parcelable.Creator<BusLine>() {
		public BusLine createFromParcel(Parcel in) {
			return new BusLine(in);
	}
	
	public BusLine[] newArray(int size) {
		return new BusLine[size];
	}
	};
	public BusLine(Parcel in) {
		mId = in.readInt();
		mBuytCode = in.readString();
		mBuytGoing = in.readString();
		mBuytCodeEx = in.readString();
	}

	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mId);
		dest.writeString(mBuytCode);
		dest.writeString(mBuytGoing);
		dest.writeString(mBuytCodeEx);
	}

}
